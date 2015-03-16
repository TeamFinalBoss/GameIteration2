package exampleTest.actualTests;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import exampleTest.DependencyInterface;
import exampleTest.MainTestingClass;

/**
 * @author Kyle Kyrazis
 * 
 * This should be a good enough example for you guys to write tests. If it's not then
 * you can ask me, but you'll owe me a burrito.
 *
 */
@RunWith(EasyMockRunner.class)
public class MainTestingClassTest {

	private DependencyInterface face;
	private MainTestingClass testingClass;
	
	@Before
	public void setUp() throws Exception {
		face = EasyMock.createNiceMock(DependencyInterface.class);
		testingClass = new MainTestingClass();
		testingClass.setFace(face);
	}

	@Test
	public void testForwardARequest() {
		face.doSomething();
		EasyMock.expectLastCall();
		
		EasyMock.replay(face);
		
		testingClass.forwardARequest();
		
		EasyMock.verify(face);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsAnException() {
		testingClass.thisWillThrowAnException();
	}
	
	@Test
	public void testTheAlgorithm() {
		assertEquals(26,testingClass.thisIsSomeAlgorithm(5));
	}
	
	@Test
	public void testTheAlgorithmWithWrongExpectResultShouldFail() {
		assertEquals(21,testingClass.thisIsSomeAlgorithm(5));
	}
	
	@Test
	public void testPerformingLogicWithDependencyShouldJustCallTheForwardedRequest() {
		EasyMock.expect(face.returnSomething()).andReturn(7).anyTimes();
		face.doSomething();
		EasyMock.expectLastCall();
		
		EasyMock.replay(face);
		
		testingClass.performSomeLogicForState();
		
		assertFalse(testingClass.getState());
		
		EasyMock.verify(face);
	}
	
	@Test
	public void testPerformingLogicWithDependencyTakingTruePathOnceStateStillFalse() {
		EasyMock.expect(face.returnSomething()).andReturn(4).anyTimes();
		
		EasyMock.replay(face);
		
		testingClass.performSomeLogicForState();
		
		assertFalse(testingClass.getState());
		
		EasyMock.verify(face);
	}
	
	@Test
	public void testPerformingLogicWithDependencyTakingTruePathAllTimesStateTrue() {
		EasyMock.expect(face.returnSomething()).andReturn(4).once();
		EasyMock.expect(face.returnSomething()).andReturn(10).once();
		
		EasyMock.replay(face);
		
		testingClass.performSomeLogicForState();
		
		assertTrue(testingClass.getState());
		
		EasyMock.verify(face);
	}
	
	

}
