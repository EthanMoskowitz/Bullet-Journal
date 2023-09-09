package cs3500.pa05.controller;

import cs3500.pa05.model.BujoTime;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.enumerations.Meridiem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for handle the event window
 */
public class EventController implements Controller {

  /**
   * Journal to create event in
   */
  protected final Journal journal;

  /**
   * Window title label
   */
  @FXML
  protected Label title;

  /**
   * Event cancel button
   */
  @FXML
  protected Button cancel;

  /**
   * Event confirm button
   */
  @FXML
  protected Button confirm;

  /**
   * Event name text field
   */
  @FXML
  protected TextField name;

  /**
   * Event duration text field
   */
  @FXML
  protected TextField duration;

  /**
   * Event description text area
   */
  @FXML
  protected TextArea description;

  /**
   * Event day combo box
   */
  @FXML
  protected ComboBox<String> day;

  /**
   * Event start hour text field
   */
  @FXML
  protected TextField startHour;

  /**
   * Event start minute text field
   */
  @FXML
  protected TextField startMinute;

  /**
   * Event start meridiem combo box
   */
  @FXML
  protected ComboBox<String> meridiem;

  /**
   * Event warning message label
   */
  @FXML
  protected Label message;

  /**
   * Instantiates an EventController
   *
   * @param journal a journal
   */
  public EventController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    startHour.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
      if (!newPropertyValue) {
        enforceDoubleDigits(startHour);
      }
    });
    startMinute.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
      if (!newPropertyValue) {
        enforceDoubleDigits(startMinute);
      }
    });

    setFxmlItems();
  }

  /**
   * Sets the fxml items
   */
  protected void setFxmlItems() {
    day.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday"));
    meridiem.setItems(FXCollections.observableArrayList("AM", "PM"));
    meridiem.setValue("AM");
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
    if (journal.getPreferences().getEventLimit() <= getEventsOnThisDay(checkedDay)) {
      message.setText("Maximum number of events reached for today.");
    } else {
      updateJournal();
    }
  }

  /**
   * Updates the journal with a new event
   */
  protected void updateJournal() {
    //Checked Values
    String checkedName = Validator.validateName(name.getText());
    String checkedDesc = Validator.validateDescription(description.getText());
    Day checkedDay = Validator.validateDay(day.getValue());
    Meridiem checkedMeridiem = Meridiem.valueOf(meridiem.getValue());
    BujoTime checkedTime =
        Validator.validateTime(startHour.getText(), startMinute.getText(), checkedMeridiem);
    Double checkedDuration = Validator.validateDuration(duration.getText());

    if (checkedDay == null) {
      message.setText("Please select a day.");
    } else if (checkedName.equals("")) {
      message.setText("Please enter a name for the event.");
    } else if (checkedTime == null) {
      message.setText("Please enter a valid start time.");
    } else if (checkedDuration == null) {
      message.setText("Invalid duration. Enter a valid duration.");
    } else {
      updateEntry(checkedName, checkedDesc, checkedDay, checkedTime, checkedDuration);
      SceneChanger.switchToScene("WeekView.fxml",
          new MenuController(journal), "Bujo's Bullet Journal");
    }
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

  /**
   * Adds the new entry to the task list
   *
   * @param name     a name
   * @param desc     a description
   * @param day      a day
   * @param start    a start time
   * @param duration a duration
   */
  protected void updateEntry(String name, String desc, Day day, BujoTime start, double duration) {
    Event e = new Event(name, desc, day, start, duration);
    journal.addEvent(e);
  }

  /**
   * Enforces double digits on the given text field
   *
   * @param textField a text field
   */
  protected void enforceDoubleDigits(TextField textField) {
    String numStr = textField.getText();
    if (!numStr.equals("")) {
      try {
        Integer.parseInt(numStr);
        if (numStr.length() == 1) {
          textField.setText("0" + numStr);
        } else if (numStr.length() > 2) {
          textField.setText(numStr.substring(0, 2));
        }
      } catch (NumberFormatException e) {
        textField.setText("");
      }
    }
  }
}