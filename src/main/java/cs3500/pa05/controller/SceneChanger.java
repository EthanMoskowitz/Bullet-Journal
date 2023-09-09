package cs3500.pa05.controller;

import cs3500.pa05.view.FxmlViewLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for controller the switch of scenes
 */
public class SceneChanger {

  private static Stage stage;

  /**
   * Sets the stage
   *
   * @param s a stage
   */
  public static void setStage(Stage s) {
    stage = s;
  }

  /**
   * Gets the stage
   *
   * @return the stage
   */
  public static Stage getStage() {
    return stage;
  }

  /**
   * Gets the scene
   *
   * @return the scene
   */
  public static Scene getScene() {
    return stage.getScene();
  }

  /**
   * Switches the scene to the task container
   *
   * @param fxmlFile a string of the desired FXML file to switch to
   * @param controller a controller
   * @param sceneName the name of the scene to change to
   */
  public static void switchToScene(String fxmlFile, Controller controller,
                                   String sceneName) {
    FxmlViewLoader loader = new FxmlViewLoader(fxmlFile, controller);
    Scene scene = loader.load();
    stage.setScene(scene);
    stage.setTitle(sceneName);
    stage.show();
    controller.run();
  }
}
