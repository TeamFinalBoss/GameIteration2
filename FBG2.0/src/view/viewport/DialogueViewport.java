package view.viewport;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;
import model.director.ActiveMapManager;
import model.director.AvatarInteractionManager;
import model.director.GameDirector;
import model.entity.NPC;

public class DialogueViewport implements ViewPort, Observer, SceneObserver {

	private String[] options;
	private SceneType type;
	private static int xStart;
	private static int yStart;
	private static int padding = 1;
	
	
	public DialogueViewport() {
		xStart = (int)(GameDirector.getSize().width * .8);
		yStart = 0;
		SceneChanger.getInstance().registerObserver(this);
	}
	
	@Override
	public void draw(Graphics g) {
		if(type.equals(SceneType.DIALOGUE)) {
		NPC npc = AvatarInteractionManager.getInstance().getConversationPartner();
		if(npc != null) {
			options = new String[npc.getDialogueMessage().length()];
			npc.getDialogueOptions().toArray(options);
		}
	
		if(options != null) {
			int height = g.getFontMetrics().getHeight();
			g.setColor(Color.BLACK);
			g.fillRect(xStart, yStart, (int)(GameDirector.getSize().getWidth() * 0.2), (options.length * (height + padding) + 100));
			g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN,15));
			g.setColor(Color.WHITE);
			for(int i = 0; i < options.length; i++) {
				height = g.getFontMetrics().getHeight();
				if(options[i] != null)
				g.drawString(options[i], xStart, yStart + (i * (padding + height) + 15));
			}
		}
		}
	

	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SceneType type) {
		this.type = type;
		
	}

}
