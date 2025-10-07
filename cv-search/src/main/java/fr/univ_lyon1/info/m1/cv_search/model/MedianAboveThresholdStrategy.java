package fr.univ_lyon1.info.m1.cv_search.model;
import java.util.List;

/**
 * Strategy use to know who is above the median
 */

public class MedianAboveThresholdStrategy implements SelectionStrategy{

    /**
     * @param threshold represent the median
     */
    private int threshold;


    public MedianAboveThresholdStrategy(final int threshold) {
        this.threshold = threshold;
    }

}