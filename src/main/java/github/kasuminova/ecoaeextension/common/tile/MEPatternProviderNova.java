package github.kasuminova.ecoaeextension.common.tile;

public interface MEPatternProviderNova {

    /**
     * Check if this pattern provider should ignore parallelism constraints.
     * Used for ECalculator special patterns that don't need parallel processing.
     */
    boolean r$isIgnoreParallel();

    /**
     * Set this pattern provider to ignore parallelism constraints.
     */
    void r$IgnoreParallel();

}
