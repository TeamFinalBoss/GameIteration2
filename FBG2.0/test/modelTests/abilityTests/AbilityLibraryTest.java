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
 * @author Kyle Kyrazis, Jason Owens
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
        	//We can only EasyMock.function Mocked Objects. You were trying to use them on the TestClass
        	//With the non mocked object we want to actually make real calls using the mocked objects as
        	//dependencies and we set the state of those mocked objects to expect a call to some function
        	//that is can perform.
        	
        	//Set the state
        	//We expect the mocked ability to call the getName Method so we make it return whatever we want.
        	EasyMock.expect(ability1.getName()).andReturn("Test Name").anyTimes();
        	//We expect the mocked entity to call get direction so we make it return what we want.
        	EasyMock.expect(entity1.getDirection()).andReturn(Direction.North).once();
        	//We expect the mocked ability to make a call to performAbility given a direction so 
        	//we call it with the result of entity1.getDirection. Since it doesn't call anything we
        	//expect last call.
        	ability1.performAbility(Direction.North);
        	EasyMock.expectLastCall();
        	
        	// Lock the state
        	EasyMock.replay(ability1);
        	EasyMock.replay(entity1);
        	
        	//Adding the ability to the ability libraries so we can call it
        	testClass.addAbility(ability1);
                
        	//Assert that the outcome of performActiveAbility is true
        	//This is the actual call that we are testing.
        	assertTrue(testClass.performActiveAbility("Test Name",entity1));
        	
        	//Verify the outcome of the mocks so we can make sure that the functions were called as 
        	//We expect.
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
