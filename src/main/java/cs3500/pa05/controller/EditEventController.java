package cs3500.pa05.controller;

import cs3500.pa05.model.BujoTime;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.enumerations.Day;
import javafx.collections.FXCollections;

/**
 * Controller for editing an event
 */
public class EditEventController extends EventController {
  private final Event event;
  private final Day initialDay;

  /**
   * Instantiates an EditEventController
   *
   * @param journal a journal
   * @param index the index of the event in the event list
   */
  public EditEventController(Journal journal, int index) {
    super(journal);
    this.event = journal.getEvents().get(index);
    initialDay = event.getDay();
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    setFxmlItems();
  }

  /**
   * Sets the fxml items
   */
  @Override
  protected void setFxmlItems() {
    title.setText("Edit Event");
    day.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday"));
    day.setValue(event.getDay().toString());
    name.setText(event.getName());
    startHour.setText(Integer.toString(event.getStart().getHour()));
    startMinute.setText(Integer.toString(event.getStart().getMinute()));
    meridiem.setItems(FXCollections.observableArrayList("AM", "PM"));
    meridiem.setValue(event.getStart().getMeridiem().toString().toUpperCase());
    duration.setText(Double.toString(event.getDuration()));
    description.setText(event.getDescription());
    cancel.setOnAction(event -> SceneChanger.switchToScene(
        "WeekView.fxml", new MenuController(journal), "Bujo's Bullet Journal"));
    confirm.setOnAction(event -> checkDays());
    description.setOnKeyReleased(e -> Validator.enforceDescriptionLimit(
        description.getText(), description, message));
    name.setOnKeyReleased(e -> Validator.enforceTitleLimit(name.getText(), name, message));
  }

  /**
   * Checks if days are the same
   */
  private void checkDays() {
    if (initialDay.equals(Validator.validateDay(day.getValue()))) {
      updateJournal();
    } else {
      checkLimit();
    }
  }

  /**
   * Adds the new entry to the task list
   *
   * @param name a name
   * @param desc a description
   * @param day a day
   * @param start a start time
   * @param duration a duration
   */
  @Override
  protected void updateEntry(String name, String desc, Day day, BujoTime start, double duration) {
    event.setName(name);
    event.setDescription(desc);
    event.setDay(day);
    event.setStart(start);
    event.setDuration(duration);
  }
}
