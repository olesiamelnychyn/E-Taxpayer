package sample.Animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * This class is responsible for performing an animation while running an application.
 * For now it is only used on two fields in LogIn-window.
 */
public class Shake {
    private TranslateTransition tt;

    /**
     * This method add parameters of how an object should move.
     *
     * @param node is an object which is performing animation.
     */
    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);
    }


    /**
     * This method runs an animation.
     */
    public void playAnim() {
        tt.playFromStart();
    }
}
