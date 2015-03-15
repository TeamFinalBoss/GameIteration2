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
            testClass.addAbility(ability1);
            EasyMock.expectLastCall();
            
            
            EasyMock.replay(testClass);
            EasyMock.verify(testClass);
	}
	
        @Test
        public void testHasAbility(){
            EasyMock.expect(testClass.hasAbility(ability1.getName())).andReturn(false);
            testClass.addAbility(ability1);
            EasyMock.expectLastCall();
            
            EasyMock.expect(testClass.hasAbility(ability1.getName())).andReturn(true);
            EasyMock.verify(testClass);
        }
        
        @Test
        public void testForgetAbility(){
            EasyMock.expect(testClass.forgetAbility(ability1.getName())).andReturn(false);
            testClass.addAbility(ability1);
            EasyMock.expectLastCall();
            
            EasyMock.expect(testClass.hasAbility(ability1.getName())).andReturn(true);
            
            EasyMock.expect(testClass.forgetAbility(ability1.getName())).andReturn(true);
            EasyMock.verify(testClass);
            EasyMock.expect(testClass.hasAbility(ability1.getName())).andReturn(false);
        }
        
        @Test
        public void testPerformAbility(){
            
            EasyMock.expect(testClass.hasAbility(ability1.getName())).andReturn(false);
            testClass.addAbility(ability1);
            EasyMock.expectLastCall();
            
            EasyMock.expect(testClass.performActiveAbility(ability1.getName(), entity1)).andReturn(true);
            EasyMock.verify(testClass);
        
        }
       
}
