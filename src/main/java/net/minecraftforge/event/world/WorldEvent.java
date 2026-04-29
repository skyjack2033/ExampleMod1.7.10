package net.minecraftforge.event.world;

import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Event;

public class WorldEvent extends Event {

    public WorldEvent() {
    }

    public static class Load extends Event {
        public final World world;

        public Load(World world) {
            this.world = world;
        }
    }
}
