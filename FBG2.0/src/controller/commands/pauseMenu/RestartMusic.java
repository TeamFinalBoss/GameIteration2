package controller.commands.pauseMenu;

import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import controller.commands.Commandable;

public class RestartMusic implements Commandable {

	@Override
	public void execute() {
		try {
            InputStream in = new FileInputStream("src/resources/sound/menu_funny.wav");
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            System.out.println(e);
        }
	}

}
