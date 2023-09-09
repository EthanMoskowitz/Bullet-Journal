package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the About-window
 */
public class AboutController implements Controller {
  private final Journal journal;

  @FXML
  private Button back;

  /**
   * Instantiates an AboutController
   *
   * @param journal a journal
   */
  public AboutController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    back.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
  }
}
