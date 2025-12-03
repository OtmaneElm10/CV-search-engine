package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Experience of an applicant.
 */
public class Experience {

    private String entreprise;
    private int start;
    private int fin;
    private int duree;
    private List<String> keywords;

    /**
     * Constructor (new instance of Experience).
     * @param entreprise entreprise
     * @param start date of start
     * @param fin date of end
     * @param duree duration
     * @param keywords list of what the applicant did or skills he developed
     */
    public Experience(final String entreprise, final int start,
         final int fin, final int duree, final List<String> keywords) {
        this.entreprise = entreprise;
        this.start = start;
        this.fin = fin;
        this.duree = duree;
        this.keywords = new ArrayList<>(keywords);
    }


    public String getEntreprise() {
        return entreprise;
    }

    public int getStart() {
        return start;
    }

    public int getFin() {
        return fin;
    }

    public int getDuree() {
        return duree;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    
    
}
