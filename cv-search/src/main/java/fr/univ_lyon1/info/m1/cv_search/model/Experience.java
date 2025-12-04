package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Experience of an applicant.
 */
public class Experience {

    private String entreprise;
    private int start;
    private int end;
    private int duree;
    private List<String> keywords;

    /**
     * Constructor (new instance of Experience).
     * @param entreprise entreprise
     * @param start date of start
     * @param end date of end
     * @param keywords list of what the applicant did or skills he developed
     */
    public Experience(final String entreprise, final int start,
         final int end, final List<String> keywords) {
        this.entreprise = entreprise;
        this.start = start;
        this.end = end;
        this.duree = end - start;
        this.keywords = new ArrayList<>(keywords);
    }


    public String getEntreprise() {
        return entreprise;
    }

    public int getStart() {
        return start;
    }

    public int getFin() {
        return end;
    }

    public int getDuree() {
        return duree;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return entreprise + " : " + start + "-" + end + " (" + duree + " ans)"
            + " | keywords=" + keywords;
    }

    
}
