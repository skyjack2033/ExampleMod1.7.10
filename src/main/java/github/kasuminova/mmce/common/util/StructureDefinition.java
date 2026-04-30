package github.kasuminova.mmce.common.util;

import com.google.gson.*;
import github.kasuminova.ecoaeextension.common.util.BlockPos;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.ForgeDirection;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Minimal multi-block structure definition for 1.7.10.
 * Parses the MMCE JSON machine definition format and provides block validation.
 */
public class StructureDefinition {

    public final String registryName;
    public final List<PatternEntry> fixedParts = new ArrayList<>();
    public final List<DynamicPattern> dynamicPatterns = new ArrayList<>();
    public BlockPos min = new BlockPos(0, 0, 0);
    public BlockPos max = new BlockPos(0, 0, 0);

    public StructureDefinition(String registryName) {
        this.registryName = registryName;
    }

    public static StructureDefinition fromJsonResource(String path) {
        try {
            InputStream is = StructureDefinition.class.getClassLoader().getResourceAsStream(path);
            if (is == null) return null;
            JsonObject root = new JsonParser().parse(new InputStreamReader(is)).getAsJsonObject();
            return fromJson(root);
        } catch (Exception e) {
            return null;
        }
    }

    public static StructureDefinition fromJsonString(String json) {
        return fromJson(new JsonParser().parse(json).getAsJsonObject());
    }

    public static StructureDefinition fromJson(JsonObject root) {
        String name = root.get("registryname").getAsString();
        StructureDefinition def = new StructureDefinition(name);

        // Parse fixed parts
        if (root.has("parts")) {
            for (JsonElement e : root.getAsJsonArray("parts")) {
                JsonObject part = e.getAsJsonObject();
                def.fixedParts.add(parsePart(part));
            }
        }

        // Parse dynamic patterns
        if (root.has("dynamic-patterns")) {
            for (JsonElement e : root.getAsJsonArray("dynamic-patterns")) {
                def.dynamicPatterns.add(parseDynamicPattern(e.getAsJsonObject()));
            }
        }

        // Calculate bounding box
        def.recalcBounds();
        return def;
    }

    private static PatternEntry parsePart(JsonObject part) {
        int x = part.get("x").getAsInt();
        int y = part.get("y").getAsInt();
        int z = part.get("z").getAsInt();
        List<String> elements = new ArrayList<>();
        for (JsonElement e : part.getAsJsonArray("elements")) {
            elements.add(e.getAsString());
        }
        return new PatternEntry(x, y, z, elements);
    }

    private static DynamicPattern parseDynamicPattern(JsonObject dp) {
        String name = dp.get("name").getAsString();
        int minSize = dp.get("minSize").getAsInt();
        int maxSize = dp.get("maxSize").getAsInt();
        ForgeDirection face = parseFace(dp.getAsJsonArray("faces").get(0).getAsString());

        List<PatternEntry> parts = new ArrayList<>();
        if (dp.has("parts")) {
            for (JsonElement e : dp.getAsJsonArray("parts")) {
                parts.add(parsePart(e.getAsJsonObject()));
            }
        }

        return new DynamicPattern(name, face, minSize, maxSize, parts);
    }

    private static ForgeDirection parseFace(String face) {
        switch (face.toUpperCase()) {
            case "WEST": return ForgeDirection.WEST;
            case "EAST": return ForgeDirection.EAST;
            case "NORTH": return ForgeDirection.NORTH;
            case "SOUTH": return ForgeDirection.SOUTH;
            case "UP": return ForgeDirection.UP;
            case "DOWN": return ForgeDirection.DOWN;
            default: return ForgeDirection.NORTH;
        }
    }

    private void recalcBounds() {
        int minX = 0, minY = 0, minZ = 0, maxX = 0, maxY = 0, maxZ = 0;
        for (PatternEntry e : fixedParts) {
            minX = Math.min(minX, e.x); minY = Math.min(minY, e.y); minZ = Math.min(minZ, e.z);
            maxX = Math.max(maxX, e.x); maxY = Math.max(maxY, e.y); maxZ = Math.max(maxZ, e.z);
        }
        for (DynamicPattern dp : dynamicPatterns) {
            for (int i = 0; i < dp.maxSize; i++) {
                for (PatternEntry e : dp.parts) {
                    int px = e.x + dp.direction.offsetX * i;
                    int py = e.y + dp.direction.offsetY * i;
                    int pz = e.z + dp.direction.offsetZ * i;
                    minX = Math.min(minX, px); minY = Math.min(minY, py); minZ = Math.min(minZ, pz);
                    maxX = Math.max(maxX, px); maxY = Math.max(maxY, py); maxZ = Math.max(maxZ, pz);
                }
            }
        }
        this.min = new BlockPos(minX, minY, minZ);
        this.max = new BlockPos(maxX, maxY, maxZ);
    }

    public static class PatternEntry {
        public final int x, y, z;
        public final List<String> elements;

        public PatternEntry(int x, int y, int z, List<String> elements) {
            this.x = x; this.y = y; this.z = z;
            this.elements = elements;
        }

        public boolean matches(Block block, int meta) {
            String blockName = Block.blockRegistry.getNameForObject(block);
            if (blockName == null) return false;
            for (String element : elements) {
                // element format: "modid:blockname@meta" or "modid:blockname"
                String[] parts = element.split("@");
                String expectedName = parts[0];
                int expectedMeta = parts.length > 1 ? Integer.parseInt(parts[1]) : -1;
                if (blockName.equals(expectedName) && (expectedMeta < 0 || meta == expectedMeta)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class DynamicPattern {
        public final String name;
        public final ForgeDirection direction;
        public final int minSize;
        public final int maxSize;
        public final List<PatternEntry> parts;

        public DynamicPattern(String name, ForgeDirection direction, int minSize, int maxSize, List<PatternEntry> parts) {
            this.name = name;
            this.direction = direction;
            this.minSize = minSize;
            this.maxSize = maxSize;
            this.parts = parts;
        }

        public int getMaxRepeat() {
            return maxSize;
        }
    }
}
