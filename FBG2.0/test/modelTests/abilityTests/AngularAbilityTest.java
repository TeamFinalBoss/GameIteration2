package modelTests.abilityTests;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import exampleTest.DependencyInterface;
import exampleTest.MainTestingClass;
import model.entity.Entity;
import model.entity.ability.Ability;
import model.entity.ability.AbilityLibrary;
import model.map.Direction;

/**
 * @author Aaron Iglesias
 * 
 * Tests AngularAbility.java
 *
 */
public class AngularAbilityTest 
{
    private AngularAbility testClass;
    private Entity caster;
    private Entity entity;

    @Before 
    public void setUp() 
    { 
        testClass = new AngularAbility(); 
        caster = EasyMock.createNiceMock(Entity.class);
        entity = EasyMock.createNiceMock(Entity.class);
    } 

    @Test
    public void getDegreeTest()
    {
        // test default
        assertEquals(90, testClass.getDegree());

        // test adjustment
        testClass.setDegree(45);
        assertEquals(45, testClass.getDegree());
    }

    @Test
    public void getRadiusTest()
    {
        // test default
        assertEquals(1, testClass.getRadius());

        // test adjustment
        testClass.setDegree(5);
        assertEquals(5, testClass.getRadius());
    }

    @Test
    public void setDegreeTest()
    {
        // test default
        assertEquals(90, testClass.getDegree());

        // test adjustment
        testClass.setDegree(45);
        assertEquals(45, testClass.getDegree());
    }

    @Test
    public void setRadiusTest()
    {
        // test default
        assertEquals(1, testClass.getRadius());

        // test adjustment
        testClass.setDegree(5);
        assertEquals(5, testClass.getRadius());
    }

    @Test
    public void inRangeTestReturnsTrue90();
    {
        /*
        caster facing North
        */
        // degree == 90
        testClass.setDegree(90);
    }

    @Test
    public void inRangeTestReturnsFalse();
    {
        // radius = 0;
        testClass.setRadius(0);
        assertFalse(testClass.inRange());

        // degree == 90
        testClass.testClass();
        testClass.setDegree(0);
        assertFalse(testClass.inRange());

        /*
        caster facing North
        */
        // degree == 90
        testClass.setDegree(90);

        // degree == 180
        testClass.setDegree(180);

        // degree == 270
        testClass.setDegree(270);

        // degree == 360
        testClass.setDegree(360);

        /*
        caster facing NorthWest
        */
        // degree == 90
        testClass.setDegree(90);

        // degree == 180
        testClass.setDegree(180);

        // degree == 270
        testClass.setDegree(270);

        // degree == 360
        testClass.setDegree(360);

        /*
        caster facing West
        */
        // degree == 90
        testClass.setDegree(90);

        // degree == 180
        testClass.setDegree(180);

        // degree == 270
        testClass.setDegree(270);

        // degree == 360
        testClass.setDegree(360);

        /*
        caster facing SouthWest
        */
        // degree == 90
        testClass.setDegree(90);

        // degree == 180
        testClass.setDegree(180);

        // degree == 270
        testClass.setDegree(270);

        // degree == 360
        testClass.setDegree(360);

        /*
        caster facing South
        */
        // degree == 90
        testClass.setDegree(90);

        // degree == 180
        testClass.setDegree(180);

        // degree == 270
        testClass.setDegree(270);

        // degree == 360
        testClass.setDegree(360);

        /*
        caster facing SouthEast
        */
        // degree == 90
        testClass.setDegree(90);

        // degree == 180
        testClass.setDegree(180);

        // degree == 270
        testClass.setDegree(270);

        // degree == 360
        testClass.setDegree(360);

        /*
        caster facing East
        */
        // degree == 90
        testClass.setDegree(90);

        // degree == 180
        testClass.setDegree(180);

        // degree == 270
        testClass.setDegree(270);

        // degree == 360
        testClass.setDegree(360);

        /*
        caster facing NorthEast
        */
        // degree == 90
        testClass.setDegree(90);

        // degree == 180
        testClass.setDegree(180);

        // degree == 270
        testClass.setDegree(270);

        // degree == 360
        testClass.setDegree(360);
    }
}