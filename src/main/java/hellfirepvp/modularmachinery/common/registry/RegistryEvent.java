package hellfirepvp.modularmachinery.common.registry;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;

public class RegistryEvent extends Event {
    public static class RegisterBlocks extends RegistryEvent {
        public void register(Block block) {}
        public void register(Block block, Class<? extends TileEntity> teClass) {}
    }
    public static class RegisterItems extends RegistryEvent {
        public void register(Item item) {}
    }
    public static class RegisterRecipes extends RegistryEvent {
        public void register(IRecipe recipe) {}
    }
}
