package fr.univ_lyon1.info.m1.cv_search.model;


/**
 * Type of strategy. Used to avoid magic string everywhere, and to have a solid code.
 */
public enum StrategyType {
    ALL_50("ALL >= 50"),
    ALL_60("ALL >= 60"),
    AVG_50("AVERAGE >= 50"),
    EXPERT_70("EXPERT >= 70");

    private final String label;

    StrategyType(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() { 
        return label; // what we want to show in the comboBox
    }

    
}
