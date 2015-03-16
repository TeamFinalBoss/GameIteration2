package controllerTest.sceneControllers;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import controller.keyBindings.KeyOptions;
import controller.sceneControllers.SceneController;

public class SceneControllerTest {
	
	private KeyOptions keyOptions;
	private SceneController sceneController;

	@Before
	public void setUp() throws Exception {
		keyOptions = EasyMock.createNiceMock(KeyOptions.class);
		sceneController = new SceneController(keyOptions);
	}

	@Test
	public void testUseKey() {
		keyOptions.useKey(2);
		EasyMock.expectLastCall();
		
		EasyMock.replay(keyOptions);
		
		sceneController.useKey(2);
		
		EasyMock.verify(keyOptions);
	}
	
	@Test
	public void testUseMultipleKeys() {
		keyOptions.useKey(2);
		EasyMock.expectLastCall();
		keyOptions.useKey(4);
		EasyMock.expectLastCall();
		
		EasyMock.replay(keyOptions);
		
		sceneController.useKey(2);
		sceneController.useKey(4);
		
		EasyMock.verify(keyOptions);
	}

}
