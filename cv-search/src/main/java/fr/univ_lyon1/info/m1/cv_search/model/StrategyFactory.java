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
     * @param label label of the choosed strategy
     * @return
     */
    public static SelectionStrategy createStrategy(final String label) {
        return switch (label) {
            case "ALL >= 50" -> new AllAboveThresholdStrategy(50);
            case "ALL >= 60" -> new AllAboveThresholdStrategy(60);
            case "AVERAGE >= 50" -> new AverageAboveThresholdStrategy(50);
            case "EXPERT >= 70" -> new ExpertInAnyStrategy(70);

            default -> null; // ou une stratégie par défaut
        };

    }
    
}
