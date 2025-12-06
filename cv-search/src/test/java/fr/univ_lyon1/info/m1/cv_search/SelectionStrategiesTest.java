package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.ExpertInAnyStrategy;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SelectionStrategiesTest {

    // Test of Expert in any strategy

    @Test
    public void testExpertInAny_IsSelected () {

        //Given
        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");
        Applicant a = builder.build();


        ExpertInAnyStrategy strategy = new ExpertInAnyStrategy(80);

        //When and Then

        assertThat("Java ne doit pas être sélectionné",
                strategy.isSelected(a, Arrays.asList("java")), is(false));

        assertThat("C++ ne doit pas être sélectionné",
                strategy.isSelected(a, Arrays.asList("c++")), is(false));

        assertThat("C doit être sélectionné",
                strategy.isSelected(a, Arrays.asList("c")), is(true));


        assertThat("Doit être sélectionné s'il est expert dans au moins un des skills",
                strategy.isSelected(a, Arrays.asList("java", "python")), is(false));

        assertThat("Doit être sélectionné s'il est expert dans au moins un des skills",
                strategy.isSelected(a, Arrays.asList("c", "python")), is(true));


        assertThat("Aucun skill au niveau expert",
                strategy.isSelected(a, Arrays.asList("python", "c++")), is(false));


    }

    @Test
    public void testExpertInAny_Label() {
        ExpertInAnyStrategy strategy = new ExpertInAnyStrategy(55);
        assertThat(strategy.getLabel(), is("EXPERT >= 55"));
    }
}
