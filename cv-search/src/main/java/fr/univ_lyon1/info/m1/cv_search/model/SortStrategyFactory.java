package fr.univ_lyon1.info.m1.cv_search.model;

/**
 * Factory to create the short strategy.
 */

public final class SortStrategyFactory {



    /**
     * contructor used to avoid instantiation.
     */
    private SortStrategyFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Create a strategy based on the type.
     * @param type is the type of short choosed
     * @return the strategy or null if nothing has been choose
     */
    public static SortStrategy createSortStrategy(final SortType type) {
        switch (type) {
            case EXPERIENCE_DESC:
                return new SortByExperienceDesc();
            // case NAME_ASC: return new SortByName();

            case NONE:
            default:
                return null;
        }
    }



}
