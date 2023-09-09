package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.Day;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * Controller for the edit window
 */
public class EditController implements Controller {
  private final Journal journal;

  @FXML
  private Button cancel;
  @FXML
  private Button confirm;
  @FXML
  private Slider taskSlider;
  @FXML
  private Label taskLimit;
  @FXML
  private Slider eventSlider;
  @FXML
  private Label eventLimit;
  @FXML
  private TextField name;

  /**
   * Instantiates an EditController
   *
   * @param journal a journal
   */
  public EditController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    name.setText(journal.getPreferences().getName());
    taskLimit.setText("New Task Limit: " + journal.getPreferences().getTaskLimit());
    eventLimit.setText("New Event Limit: " + journal.getPreferences().getEventLimit());
    eventSlider.setValue(journal.getPreferences().getEventLimit());
    taskSlider.setValue(journal.getPreferences().getTaskLimit());
    eventSlider.setMin(maxEvents());
    taskSlider.setMin(maxTasks());
    eventSlider.setSnapToTicks(true);
    taskSlider.setSnapToTicks(true);
    cancel.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));

    taskSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      int tasks = (int) taskSlider.getValue();
      taskLimit.setText("New Task Limit: " + tasks);
    });
    eventSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      int events = (int) eventSlider.getValue();
      eventLimit.setText("New Event Limit: " + events);
    });
    confirm.setOnAction(event -> setNew());
  }

  /**
   * Sets the new preferences
   */
  private void setNew() {
    if (Objects.equals(name.getText(), "")) {
      return;
    }
    String newName = name.getText();
    int tasks = (int) taskSlider.getValue();
    int events = (int) eventSlider.getValue();
    journal.getPreferences().setName(newName);
    journal.getPreferences().setEventLimit(events);
    journal.getPreferences().setTaskLimit(tasks);
    SceneChanger.switchToScene("WeekView.fxml",
        new MenuController(journal), "Bujo's Bullet Journal");
  }

  /**
   * Gets the max amount of tasks for all days
   *
   * @return int of max tasks
   */
  private int maxTasks() {
    int max = 0;
    for (Day d : Day.asList()) {
      int newMax = getTasksOnThisDay(d);
      max = Math.max(max, newMax);
    }
    return max;
  }

  /**
   * Gets the max amount of events for all days
   *
   * @return int of max events
   */
  private int maxEvents() {
    int max = 0;
    for (Day d : Day.asList()) {
      int newMax = getEventsOnThisDay(d);
      max = Math.max(max, newMax);
    }
    return max;
  }

  /**
   * Gets the number of tasks on this day
   *
   * @param day a day
   * @return the number of tasks on this day
   */
  private int getTasksOnThisDay(Day day) {
    int tasksOnThisDay = 0;
    for (Task t : journal.getTasks()) {
      if (t.getDay().equals(day)) {
        tasksOnThisDay++;
      }
    }
    return tasksOnThisDay;
  }

  /**
   * Gets the number of events on this day
   *
   * @param day a day
   * @return the number of events on this day
   */
  private int getEventsOnThisDay(Day day) {
    int eventsOnThisDay = 0;
    for (Event e : journal.getEvents()) {
      if (e.getDay().equals(day)) {
        eventsOnThisDay++;
      }
    }
    return eventsOnThisDay;
  }
}
