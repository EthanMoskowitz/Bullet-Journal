package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.SceneChanger;
import cs3500.pa05.controller.SplashController;
import cs3500.pa05.view.FxmlViewLoader;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 */
public class BujoApplication extends Application {

  /**
   * @param primaryStage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage primaryStage) {
    SceneChanger.setStage(primaryStage);
    Controller controller = new SplashController();
    FxmlViewLoader loader = new FxmlViewLoader("Splash.fxml", controller);

    try {
      primaryStage.setScene(loader.load());
      primaryStage.setTitle("Loading");
      primaryStage.setResizable(false);

      controller.run();

      primaryStage.show();
    } catch (IllegalStateException e) {
      System.out.println("Unable to Load");
    }
  }

  /**
   * Launches the application
   */
  public void run() {
    Application.launch();
  }

}
