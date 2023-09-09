package cs3500.pa05.model;

import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;

/**
 * Class for representing a journal task
 */
public class Task extends Item {

  private CompletionStatus status;

  /**
   * Instantiates a task
   *
   * @param name the name of the task
   * @param description a description of the task
   * @param day the day of the weeks the task occurs on
   * @param status the completion status of the task
   */
  public Task(String name, String description, Day day, CompletionStatus status) {
    super(name, description, day);
    this.status = status;
  }

  /**
   * Gets the status
   *
   * @return the completion status
   */
  public CompletionStatus getStatus() {
    return this.status;
  }

  /**
   * Sets the status
   *
   * @param cs a completion status
   */
  public void setStatus(CompletionStatus cs) {
    this.status = cs;
  }

}
