package com.redsponge.platformer;

import com.redsponge.redutils.console.ConsoleMSG;

public class Launcher {
	
	public static final int WIDTH = (int) (640*1.5), HEIGHT = (int) (480*1.2);
	public static final String TITLE = "Platformer!";
	public static boolean SHOWFPS = false;
	
	public static void main(String[] args) {
		ConsoleMSG.ADD.info("STARTING GAME!");
		try {
			Platformer p = new Platformer(TITLE, WIDTH, HEIGHT);
			p.start();
			ConsoleMSG.ADD.info("GAME STARTED SUCCESSFULLY!");
		} catch(Exception e) {
			ConsoleMSG.FATAL.info("COULDN'T START GAME!");
		}
	}
	
}
