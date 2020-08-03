package controllers;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class PauseController {
    
    private DungeonScreen dungeonScreen;

    private StartScreen startScreen; 

    private PauseScreen pauseScreen;

    
    public void setDungeonScreen(DungeonScreen dungeonscreen) {
        this.dungeonScreen = dungeonscreen;
    }

    public void setStartScreen(StartScreen screen) {
        this.startScreen = screen;
    }

    public void setPauseScreen(PauseScreen pausescreen) {
        this.pauseScreen = pausescreen;
    }

}
