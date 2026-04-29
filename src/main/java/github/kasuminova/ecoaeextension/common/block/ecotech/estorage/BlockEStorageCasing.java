package github.kasuminova.ecoaeextension.common.block.ecotech.estorage;

import github.kasuminova.ecoaeextension.ECOAEExtension;

public class BlockEStorageCasing extends BlockEStorage {

    public static final BlockEStorageCasing INSTANCE = new BlockEStorageCasing();

    protected BlockEStorageCasing() {
        this.setDefaultState(this.stateContainer.getBaseState());
        this.setBlockName(ECOAEExtension.MOD_ID + '.' + "estorage_casing");
    }

}
