package cs3500.pa05.controller;

import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.BujoWriter;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Item;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.adapter.JournalAdapter;
import cs3500.pa05.view.FxmlViewLoader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Controller for controlling the menu scene
 */
public class MenuController implements Controller {

  private static final Font WEEK_NAME_FONT = Font.font("Verdana", FontWeight.BOLD, 20);
  private static final Font LABEL_FONT = Font.font("Verdana", FontWeight.MEDIUM, 10);
  private static final Color EVENT_COLOR = Color.rgb(180, 166, 213);
  private static final Color COMPLETE_TASK_COLOR = Color.rgb(146, 196, 125);
  private static final Color INCOMPLETE_TASK_COLOR = Color.rgb(233, 153, 152);
  private static final CornerRadii CORNER_RADII = new CornerRadii(2);
  private static final Insets INSETS = new Insets(2);
  private final Journal journal;
  @FXML
  private Button task;
  @FXML
  private Button event;
  @FXML
  private Button week;
  @FXML
  private Button save;
  @FXML
  private Button open;
  @FXML
  private Button edit;
  @FXML
  private Button about;
  @FXML
  private VBox monday;
  @FXML
  private VBox tuesday;
  @FXML
  private VBox wednesday;
  @FXML
  private VBox thursday;
  @FXML
  private VBox friday;
  @FXML
  private VBox saturday;
  @FXML
  private VBox sunday;
  @FXML
  private Label name;
  private Popup popup;
  @FXML
  private VBox pop;
  @FXML
  private ScrollPane tasks;
  @FXML
  private Label border;
  @FXML
  private Label stats;
  @FXML
  private VBox queue;

  /**
   * Controls the menu scene
   *
   * @param journal a Journal
   */
  public MenuController(Journal journal) {
    this.journal = journal;
  }

  /**
   * Sets the button actions
   */
  @FXML
  public void run() {
    clearBoardVisual();
    setBorder();
    setMenubar();
    setShortcuts();
    addEventsToView();
    addTasksToView();
    addTasksToQueue();
    updateStats();
  }

  /**
   * Sets the warning border
   */
  private void setBorder() {
    if (popup == null || !popup.isShowing()) {
      if (journal.getTasks().size() < journal.getPreferences().getTaskLimit() * 7) {
        task.setOnAction(event -> SceneChanger.switchToScene(
            "NewTask.fxml", new TaskController(journal), "Add a new task"));
      } else {
        border.setText("  Maximum Amount of Tasks Reached for this Week.");
      }
      if (journal.getEvents().size() < journal.getPreferences().getEventLimit() * 7) {
        event.setOnAction(event -> SceneChanger.switchToScene(
            "NewEvent.fxml", new EventController(journal), "Add a new event"));
      } else {
        border.setText("  Maximum Amount of Events Reached for this Week.");
      }

      if (journal.getTasks().size() >= journal.getPreferences().getTaskLimit() * 7
          && journal.getEvents().size() >= journal.getPreferences().getEventLimit() * 7) {
        border.setText("  Maximum Amount of Tasks and Events Reached for this Week.");
      }
    } else {
      task.setOnAction(null);
      event.setOnAction(null);
    }
  }

  /**
   * Sets the menubar actions
   */
  private void setMenubar() {
    if (popup == null || !popup.isShowing()) {
      save.setOnAction(event -> fileSaver());
      open.setOnAction(event -> fileChooser());
      week.setOnAction(event -> SceneChanger.switchToScene("NewWeek.fxml",
          new WeekController(journal), "New Week"));
      name.setText(journal.getPreferences().getName());
      name.setFont(WEEK_NAME_FONT);
      edit.setOnAction(event -> SceneChanger.switchToScene("Edit.fxml",
          new EditController(journal), "Edit Preferences"));
      about.setOnAction(event -> SceneChanger.switchToScene("About.fxml",
          new AboutController(journal), "About"));
    } else {
      save.setOnAction(null);
      open.setOnAction(null);
      week.setOnAction(null);
      edit.setOnAction(null);
      about.setOnAction(null);
    }
  }

  /**
   * Sets the menu shortcuts
   */
  private void setShortcuts() {
    Scene scene = SceneChanger.getScene();
    KeyCombination saveCombo = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    Runnable saveRunnable = this::fileSaver;
    addShortCut(scene, saveCombo, saveRunnable);
    KeyCombination openCombo = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
    Runnable openRunnable = this::fileChooser;
    addShortCut(scene, openCombo, openRunnable);
    KeyCombination weekCombo = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
    Runnable weekRunnable = () -> SceneChanger.switchToScene("NewWeek.fxml",
        new WeekController(journal), "New Week");
    addShortCut(scene, weekCombo, weekRunnable);
    if (journal.getTasks().size() < journal.getPreferences().getTaskLimit() * 7) {
      KeyCombination taskCombo = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
      Runnable taskRunnable = () -> SceneChanger.switchToScene(
          "NewTask.fxml", new TaskController(journal), "Add a new task");
      addShortCut(scene, taskCombo, taskRunnable);
    }
    if (journal.getEvents().size() < journal.getPreferences().getEventLimit() * 7) {
      KeyCombination eventCombo = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
      Runnable eventRunnable = () -> SceneChanger.switchToScene(
          "NewEvent.fxml", new EventController(journal), "Add a new event");
      addShortCut(scene, eventCombo, eventRunnable);
    }
    KeyCombination editCombo = new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN);
    Runnable editRunnable = () -> SceneChanger.switchToScene("Edit.fxml",
        new EditController(journal), "Edit Tasks");
    addShortCut(scene, editCombo, editRunnable);
    KeyCombination aboutCombo = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
    Runnable aboutRunnable = () -> SceneChanger.switchToScene("About.fxml",
        new AboutController(journal), "About");
    addShortCut(scene, aboutCombo, aboutRunnable);
  }

  /**
   * Adds a shortcut to the scene
   *
   * @param scene       the scene
   * @param combination the shortcut combination
   * @param runnable    what to run on the shortcut
   */
  private void addShortCut(Scene scene, KeyCombination combination, Runnable runnable) {
    if (popup == null || !popup.isShowing()) {
      scene.getAccelerators().put(combination, runnable);
    } else {
      scene.getAccelerators().remove(combination);
    }
  }

  /**
   * Prompts the user to choose a .bujo file
   */
  @FXML
  private void fileChooser() {
    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("BUJO File", "*.bujo"));
    File f = chooser.showOpenDialog(null);

    if (f != null) {
      try {
        JournalJson journalJson = BujoReader.produceJournal(f.toPath());
        Journal journal = JournalAdapter.toJournal(journalJson, f.toPath());
        Controller menuController = new MenuController(journal);
        SceneChanger.switchToScene("WeekView.fxml",
            menuController, "Bujo's Bullet Journal");
      } catch (IOException e) {
        border.setText("");
      }
    }
  }

  /**
   * Saves the journal
   */
  private void fileSaver() {
    StringBuilder fileName = new StringBuilder();
    if (!journal.getPath().toFile().isFile()) {
      Path p = journal.getPath();
      String weekTitle = journal.getPreferences().getName();
      String[] titleArray = weekTitle.split(" ");
      for (String s : titleArray) {
        fileName.append(s);
      }
      String newPath = p.toString() + "/" + fileName + ".bujo";
      journal.setPath(Path.of(newPath));

    }
    JournalJson journalJson = JournalAdapter.toJson(journal);
    try {
      BujoWriter.writeJournal(journal.getPath(), journalJson);
      border.setText("  Journal saved in file: " + journal.getPath().getFileName().toString());
    } catch (IOException e) {
      border.setText("  Journal could not be saved.");
    }
  }

  /**
   * Adds the tasks in the journal to the week view
   */
  @FXML
  private void addTasksToView() {
    List<Task> tasks = journal.getTasks();
    for (int i = 0; i < tasks.size(); i++) {
      switch (tasks.get(i).getDay()) {
        case MONDAY -> monday.getChildren().add(generateTask(tasks.get(i), i));
        case TUESDAY -> tuesday.getChildren().add(generateTask(tasks.get(i), i));
        case WEDNESDAY -> wednesday.getChildren().add(generateTask(tasks.get(i), i));
        case THURSDAY -> thursday.getChildren().add(generateTask(tasks.get(i), i));
        case FRIDAY -> friday.getChildren().add(generateTask(tasks.get(i), i));
        case SATURDAY -> saturday.getChildren().add(generateTask(tasks.get(i), i));
        case SUNDAY -> sunday.getChildren().add(generateTask(tasks.get(i), i));
        default -> {

        }
      }
    }

  }

  /**
   * Adds tasks to task queue
   */
  @FXML
  private void addTasksToQueue() {
    for (int i = 0; i < journal.getTasks().size(); i++) {
      queue.getChildren().add(generateTask(journal.getTasks().get(i), i));
    }
    tasks.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    tasks.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    tasks.setContent(queue);
  }

  /**
   * Adds the events in the journal to the week view
   */
  @FXML
  private void addEventsToView() {
    List<Event> events = journal.getEvents();
    for (int i = 0; i < events.size(); i++) {
      switch (events.get(i).getDay()) {
        case MONDAY -> monday.getChildren().add(generateEvent(events.get(i), i));
        case TUESDAY -> tuesday.getChildren().add(generateEvent(events.get(i), i));
        case WEDNESDAY -> wednesday.getChildren().add(generateEvent(events.get(i), i));
        case THURSDAY -> thursday.getChildren().add(generateEvent(events.get(i), i));
        case FRIDAY -> friday.getChildren().add(generateEvent(events.get(i), i));
        case SATURDAY -> saturday.getChildren().add(generateEvent(events.get(i), i));
        case SUNDAY -> sunday.getChildren().add(generateEvent(events.get(i), i));
        default -> {
        }
      }
    }
  }

  /**
   * Generates a vbox representing a task
   *
   * @param task a task
   * @return the task as a vbox
   */
  private VBox generateTask(Task task, int index) {

    VBox taskBox = new VBox();
    Color color;
    if (task.getStatus().equals(CompletionStatus.COMPLETE)) {
      color = COMPLETE_TASK_COLOR;
      taskBox.getStyleClass().add("complete-task");
    } else {
      color = INCOMPLETE_TASK_COLOR;
      taskBox.getStyleClass().add("incomplete-task");
    }
    taskBox.setBackground(new Background(
        new BackgroundFill(color, CORNER_RADII, INSETS)));
    Label name = new Label(" " + task.getName());
    name.setFont(LABEL_FONT);
    Label description = new Label(" " + task.getDescription());
    description.setFont(LABEL_FONT);
    Label status = new Label(" " + task.getStatus().toString());
    status.setFont(LABEL_FONT);
    Label empty = new Label();
    empty.setFont(LABEL_FONT);
    taskBox.getChildren().addAll(name, description, status, empty);
    taskBox.setOnMouseClicked(event -> {
      makePopup(
          Arrays.asList(
              "Day: " + task.getDay(),
              "Task: " + task.getName(),
              "Description: " + task.getDescription(),
              "Status: " + task.getStatus().toString()),
          color, index, Task.class);
      run();
    });
    return taskBox;
  }

  /**
   * Generates a vbox representing an event
   *
   * @param event an event
   * @return the event as a vbox
   */
  private VBox generateEvent(Event event, int index) {

    VBox eventBox = new VBox();
    eventBox.setBackground(new Background(
        new BackgroundFill(EVENT_COLOR, CORNER_RADII, INSETS)));
    Label name = new Label(" " + event.getName());
    name.setFont(LABEL_FONT);
    Label description = new Label(" " + event.getDescription());
    description.setFont(LABEL_FONT);
    Label start = new Label(" " + event.getStart().toString());
    start.setFont(LABEL_FONT);
    Label duration = new Label(" " + event.getDuration());
    duration.setFont(LABEL_FONT);
    eventBox.getChildren().addAll(name, description, start, duration);

    String desc = event.getDescription();
    String formattedDesc = desc.replaceAll("(.{25})", "$1\n");

    eventBox.setOnMouseClicked(e -> {
      makePopup(
          Arrays.asList(
              "Day: " + event.getDay(),
              "Event: " + event.getName(),
              "Description: " + formattedDesc,
              "Start Time: " + event.getStart().toString(),
              "Duration: " + event.getDuration() + " hours"),
          EVENT_COLOR, index, Event.class);
      run();
    });
    eventBox.getStyleClass().add("event");
    return eventBox;
  }

  /**
   * Makes a mini-viewer popup when an item is clicked
   *
   * @param data  a list of the item's data
   * @param color the color of the item
   */
  private void makePopup(List<String> data, Color color,
                         int index, Class<? extends Item> className) {
    if (popup != null && popup.isShowing()) {
      return;
    }
    popup = new Popup();
    FxmlViewLoader loader = new FxmlViewLoader("Popup.fxml", this);
    Scene s = loader.load();
    popup.getContent().add(s.getRoot());
    pop.setBackground(new Background(
        new BackgroundFill(color, CORNER_RADII, INSETS)));

    for (String string : data) {
      pop.getChildren().addAll(PopupUtils.linksAndLabels(string));
    }

    PopupUtils.addTaskButtons(className, journal, index, this.pop, this);
    pop.getChildren().add(PopupUtils.buttons(className, index, journal, popup, this));

    popup.getContent().add(pop);
    Stage stage = SceneChanger.getStage();
    popup.show(stage);
  }

  /**
   * Clears the dynamic board items
   */
  private void clearBoardVisual() {
    queue.getChildren().clear();
    monday.getChildren().clear();
    tuesday.getChildren().clear();
    wednesday.getChildren().clear();
    thursday.getChildren().clear();
    friday.getChildren().clear();
    saturday.getChildren().clear();
    sunday.getChildren().clear();
    border.setText("");
  }

  /**
   * When called, updates the statistics for this object's journal
   */
  private void updateStats() {
    int completeTasks = 0;
    int incompleteTasks = 0;
    int numEvents = journal.getEvents().size();

    for (Task t : journal.getTasks()) {
      if (t.getStatus().equals(CompletionStatus.COMPLETE)) {
        completeTasks++;
      } else {
        incompleteTasks++;
      }
    }
    double percent = 0;
    if (journal.getTasks().size() > 0) {
      percent = ((0.0 + completeTasks) / (completeTasks + incompleteTasks)) * 100;
    }
    String percentString = String.format("%.2f", percent);
    String statistics = "Percentage of Tasks Complete: " + percentString + "%"
        + "\nCompleted Tasks: " + completeTasks
        + "\nIncomplete Tasks: " + incompleteTasks
        + "\nEvents: this week: " + numEvents;

    stats.setText(statistics);
    stats.getStyleClass().add("stats");
  }
}