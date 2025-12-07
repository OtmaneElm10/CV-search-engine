package fr.univ_lyon1.info.m1.cv_search;


import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.SortByAverageDesc;
import fr.univ_lyon1.info.m1.cv_search.model.SortByExperienceDesc;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

/**
 * Tests class for sorting strategies.
 */
public class SortStrategyFactoryTest {


    /**
     * test of sorting by experience.
     */
    @Test
    public void testSortByExperience() {

        //Given
        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");
        Applicant a = builder.build();

        ApplicantBuilder builder1 = new ApplicantBuilder("applicant2.yaml");
        Applicant b = builder1.build();

        ApplicantBuilder builder2 = new ApplicantBuilder("applicant3.yaml");
        Applicant c = builder2.build();

        //random list to test the sorting (goal is [b,a,c] )
        List<Applicant> randomList = Arrays.asList(c, b, a);

        //When
        SortByExperienceDesc sorter = new SortByExperienceDesc();
        List<Applicant> result = sorter.sort(randomList);

        assertThat(result, hasItems(a, b, c));

        //the first one should be b and check name
        assertThat("1", result.get(0), is(b));
        assertThat("2", result.get(0).getName(), is("Foo Bar"));

        //The second one should be a
        assertThat("3", result.get(1), is(a));
        assertThat("4", result.get(1).getName(), is("John Smith"));

        //the third one should be c
        assertThat("5", result.get(2), is(c));
        assertThat("6", result.get(2).getName(), is("Alice Durand"));


    }

    /**
     * Tests of sorting by grade (desc).
     */
    @Test
    public void testSortByGradeDesc() {

        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");
        Applicant a = builder.build();

        ApplicantBuilder builder1 = new ApplicantBuilder("applicant2.yaml");
        Applicant b = builder1.build();

        ApplicantBuilder builder2 = new ApplicantBuilder("applicant3.yaml");
        Applicant c = builder2.build();

        List<Applicant> randomList = Arrays.asList(c, b, a);
        List<Applicant> randomList2 = Arrays.asList(c, b, a);

        // --- WHEN ---
        // sort with Java goal ([c,b,a])
        List<String> skill = Arrays.asList("Java");
        SortByAverageDesc sorter = new SortByAverageDesc(skill);

        //sort with c and java goal ([a,b,c])
        //rmq c doesn't have c skill
        List<String> skill2 = Arrays.asList("c", "java");
        SortByAverageDesc sorter2 = new SortByAverageDesc(skill2);

        //Then
        List<Applicant> result = sorter.sort(randomList);
        assertThat("0", result, hasSize(3));
        assertThat("1", result.get(0), is(c));
        assertThat("2", result.get(1), is(b));
        assertThat("3", result.get(2), is(a));

        List<Applicant> result2 = sorter2.sort(randomList2);
        assertThat("4", result2, hasSize(3));
        assertThat("5", result2.get(0), is(a));
        assertThat("6", result2.get(1), is(b));
        assertThat("7", result2.get(2), is(c));




    }


}
