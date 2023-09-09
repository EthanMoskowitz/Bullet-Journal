package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Item;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

/**
 * Utility class for generating popups
 */
public class PopupUtils {
  private static final Color COMPLETE_TASK_COLOR = Color.rgb(146, 196, 125);
  private static final Color INCOMPLETE_TASK_COLOR = Color.rgb(233, 153, 152);
  private static final Font MINI_VIEWER_FONT = Font.font("Verdana", FontWeight.MEDIUM, 15);
  private static final CornerRadii CORNER_RADII = new CornerRadii(2);
  private static final Insets INSETS = new Insets(2);

  /**
   * Produces links and nodes of text for this popup
   *
   * @param string    String of text to separate into links and text
   * @return          List of nodes in proper original order as links and labels
   */
  public static List<Node> linksAndLabels(String string) {
    List<Node> popupTextNodes = new ArrayList<>();
    int currentIndex = 0;
    String regex =
        "(http)(s|)(:\\/\\/)(www\\.|)([A-Za-z0-9\\n\\r]+)"
            + "((?<!\\.)\\.(?!\\.))([A-Za-z0-9\\/\\n\\r\\?\\=]+)";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(string);

    List<String> links = new ArrayList<>();
    List<Integer> linkIndexes = new ArrayList<>();

    while (m.find()) {
      links.add(m.group());
      linkIndexes.add(m.start());
    }

    List<String> split = new ArrayList<>(Arrays.asList(string.split(regex)));

    int numTimes = split.size() + links.size();
    for (int i = 0; i < numTimes; i++) {
      if (links.size() > 0 && linkIndexes.get(0) == currentIndex) {

        Hyperlink hyperLink = new Hyperlink(links.get(0));
        String l = links.get(0);
        hyperLink.setOnAction(e -> setHyperlinkAction(l));
        popupTextNodes.add(hyperLink);
        currentIndex += links.get(0).length();
        links.remove(0);
        linkIndexes.remove(0);

      } else if (split.size() > 0) {
        Label label = new Label(split.get(0));
        currentIndex += split.get(0).length();
        popupTextNodes.add(label);
        split.remove(0);
      }
    }
    return styleLinksAndLabels(popupTextNodes);
  }

  /**
   * Sets the hyperlink to this string
   *
   * @param l link
   */
  private static void setHyperlinkAction(String l) {
    try {
      Desktop desk = Desktop.getDesktop();
      URI url = new URI(l);
      desk.browse(url);
    } catch (Exception exception) {
      //Ignore invalid link
    }
  }

  /**
   * adds style to the hyperlinks and labels
   *
   * @param nodes   list of nodes to style
   * @return        list of styled nodes
   */
  private static List<Node> styleLinksAndLabels(List<Node> nodes) {
    for (Node n : nodes) {
      if (n.getClass().equals(Hyperlink.class)) {
        Hyperlink h = (Hyperlink) n;
        h.setFont(MINI_VIEWER_FONT);
      } else {
        Label l = (Label) n;
        l.setFont(MINI_VIEWER_FONT);
        l.setTextAlignment(TextAlignment.CENTER);
        l.setWrapText(true);
      }
    }
    return nodes;
  }

  /**
   * Adds buttons to the popup window
   *
   * @param className the type of item
   * @param journal a journal
   * @param index the index of the item
   * @param popBox the popup
   * @param c the menu controller
   */
  public static void addTaskButtons(Class<? extends Item> className, Journal journal, int index,
                           VBox popBox, Controller c) {
    if (className.equals(Task.class)) {
      Button d = new Button("Mark as Complete");
      d.setPrefSize(150, 25);
      d.setOnAction(e -> {
        journal.getTasks().get(index).setStatus(CompletionStatus.COMPLETE);
        popBox.setBackground(new Background(
            new BackgroundFill(COMPLETE_TASK_COLOR, CORNER_RADII, INSETS)));
        c.run();
      });
      Button f = new Button("Mark as Incomplete");
      f.setPrefSize(150, 25);
      f.setOnAction(e -> {
        journal.getTasks().get(index).setStatus(CompletionStatus.INCOMPLETE);
        popBox.setBackground(new Background(
            new BackgroundFill(INCOMPLETE_TASK_COLOR, CORNER_RADII, INSETS)));
        c.run();
      });
      HBox taskButtons = new HBox();
      taskButtons.getChildren().addAll(f, d);
      taskButtons.setAlignment(Pos.BOTTOM_CENTER);
      taskButtons.setPrefSize(400, 50);
      popBox.getChildren().add(taskButtons);
    }
  }

  /**
   * Adds the default buttons to this item
   *
   * @param className   what type of item is this
   * @param index       what index does this popup correspond to
   * @param journal     a journal
   * @param popup       the popup
   * @param controller  the menu controller
   * @return            HBox with the correct data.
   */
  public static HBox buttons(Class<? extends Item> className, int index,
                             Journal journal, Popup popup, Controller controller) {
    HBox genericButtons = new HBox();
    genericButtons.setPrefSize(400, 50);
    genericButtons.setAlignment(Pos.BOTTOM_CENTER);

    Button b = new Button("Done");
    b.setPrefSize(80, 25);
    b.setOnAction(e -> {
      popup.hide();
      controller.run();
    });

    Button c = new Button("Delete");
    c.setPrefSize(80, 25);
    c.setOnAction(e -> hideAndDelete(index, className, journal, popup, controller));

    Button g = new Button("Edit");
    g.setPrefSize(80, 25);
    if (className.equals(Task.class)) {
      g.setOnAction(event -> {
        popup.hide();
        SceneChanger.switchToScene("NewTask.fxml",
            new EditTaskController(journal, index), "Edit");
      });
    } else {
      g.setOnAction(event -> {
        popup.hide();
        SceneChanger.switchToScene("NewEvent.fxml",
            new EditEventController(journal, index), "Edit");
      });
    }

    genericButtons.getChildren().add(c);
    genericButtons.getChildren().add(g);
    genericButtons.getChildren().add(b);
    return genericButtons;
  }

  /**
   * Hides and deletes this task from the popup menu
   *
   * @param index     index to delete from
   * @param className class of the Task/Event to delete
   */
  private static void hideAndDelete(int index, Class<? extends Item> className,
                                    Journal journal, Popup popup, Controller c) {
    popup.hide();
    if (className.equals(Task.class)) {
      journal.removeTask(index);
    } else if (className.equals(Event.class)) {
      journal.removeEvent(index);
    }
    c.run();
  }
}