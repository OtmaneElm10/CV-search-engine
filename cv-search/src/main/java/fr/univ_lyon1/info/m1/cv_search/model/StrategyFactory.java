package fr.univ_lyon1.info.m1.cv_search.model;

/**
 * Class that implements the factory method.
 * This class handles everything related to creating objects and centralizes them,
 * instead of having creation of new objects everywhere in the model/controller
 * "final" -> avoid heritage
 */
public final class StrategyFactory {

    /**
     * contructor used to avoid instantiation.
     */
    private StrategyFactory() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    
    /**
     * Set strategy selected by the user.
     * @param type strategy type
     */
    public static SelectionStrategy createStrategy(final StrategyType type) {
        return switch (type) {
            case ALL_50 -> new AllAboveThresholdStrategy(50);
            case ALL_60 -> new AllAboveThresholdStrategy(60);
            case AVG_50 -> new AverageAboveThresholdStrategy(50);
            case EXPERT_70 -> new ExpertInAnyStrategy(70);

            default -> null; // ou une stratégie par défaut
        };

    }
    
}
