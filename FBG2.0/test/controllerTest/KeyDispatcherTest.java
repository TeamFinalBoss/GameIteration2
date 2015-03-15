package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import controller.KeyDispatcher;
import controller.sceneControllers.SceneController;
import controller.sceneControllers.SceneType;

public class KeyDispatcherTest {

	private KeyDispatcher keyDisp;
	private SceneController sceneController1;
	private SceneController sceneController2;
	
	
	@Before
	public void setUp() throws Exception {
		sceneController1 = EasyMock.createNiceMock(SceneController.class);
		sceneController2 = EasyMock.createNiceMock(SceneController.class);
		
		Map<SceneType, SceneController> map = new HashMap<>();
		map.put(SceneType.MAIN_MENU, sceneController1);
		map.put(SceneType.PAUSE_MENU, sceneController2);
		
		keyDisp = new KeyDispatcher(map,sceneController1);
	}

	@Test
	public void testUseKey() {
		sceneController1.useKey(2);
		EasyMock.expectLastCall();
		
		EasyMock.replay(sceneController1);
		
		keyDisp.useKey(2);
		
		EasyMock.verify(sceneController1);
	}
	
	@Test
	public void testUseKeyWithDifferentController() {
		sceneController2.useKey(2);
		EasyMock.expectLastCall();
		
		EasyMock.replay(sceneController2);
		
		keyDisp.setActiveController(sceneController2);
		keyDisp.useKey(2);
		
		EasyMock.verify(sceneController2);
	}
	
	@Test
	public void testUpdateController() {
		sceneController2.useKey(2);
		EasyMock.expectLastCall();
		
		EasyMock.replay(sceneController2);
		
		keyDisp.update(SceneType.PAUSE_MENU);
		keyDisp.useKey(2);
		
		assertEquals(sceneController2, keyDisp.getActiveController());
		
		EasyMock.verify(sceneController2);
	}
	
	@Test
	public void testUpdateControllerTwice() {
		sceneController2.useKey(2);
		EasyMock.expectLastCall();
		
		sceneController1.useKey(2);
		EasyMock.expectLastCall();
		
		EasyMock.replay(sceneController2);
		EasyMock.replay(sceneController1);
		
		keyDisp.update(SceneType.PAUSE_MENU);
		keyDisp.useKey(2);
		keyDisp.update(SceneType.MAIN_MENU);
		keyDisp.useKey(2);
		
		assertEquals(sceneController1, keyDisp.getActiveController());
		
		EasyMock.verify(sceneController2);
		EasyMock.verify(sceneController1);
	}
	

}
