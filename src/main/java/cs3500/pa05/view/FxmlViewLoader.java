package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 *
 */
public class FxmlViewLoader {
  FXMLLoader loader;

  /**
   *
   * @param s a string of the fxml path to load
   * @param controller the controller for the scene
   */
  public FxmlViewLoader(String s, Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(s));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a Whack-a-Mole GUI layout.
   *
   * @return the layout
   */
  public Scene load() {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}