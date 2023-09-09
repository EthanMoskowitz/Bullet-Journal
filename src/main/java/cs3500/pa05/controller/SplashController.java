package cs3500.pa05.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Controls splash screen
 */
public class SplashController implements Controller {
  @FXML
  private Label load;

  @FXML
  private Rectangle progress;

  @FXML
  private ImageView gif;

  @Override
  public void run() {
    final String[] dots = {"."};
    Timeline loads = new Timeline(new KeyFrame(Duration.millis(500), e -> {
      if (dots[0].equals("....")) {
        dots[0] = ".";
      }
      load.setText("LOADING" + dots[0]);
      dots[0] += ".";
    }));
    loads.setCycleCount(16);
    loads.play();

    Timeline bar = new Timeline(new KeyFrame(Duration.millis(50), e -> {
      progress.setWidth(progress.getWidth() + 2.1125);
    }));
    bar.setCycleCount(160);
    bar.play();

    Timeline gifChange1 = new Timeline(new KeyFrame(Duration.millis(3385), e -> {
      Image gif1 = new Image("file:src/main/resources/images/loading2.gif");
      gif.setImage(gif1);
    }));
    gifChange1.setCycleCount(1);
    gifChange1.play();

    Timeline gifChange2 = new Timeline(new KeyFrame(Duration.millis(6085), e -> {
      Image gif2 = new Image("file:src/main/resources/images/loading3.gif");
      gif.setImage(gif2);
    }));
    gifChange2.setCycleCount(1);
    gifChange2.play();

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), e -> {
      SceneChanger.switchToScene("Welcome.fxml", new WelcomeSceneController(), "Welcome");
    }));
    timeline.setCycleCount(1);
    timeline.play();
  }

}
