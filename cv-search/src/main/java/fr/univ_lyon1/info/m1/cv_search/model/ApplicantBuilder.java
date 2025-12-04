package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;


import org.yaml.snakeyaml.Yaml;

/**
 * Builder reading a Yaml file to build an Applicant object.
 */
public class ApplicantBuilder {

    private File file;

    /**
     * @param f Yaml file describing the applicant.
     */
    public ApplicantBuilder(final File f) {
        this.file = f;
    }

    /**
     * @param filename Name of the Yaml file describing the applicant.
     */
    public ApplicantBuilder(final String filename) {
        this.file = new File(filename);
    }

    /**
     * Build the applicant from the Yaml file provided to the constructor.
     */
    public Applicant build() {
        Applicant a = new Applicant();
        Yaml yaml = new Yaml();
        Map<String, Object> map;
        try {
            map = yaml.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Error(e);
        }

        a.setName((String) map.get("name"));

        // Cast may fail if the Yaml is incorrect. Ideally we should provide
        // clean error messages.
        @SuppressWarnings("unchecked")
        Map<String, Integer> skills = (Map<String, Integer>) map.get("skills");
        Map<String, Object> experience = (Map<String, Object>) map.get("experience");

        for (String skill : skills.keySet()) {
            Integer value = skills.get(skill);
            a.setSkill(skill, value);
        }

        for (String entreprise : experience.keySet()) {
            Map<String, Object> info = (Map<String, Object>) experience.get(entreprise);

            int  start = (int) info.get("start");
            int end = (int) info.get("end");
            List<String> keywords = (List<String>) info.get("keywords");

            Experience e = new Experience(entreprise, start, end, keywords);
            a.addExperience(e);


        }


        System.out.println("liste experience : " + a.getExperience());
        return a;
    }
}
