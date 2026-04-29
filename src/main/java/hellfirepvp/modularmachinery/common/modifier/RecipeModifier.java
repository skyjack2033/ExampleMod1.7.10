package hellfirepvp.modularmachinery.common.modifier;

public class RecipeModifier {

    private final String type;
    private final double value;

    public RecipeModifier(String type, double value) {
        this.type = type;
        this.value = value;
    }

    public static void apply(RecipeModifier... modifiers) {
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }
}
