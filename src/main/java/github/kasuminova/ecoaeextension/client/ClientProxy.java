package github.kasuminova.ecoaeextension.client;

import github.kasuminova.ecoaeextension.client.gui.*;
import github.kasuminova.ecoaeextension.common.CommonProxy;
import github.kasuminova.ecoaeextension.common.registry.RegistryBlocks;
import github.kasuminova.ecoaeextension.common.registry.RegistryItems;
import github.kasuminova.ecoaeextension.common.tile.ecotech.ecalculator.ECalculatorController;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorController;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorPatternBus;
import github.kasuminova.ecoaeextension.common.tile.ecotech.estorage.EStorageController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("MethodMayBeStatic")
public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void preInit() {
        super.preInit();
        RegistryBlocks.registerBlockModels();
        RegistryItems.registerItemModels();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        GuiType type = GuiType.values()[MathHelper.clamp_int(ID, 0, GuiType.values().length - 1)];
        Class<? extends TileEntity> required = type.requiredTileEntity;
        TileEntity present = null;
        if (required != null) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te != null && required.isAssignableFrom(te.getClass())) {
                present = te;
            } else {
                return null;
            }
        }

        switch (type) {
            case ESTORAGE_CONTROLLER:
                return new GuiEStorageController((EStorageController) present, player);
            case EFABRICATOR_CONTROLLER:
                return new GuiEFabricatorController((EFabricatorController) present, player);
            case EFABRICATOR_PATTERN_SEARCH:
                return new GuiEFabricatorPatternSearch((EFabricatorController) present, player);
            case EFABRICATOR_PATTERN_BUS:
                return new GuiEFabricatorPatternBus((EFabricatorPatternBus) present, player);
            case ECALCULATOR_CONTROLLER:
                return new GuiECalculatorController((ECalculatorController) present, player);
        }
        return null;
    }
}
