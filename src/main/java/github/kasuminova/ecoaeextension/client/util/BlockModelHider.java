package github.kasuminova.ecoaeextension.client.util;

import github.kasuminova.ecoaeextension.common.tile.ecotech.NovaMultiBlockBase;
import hellfirepvp.modularmachinery.common.tiles.base.TileMultiblockMachineController;
import github.kasuminova.ecoaeextension.common.util.BlockPos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import java.util.stream.Collectors;

public class BlockModelHider {

    @SideOnly(Side.CLIENT)
    public static void hideOrShowBlocks(final List<BlockPos> posList, final NovaMultiBlockBase ctrl) {
        BlockPos ctrlPos = ctrl.getPos();
        if (ctrl.isInvalid() || !ctrl.isStructureFormed()) {
            MultiblockWorldSavedData.removeDisableModel(ctrlPos);
            return;
        }

        MultiblockWorldSavedData.addDisableModel(ctrlPos, posList.stream()
                .map(pos -> rotateYCCWNorthUntil(pos, ctrl.getControllerRotation()))
                .map(pos -> pos.add(ctrlPos))
                .collect(Collectors.toList()));
    }

    /** Bridge for old MMCE-based controllers still using TileMultiblockMachineController. */
    @SideOnly(Side.CLIENT)
    public static void hideOrShowBlocks(final List<BlockPos> posList, final TileMultiblockMachineController ctrl) {
        BlockPos ctrlPos = ctrl.getPos();
        if (ctrl.isInvalid() || !ctrl.isStructureFormed()) {
            MultiblockWorldSavedData.removeDisableModel(ctrlPos);
            return;
        }

        MultiblockWorldSavedData.addDisableModel(ctrlPos, posList.stream()
                .map(pos -> rotateYCCWNorthUntil(pos, ctrl.getControllerRotation()))
                .map(pos -> pos.add(ctrlPos))
                .collect(Collectors.toList()));
    }

    /** Rotate a relative BlockPos counter-clockwise from NORTH to the given direction. */
    private static BlockPos rotateYCCWNorthUntil(BlockPos relPos, net.minecraftforge.common.util.ForgeDirection dir) {
        switch (dir) {
            case NORTH: return relPos;
            case SOUTH: return new BlockPos(-relPos.getX(), relPos.getY(), -relPos.getZ());
            case WEST:  return new BlockPos(-relPos.getZ(), relPos.getY(), relPos.getX());
            case EAST:  return new BlockPos(relPos.getZ(), relPos.getY(), -relPos.getX());
            default:    return relPos;
        }
    }
}
