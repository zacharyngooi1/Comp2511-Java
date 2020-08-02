package unsw.dungeon;

import javafx.scene.image.Image;

public class AnimationStatic extends Animation {
    private Image image;

    /**
     * @param duration in ms.
     */
    public AnimationStatic(AnimationController animationController, long duration, Image image) {
        this.animationController = animationController;
        this.duration = duration;
        this.image = image;
    }

    @Override
    public void enter() {
        animationController.setImage(image);

        try {
            wait(duration);
        } catch (Exception e) {
            System.out.println(e);
        }

        animationController.transition();
    }

    @Override
    public void exit() {
        return;
    }
}
