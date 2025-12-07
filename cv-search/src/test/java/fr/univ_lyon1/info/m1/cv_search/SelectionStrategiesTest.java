package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.AverageAboveThresholdStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.ExpertInAnyStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.AllAboveThresholdStrategy;

import org.junit.jupiter.api.Test;
import java.util.Arrays;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests class for the selection strategies.
 */
public class SelectionStrategiesTest {

    /**
     * Test of Expert in any strategy.
     */
    @Test
    public void testExpertInAnyIsSelected() {

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

    /**
     * Getter to test the get label.
     */
    @Test
    public void testExpertInAnyLabel() {
        ExpertInAnyStrategy strategy = new ExpertInAnyStrategy(55);
        assertThat(strategy.getLabel(), is("EXPERT >= 55"));
    }


    /**
     * Tests of average above X.
     */
    @Test
    public void testAverageAboveThresholdCalculation() {

        //Given
        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");
        Applicant a = builder.build();

        //When and Then

        AverageAboveThresholdStrategy strategyLow = new AverageAboveThresholdStrategy(40);
        assertThat("Moyenne au dessus de 40",
                strategyLow.isSelected(a, Arrays.asList("c")), is(true));

        AverageAboveThresholdStrategy strategytooHigh = new AverageAboveThresholdStrategy(100);
        assertThat("Moyenne au dessus de 100(impossible)",
                strategytooHigh.isSelected(a, Arrays.asList("c")), is(false));
        assertThat(strategytooHigh.isSelected(a, Arrays.asList("c", "c++", "java")), is(false));

        AverageAboveThresholdStrategy strategymidhigh = new AverageAboveThresholdStrategy(80);
        assertThat("Moyenne au dessus de 80",
                strategymidhigh.isSelected(a, Arrays.asList("java", "c++")), is(false));
        assertThat(strategymidhigh.isSelected(a, Arrays.asList("c")), is(true));

        AverageAboveThresholdStrategy strategynull = new AverageAboveThresholdStrategy(0);
        assertThat("Moyenne au dessus de 0",
                strategynull.isSelected(a, Arrays.asList()), is(false));
        assertThat(strategymidhigh.isSelected(a, Arrays.asList("c")), is(true));

        AverageAboveThresholdStrategy strategyexact = new AverageAboveThresholdStrategy(90);
        assertThat("Moyenne exact",
                strategyexact.isSelected(a, Arrays.asList("c")), is(true));




    }


    /**
     * Tests for all above X and exception included.
     */
    @Test
    public void testAllAboveThresholdCalculation() {
        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");
        Applicant a = builder.build();

        AllAboveThresholdStrategy strategy40 = new AllAboveThresholdStrategy(40);

        assertThat("1", strategy40.isSelected(a, Arrays.asList("java")), is(true));
        assertThat("2", strategy40.isSelected(a, Arrays.asList("python")), is(false));

        AllAboveThresholdStrategy strategy70 = new AllAboveThresholdStrategy(70);
        assertThat("3", strategy70.isSelected(a, Arrays.asList("c")), is(true));
        assertThat("4", strategy70.isSelected(a, Arrays.asList("c++", "c")), is(true));
        assertThat("5", strategy70.isSelected(a, Arrays.asList("c", "c++", "java")), is(false));

        AllAboveThresholdStrategy strategy0 = new AllAboveThresholdStrategy(0);
        assertThat("6", strategy0.isSelected(a, Arrays.asList("c", "c++", "java")), is(true));
        assertThat("7", strategy0.isSelected(a, Arrays.asList()), is(true));

        AllAboveThresholdStrategy strategy10 = new AllAboveThresholdStrategy(10);
        assertThat("8", strategy10.isSelected(a, Arrays.asList()), is(true));





    }



}
