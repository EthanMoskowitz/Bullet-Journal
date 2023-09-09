package cs3500.pa05.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.JournalJson;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Class for reading a .bujo file into a JsonNode
 */
public class BujoReader {

  /**
   * Produces a journal from a valid .bujo
   *
   * @param path path to .bujo file
   * @return journal Json
   * @throws IOException if an IOException occurs
   */
  public static JournalJson produceJournal(Path path) throws IOException {
    return new ObjectMapper().readValue(path.toFile(), JournalJson.class);
  }
}