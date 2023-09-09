package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Task and its associated method
 */
class TaskTest {

  private Task task;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    task = new Task("Task name", "desc", Day.MONDAY, CompletionStatus.INCOMPLETE);
  }

  /**
   * Tests the getStatus method
   */
  @Test
  public void testGetStatus() {
    assertEquals(CompletionStatus.INCOMPLETE, task.getStatus());
  }

  /**
   * Tests the setStatus method
   */
  @Test
  public void testSetStatus() {
    assertEquals(CompletionStatus.INCOMPLETE, task.getStatus());
    task.setStatus(CompletionStatus.COMPLETE);
    assertEquals(CompletionStatus.COMPLETE, task.getStatus());
  }

}