package controllerTest.sceneControllers;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;

public class SceneChangerTest {

	private SceneObserver obs;
	private SceneChanger changer;
	
	@Before
	public void setUp() {
		obs = EasyMock.createNiceMock(SceneObserver.class);
		changer = SceneChanger.getInstance();
	}
	
	@Test
	public void testRegisterObserver() {
		assertEquals(0,changer.getObservers().size());
		changer.registerObserver(obs);
		assertEquals(1,changer.getObservers().size());
		assertTrue(changer.getObservers().contains(obs));
		changer.unregisterObserver(obs);
	}
	
	@Test
	public void testUnRegisterObserver() {
		assertEquals(0,changer.getObservers().size());
		changer.registerObserver(obs);
		assertEquals(1,changer.getObservers().size());
		assertTrue(changer.getObservers().contains(obs));
		changer.unregisterObserver(obs);
		assertEquals(0,changer.getObservers().size());
		assertFalse(changer.getObservers().contains(obs));
	}
	
	@Test
	public void testChangeScene() {
		obs.update(SceneType.KEY_BINDINGS);
		EasyMock.expectLastCall();
		
		EasyMock.replay(obs);
		
		changer.registerObserver(obs);
		changer.changeScene(SceneType.KEY_BINDINGS);
		changer.unregisterObserver(obs);
		EasyMock.verify(obs);
	}
	
	@Test
	public void testSingleton() {
		assertEquals(SceneChanger.getInstance(),SceneChanger.getInstance());
	}

}
