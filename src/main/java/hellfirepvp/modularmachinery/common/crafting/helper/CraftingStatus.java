package hellfirepvp.modularmachinery.common.crafting.helper;

public enum CraftingStatus {

    SUCCESS,
    FAILURE_MISSING_INPUT,
    FAILURE_RECIPE_FAILED,
    WORKING,
    IDLE;

    public boolean isSuccess() {
        return this == SUCCESS;
    }

    public boolean isFailure() {
        return this == FAILURE_MISSING_INPUT || this == FAILURE_RECIPE_FAILED;
    }

    public boolean isWorking() {
        return this == WORKING;
    }

    public boolean isIdle() {
        return this == IDLE;
    }
}
