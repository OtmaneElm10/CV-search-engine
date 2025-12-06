package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.model.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SortStrategyFactoryTest {

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
        List<Applicant> randomList = Arrays.asList(c,b,a);

        //When
        SortByExperienceDesc sorter = new SortByExperienceDesc();
        List<Applicant> result = sorter.sort(randomList);

        assertThat(result, is(not(empty())));
        assertThat(result, hasItems(a,b,c));

        //the first one should be b and check name
        assertThat("1",result.get(0), is(b));
        assertThat("2",result.get(0).getName(), is("Foo Bar"));

        //The second one should be a
        assertThat("3",result.get(1), is(a));
        assertThat("4",result.get(1).getName(), is("John Smith"));

        //the third one should be c
        assertThat("5",result.get(2), is(c));
        assertThat("6",result.get(2).getName(), is("Alice Durand"));


    }


}
