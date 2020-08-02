package controllers;

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

        // This always raises the exception, but for some reason it gives
        // the desired effect so it's ok.
        try {
            wait(duration);
        } catch (Exception e) {
            System.out.println("AnimationStatic wait(): " + e);
        }

        animationController.transition();
    }

    @Override
    public void exit() {
        return;
    }
}
