package hellfirepvp.modularmachinery.common.crafting;

public class ActiveMachineRecipe {

    private String recipeId;
    private int tick;
    private int totalTick;

    public ActiveMachineRecipe() {
    }

    public ActiveMachineRecipe(String recipeId, int totalTick) {
        this.recipeId = recipeId;
        this.totalTick = totalTick;
        this.tick = 0;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public int getTotalTick() {
        return totalTick;
    }

    public void setTotalTick(int totalTick) {
        this.totalTick = totalTick;
    }
}
