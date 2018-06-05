package test;

import playback.PlaybackManager;

public class PlaybackManagerTest {

	public PlaybackManagerTest() {
		PlaybackManager.getInstance().init();
		PlaybackManager.getInstance().play();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		PlaybackManager.getInstance().stop();
	}
	
	public static void main(String[] args) {
		new PlaybackManagerTest();
	}
}