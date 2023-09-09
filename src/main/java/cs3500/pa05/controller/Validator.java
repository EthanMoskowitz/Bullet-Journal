package cs3500.pa05.controller;

import cs3500.pa05.model.BujoTime;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.enumerations.Meridiem;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Class for validating inputs
 */
public class Validator {

  private static final int NAME_LIMIT = 20;
  private static final int DESCRIPTION_LIMIT = 200;

  /**
   * Validates the given name
   *
   * @param name a name
   * @return the name
   */
  public static String validateName(String name) {
    return name;
  }

  /**
   * Validates the day
   *
   * @param day a day
   * @return the day or null if the given is null
   */
  public static Day validateDay(String day) {
    if (day == null) {
      return null;
    }
    return Day.valueOf(day.toUpperCase());
  }

  /**
   * Validates the description by adding line wrapping
   *
   * @param description a description
   * @return a description with line wraps
   */
  public static String validateDescription(String description) {
    String[] desc = description.split("\\n|\\r\\n");
    StringBuilder reformatted = new StringBuilder();
    for (String s : desc) {
      reformatted.append(s);
    }
    System.out.println(reformatted);
    StringBuilder withBreaks = new StringBuilder();
    int count = 0;
    for (int i = 0; i < reformatted.length(); i++) {
      if (count > 15 && reformatted.charAt(i) == ' ') {
        withBreaks.append("\r\n");
        count = 0;
      }
      withBreaks.append(reformatted.charAt(i));
      count++;
    }
    return withBreaks.toString();
  }

  /**
   * Validates the BujoTime
   *
   * @param givenHour   an hour
   * @param givenMinute a minute
   * @param meridiem    a meridiem
   * @return a BujoTime or null if any of the given are null
   */
  public static BujoTime validateTime(String givenHour, String givenMinute, Meridiem meridiem) {
    if (givenHour.equals("") || givenMinute.equals("")) {
      return null;
    } else {
      try {
        int hour = Integer.parseInt(givenHour);
        int minute = Integer.parseInt(givenMinute);

        if (hour <= 12 && hour >= 1 && minute <= 59 && minute >= 0) {
          return new BujoTime(hour, minute, meridiem);
        }
        return null;
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * Validates the duration
   *
   * @param givenDuration a duration
   * @return the duration or null if the given is null
   */
  public static Double validateDuration(String givenDuration) {
    if (givenDuration.equals("")) {
      return null;
    } else {
      try {
        return Double.parseDouble(givenDuration);
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * Validates the status
   *
   * @param givenStatus the status
   * @return a valid status, or null if null
   */
  public static CompletionStatus validateStatus(String givenStatus) {
    if (givenStatus == null) {
      return null;
    }
    return CompletionStatus.valueOf(givenStatus.toUpperCase());
  }

  /**
   * Enforces the description limit to be a maximum
   *
   * @param desc the description
   * @param description the description text area
   * @param message the warning label
   */
  public static void enforceDescriptionLimit(String desc, TextArea description, Label message) {
    if (desc.length() > DESCRIPTION_LIMIT) {
      description.setText(desc.substring(0, DESCRIPTION_LIMIT));
      message.setText("Description limit of " + DESCRIPTION_LIMIT + " char reached.");
    } else {
      message.setText("Description Characters Remaining: " + (DESCRIPTION_LIMIT - desc.length()));
    }
  }

  /**
   * Enforces the title limit
   *
   * @param name the name
   * @param nameField the text field where the name is entered
   * @param message the warning label
   */
  public static void enforceTitleLimit(String name, TextField nameField, Label message) {
    if (name.length() > NAME_LIMIT) {
      nameField.setText(name.substring(0, NAME_LIMIT));
      message.setText("Name limit of " + NAME_LIMIT + " char reached.");
    } else {
      message.setText("Name Characters Remaining: " + (NAME_LIMIT - name.length()));
    }
  }
}
