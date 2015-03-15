package modelTests.abilityTests;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import exampleTest.DependencyInterface;
import exampleTest.MainTestingClass;
import model.ability.Ability;
import model.ability.AbilityLibrary;
import model.entity.Entity;
import model.map.Direction;



/**
 * @author Kyle Kyrazis
 * 
 * This should be a good enough example for you guys to write tests. If it's not then
 * you can ask me, but you'll owe me a burrito.
 *
 */
public class AbilityLibraryTest {

	private Ability ability1;
	private AbilityLibrary testClass;
	private Entity entity1;
        
	@Before
	public void setUp() throws Exception {
            testClass= new AbilityLibrary();
            ability1 = EasyMock.createNiceMock(Ability.class);
            entity1 = EasyMock.createNiceMock(Entity.class);
	}

	@Test
	public void testAddAbility() {
		assertEquals(0,testClass.getAbilities().size());
        testClass.addAbility(ability1);
        assertEquals(1,testClass.getAbilities().size());
        assertTrue(testClass.getAbilities().contains(ability1));
	}
	
        @Test
        public void testHasAbility(){
        	EasyMock.expect(ability1.getName()).andReturn("Test Name").anyTimes();
            
        	EasyMock.replay(ability1);
        	
        	testClass.addAbility(ability1);
        	assertTrue(testClass.hasAbility("Test Name"));

            EasyMock.verify(ability1);
        }
        
        @Test
        public void testHasAbilityReturnsFalse(){
        	EasyMock.expect(ability1.getName()).andReturn("Test Name").anyTimes();
            
        	EasyMock.replay(ability1);
     
        	assertFalse(testClass.hasAbility("Test Name"));

            EasyMock.verify(ability1);
        }
        
        @Test
        public void testForgetAbility(){
        	EasyMock.expect(ability1.getName()).andReturn("Test Name").anyTimes();
        	
        	EasyMock.replay(ability1);
        	
        	assertEquals(0,testClass.getAbilities().size());
        	testClass.addAbility(ability1);
        	assertTrue(testClass.forgetAbility("Test Name"));
        	assertEquals(0, testClass.getAbilities().size());
        	
        	EasyMock.verify(ability1);
        }
        
        @Test
        public void testForgetAbilityReturnsFalse(){
        	EasyMock.expect(ability1.getName()).andReturn("Test Name").anyTimes();
        	
        	EasyMock.replay(ability1);
        	
        	assertEquals(0, testClass.getAbilities().size());
        	testClass.addAbility(ability1);
        	assertFalse(testClass.forgetAbility("Magic"));
        	assertEquals(1, testClass.getAbilities().size());
        	
        	EasyMock.verify(ability1);
        }
        
        @Test
        public void testPerformAbility(){
        	//Set the state
        	//We expect the ability1 to call 
        	EasyMock.expect(ability1.getName()).andReturn("Test Name").anyTimes();
        	EasyMock.expect(entity1.getDirection()).andReturn(Direction.North).once();
        	ability1.performAbility(Direction.North);
        	EasyMock.expectLastCall();
        	
        	// Lock the state
        	EasyMock.replay(ability1);
        	EasyMock.replay(entity1);
        	
        	//Actual calls
        	testClass.addAbility(ability1);
        	//Assert on the outcome
        	assertTrue(testClass.performActiveAbility("Test Name",entity1));
        	
        	//Verify the state of the mocks
        	EasyMock.verify(entity1);
        	EasyMock.verify(ability1);
        }
        
        @Test
        public void testPerformAbilityFalse(){
        	
        	EasyMock.expect(ability1.getName()).andReturn("Test Name").anyTimes();
        	EasyMock.expectLastCall();
        	EasyMock.replay(ability1);
        	
        	testClass.addAbility(ability1);
        	assertFalse(testClass.performActiveAbility("Magic",entity1));
        	
        	EasyMock.verify(ability1);
        }
       
}
