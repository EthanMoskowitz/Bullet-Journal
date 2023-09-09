package cs3500.pa05.controller;

import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for handling the task window
 */
public class TaskController implements Controller {

  /**
   * Journal to create task in
   */
  protected final Journal journal;

  /**
   * Task window title
   */
  @FXML
  protected Label taskTitle;

  /**
   * Task cancel button
   */
  @FXML
  protected Button cancel;

  /**
   * Task confirm button
   */
  @FXML
  protected Button confirm;

  /**
   * Task day combo box
   */
  @FXML
  protected ComboBox<String> day;

  /**
   * Task name text field
   */
  @FXML
  protected TextField name;

  /**
   * Task description text area
   */
  @FXML
  protected TextArea description;

  /**
   * Task status combo box
   */
  @FXML
  protected ComboBox<String> status;

  /**
   * Task message label
   */
  @FXML
  protected Label message;

  /**
   * Instantiates a task controller
   *
   * @param journal the current journal
   */
  public TaskController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    taskTitle.setText("New Task");
    day.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"));
    status.setItems(FXCollections.observableArrayList("Complete", "Incomplete"));
    cancel.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    confirm.setOnAction(event -> checkLimit());
    description.setOnKeyReleased(e -> Validator.enforceDescriptionLimit(
        description.getText(), description, message));
    name.setOnKeyReleased(e -> Validator.enforceTitleLimit(name.getText(), name, message));
  }

  /**
   * Checks the limit
   */
  protected void checkLimit() {
    Day checkedDay = Validator.validateDay(day.getValue());
    if (journal.getPreferences().getTaskLimit() <= getTasksOnThisDay(checkedDay)) {
      message.setText("Maximum number of tasks reached for today.");
    } else {
      updateJournal();
    }
  }

  /**
   * Updates the journal with the new task
   */
  protected void updateJournal() {
    String checkedName = Validator.validateName(name.getText());
    Day checkedDay = Validator.validateDay(day.getValue());
    String checkedDesc = description.getText();
    CompletionStatus checkedStatus = Validator.validateStatus(status.getValue());

    if (checkedDay == null) {
      message.setText("Please select a day.");
    } else if (checkedName.equals("")) {
      message.setText("Please enter a name for the task.");
    } else if (checkedStatus == null) {
      message.setText("Please select a status.");
    } else {
      updateEntry(checkedName, checkedDesc, checkedDay, checkedStatus);
      SceneChanger.switchToScene("WeekView.fxml",
          new MenuController(journal), "Bujo's Bullet Journal");
    }
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
   * Adds a new entry to the journal's list of entries
   *
   * @param name    name of the task
   * @param desc    description of the task
   * @param day     day of the task
   * @param status  status of the task
   */
  protected void updateEntry(String name, String desc, Day day, CompletionStatus status) {
    Task task = new Task(name, desc, day, status);
    journal.addTask(task);
  }
}
