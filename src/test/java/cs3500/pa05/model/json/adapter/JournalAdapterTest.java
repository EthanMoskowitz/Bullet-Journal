package cs3500.pa05.model.json.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.BujoTime;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Preferences;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.enumerations.Meridiem;
import cs3500.pa05.model.json.JournalJson;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JournalAdapterTest {

  Journal journal1;
  JournalJson json1;

  ObjectMapper mapper;

  @BeforeEach
  void setUp() {
    Preferences p = new Preferences("Week 1", 5, 6);
    Task t1 = new Task("Task 1", "Description", Day.FRIDAY,  CompletionStatus.INCOMPLETE);
    Task t2 = new Task("Task 2", "Description", Day.SUNDAY,  CompletionStatus.COMPLETE);
    Event e1 = new Event("Event 1", "Description", Day.MONDAY,
        new BujoTime(4, 53, Meridiem.PM), 2.5);
    Event e2 = new Event("Event 2", "Description", Day.TUESDAY,
        new BujoTime(7, 26, Meridiem.AM), 3.0);

    try {
      json1 = BujoReader.produceJournal(Path.of("src/test/resources/BujoReaderTest.bujo"));
    } catch (IOException e) {
      fail();
    }

    journal1 = new Journal(p, Arrays.asList(t1, t2), Arrays.asList(e1, e2), Path.of(""));

    mapper = new ObjectMapper();
  }

  @Test
  void toJournal() {
    JsonNode node = this.mapper.convertValue(json1, JsonNode.class);
    assertEquals("{\"preferences\":{\"name\":\"Week 1\",\"taskLimit\":5,\"eventLimit\""
        + ":6},\"tasks\":[{\"name\":\"Name\",\"description\":\"Desc\",\"day\":\""
        + "MONDAY\",\"status\":\"COMPLETE\"}],\"events\":[{\"name\":\"Name\",\"des"
        + "cription\":\"Desc\",\"day\":\"MONDAY\",\"start\":{\"hour\":1,\"minute\":16,"
        + "\"meridiem\":\"PM\"},\"duration\":7.7}]}", node.toString());
  }

  @Test
  void toJson() {
    JournalJson json = JournalAdapter.toJson(journal1);
    JsonNode node = this.mapper.convertValue(json, JsonNode.class);
    assertEquals("{\"preferences\":{\"name\":\"Week 1\",\"taskLimit\":5,\"eventLimit"
        + "\":6},\"tasks\":[{\"name\":\"Task 1\",\"description\":\"Description\",\"d"
        + "ay\":\"FRIDAY\",\"status\":\"INCOMPLETE\"},{\"name\":\"Task 2\",\"descrip"
        + "tion\":\"Description\",\"day\":\"SUNDAY\",\"status\":\"COMPLETE\"}],\"eve"
        + "nts\":[{\"name\":\"Event 1\",\"description\":\"Description\",\"day\":\"MO"
        + "NDAY\",\"start\":{\"hour\":4,\"minute\":53,\"meridiem\":\"PM\"},\"duration\":2.5},"
        + "{\"name\":\"Event 2\",\"description\":\"Description\",\"day\":\"TUESDAY\",\"start\""
        + ":{\"hour\":7,\"minute\":26,\"meridiem\":\"AM\"},\"duration\":3.0}]}", node.toString());
  }
}