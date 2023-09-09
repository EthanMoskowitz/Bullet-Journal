package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Preferences;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Controller for the create week window
 */
public class WeekController implements Controller {
  private final Journal journal;

  @FXML
  private TextField newName;
  @FXML
  private Button newPath;
  @FXML
  private Button newCancel;
  @FXML
  private Button newConfirm;
  @FXML
  private Label pathLabel;
  @FXML
  private Slider taskSlider;
  @FXML
  private Label taskLimit;
  @FXML
  private Slider eventSlider;
  @FXML
  private Label eventLimit;

  private Path path;

  /**
   * Instantiates a week controller
   *
   * @param journal a journal
   */
  public WeekController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    newPath.setOnAction(event -> getDirectory());
    newConfirm.setOnAction(event -> createNewWeek());

    if (!journal.getPath().toString().equals("")) {
      newCancel.setOnAction(event -> SceneChanger.switchToScene(
          "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    } else {
      newCancel.setOnAction(event -> SceneChanger.switchToScene(
          "Welcome.fxml", new WelcomeSceneController(), "Welcome!"));
    }
    eventSlider.setSnapToTicks(true);
    taskSlider.setSnapToTicks(true);
    taskSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      int tasks = (int) taskSlider.getValue();
      taskLimit.setText("Task Limit: " + tasks);
    });
    eventSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      int tasks = (int) eventSlider.getValue();
      eventLimit.setText("Event Limit: " + tasks);
    });
  }

  /**
   * Gets a directory from the user
   */
  private void getDirectory() {
    DirectoryChooser chooser = new DirectoryChooser();

    Stage stage = SceneChanger.getStage();
    File dir = chooser.showDialog(stage);
    if (dir != null) {
      pathLabel.setText(dir.getAbsolutePath());
      path = Path.of(dir.getAbsolutePath());
    }

  }

  /**
   * Creates new week
   */
  private void createNewWeek() {
    String name = newName.getText();
    if (Objects.equals(name, "") || path == null) {
      return;
    }
    int taskLimit = (int) taskSlider.getValue();
    int eventLimit = (int) eventSlider.getValue();
    Preferences preferences = new Preferences(name, taskLimit, eventLimit);
    Journal newJournal = new Journal(preferences, new ArrayList<>(), new ArrayList<>(), path);
    Controller menuController = new MenuController(newJournal);
    SceneChanger.switchToScene("WeekView.fxml", menuController, "Bujo's Bullet Journal");
  }
}
