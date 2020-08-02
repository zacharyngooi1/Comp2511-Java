package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class AnimationCycle extends Animation {
    private List<Image> images;
    private int currentImageIdx;
    private Timeline timeline;

    /**
     * @param duration in ms.
     */
    public AnimationCycle(AnimationController animationController, long duration, List<Image> images) {
        this.animationController = animationController;
        this.duration = duration;
        this.images = new ArrayList<>(images);
    }

    @Override
    public void enter() {
        currentImageIdx = 0;

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                animationController.setImage(images.get(currentImageIdx));
                currentImageIdx = (currentImageIdx + 1) % images.size();
            }
        };

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration), eh));
        timeline.play();
    }

    @Override
    public void exit() {
        timeline.stop();
    }
}
