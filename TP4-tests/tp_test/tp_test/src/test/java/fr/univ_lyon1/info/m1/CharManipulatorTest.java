package fr.univ_lyon1.info.m1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CharManipulatorTest {

    private CharManipulator manipulator;

    @BeforeEach
    public void setUp() {
        manipulator = new CharManipulator();
    }

    @Test
    public void orderNormalString() {
        assertEquals("A", manipulator.invertOrder("A"));
        assertEquals("DCBA", manipulator.invertOrder("ABCD"));
        assertEquals("321DCBA", manipulator.invertOrder("ABCD123"));
    }

    @Test
    public void orderEmptyString()
    {

        assertThat("", is(""));
    }

}