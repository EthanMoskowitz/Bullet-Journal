package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.JournalJson;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing BujoReader and its associated methods
 */
class BujoReaderTest {

  private JournalJson journal;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    try {
      BujoReader br = new BujoReader();
      journal = BujoReader.produceJournal(Path.of("src/test/resources/BujoReaderTest.bujo"));
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  void produceJournal() {
    JsonNode node = new ObjectMapper().convertValue(journal, JsonNode.class);
    assertEquals("{\"preferences\":{\"name\":\"Week 1\",\"taskLimit\":5,\"eventLimit\""
        + ":6},\"tasks\":[{\"name\":\"Name\",\"description\":\"Desc\",\"day\":\""
        + "MONDAY\",\"status\":\"COMPLETE\"}],\"events\":[{\"name\":\"Name\",\"des"
        + "cription\":\"Desc\",\"day\":\"MONDAY\",\"start\":{\"hour\":1,\"minute\":16,"
        + "\"meridiem\":\"PM\"},\"duration\":7.7}]}", node.toString());
    assertThrows(IOException.class, () -> BujoReader.produceJournal(Path.of("fake path")));
  }
}