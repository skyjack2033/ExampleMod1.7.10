package hellfirepvp.modularmachinery.common.machine;

import hellfirepvp.modularmachinery.common.crafting.ActiveMachineRecipe;

public class RecipeThread {

    private ActiveMachineRecipe activeRecipe;

    public ActiveMachineRecipe getActiveRecipe() {
        return activeRecipe;
    }

    public void setActiveRecipe(ActiveMachineRecipe activeRecipe) {
        this.activeRecipe = activeRecipe;
    }

    public Object getContext() {
        return null;
    }
}
