package cs3500.pa05.controller;

import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Preferences;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.adapter.JournalAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * Controller for the welcome scene
 */
public class WelcomeSceneController implements Controller {

  @FXML
  private Button select;

  @FXML
  private Button create;

  @FXML
  private ImageView pig;

  /**
   * Runs the controller
   */
  public void run() {
    Preferences preferences = new Preferences("Welcome", 50, 50);
    Journal journal = new Journal(preferences, new ArrayList<>(), new ArrayList<>(), Path.of(""));
    create.setOnAction(event -> SceneChanger.switchToScene("NewWeek.fxml",
        new WeekController(journal), "New Week"));
    select.setOnAction(this::setAction);
    pig.setOnMouseClicked(e -> wink());
  }

  /**
   * Sets the action of the button
   *
   * @param event an event
   */
  private void setAction(ActionEvent event) {
    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("BUJO File", "*.bujo"));
    File file = chooser.showOpenDialog(null);

    if (file != null) {
      try {
        JournalJson journalJson = BujoReader.produceJournal(file.toPath());
        Journal journal = JournalAdapter.toJournal(journalJson, file.toPath());
        Controller menuController = new MenuController(journal);
        SceneChanger.switchToScene("WeekView.fxml", menuController, "Bujo's Bullet Journal");
      } catch (IOException e) {
        // ignore
      }
    }
  }

  /**
   * Wink
   */
  private void wink() {
    Image wink = new Image("file:src/main/resources/images/wink.png");
    pig.setImage(wink);
    Timeline time = new Timeline(new KeyFrame(Duration.seconds(.5), e -> {
      Image bujo = new Image("file:src/main/resources/images/BujoPig.PNG");
      pig.setImage(bujo);
    }));
    time.setCycleCount(1);
    time.play();
  }
}
