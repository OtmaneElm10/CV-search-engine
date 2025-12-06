package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.model.Experience;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExperienceTest {

    @Test
    public void test() {

        //Given
        List<String> keywords = Arrays.asList("Java", "SQL");

        //When
        Experience exp = new Experience("Google", 2015, 2020, keywords);

        // Then
        assertThat(exp.getEntreprise(), is("Google"));
        assertThat(exp.getStart(), is(2015));
        assertThat(exp.getFin(), is(2020));

        //check list
        assertThat(exp.getKeywords(), hasSize(2));
        assertThat(exp.getKeywords(), contains("Java", "SQL"));

    }

    @Test
    public void testDurationCalculation() {

        //Given and When
        Experience exp = new Experience("Startup", 2018, 2022, new ArrayList<>());

        //Then
        assertThat(exp.getDuree(), is(4));
    }

    @Test
    public void testToString () {

        //Given
        List<String> keywords = Arrays.asList("Java", "SQL");

        Experience exp = new Experience("Google", 2015, 2020, keywords);
        assertThat(exp.toString(), is("Google : 2015-2020 (5 ans) | keywords=[Java, SQL]"));

    }
}

