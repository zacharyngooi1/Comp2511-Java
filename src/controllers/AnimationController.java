package controllers;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.HashMap;

/**
 * A state machine that transitions between animations.
 */
public class AnimationController {
    private ImageView imageView;
    private Animation currentAnimation;
    private HashMap<String, Animation> transitions;

    public AnimationController(ImageView imageView) {
        this.imageView = imageView;
        this.transitions = new HashMap<>();
    }

    public void start(Animation animation) {
        currentAnimation = animation;
        animation.enter();
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public void registerTransition(String transitionName, Animation to) {
        transitions.put(transitionName, to);
    }

    public void transition() {
        transition(currentAnimation.next);
    }

    public void transition(String transitionName) {
        transition(transitions.get(transitionName));
    }

    private void transition(Animation next) {
        if (next == null) {
            return;
        }

        currentAnimation.exit();
        currentAnimation = next;
        next.enter();
    }
}
