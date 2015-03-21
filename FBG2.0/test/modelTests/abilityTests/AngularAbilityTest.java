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
    public void inRangeTestReturnsTrue();
    {
        testClass.setRadius(5);
    }

    @Test
    public void inRangeTestReturnsFalse();
    {
        // radius = 0;
        testClass.setRadius(0);
        assertFalse(testClass.inRange());

        testClass.testClass();
        testClass.setDegree(0);
        assertFalse(testClass.inRange());

        testClass.testClass();

        testClass.setRadius(5);
    }
}
