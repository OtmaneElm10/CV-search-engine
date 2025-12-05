package fr.univ_lyon1.info.m1.cv_search.model;

/**
 * Enum of shorting type .
 */
public enum SortType {

    NONE("Aucun"),
    EXPERIENCE_DESC("Années d'expérience (décroissant)");

    private final String label;

    SortType(final String label) {
        this.label = label;
    }

    /**
     * Get label.
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Find the shorttype from the lable.
     * @param text string from combobox
     * @return the coresponding shorttype or NONE
     */
    public static SortType fromLabel(final String text) {

        for (SortType t : values()) {
            if (t.label.equals(text)) {
                return t;
            }
        }
        return NONE;
    }

}
