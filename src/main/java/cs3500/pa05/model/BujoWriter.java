package cs3500.pa05.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.JournalJson;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Class for writing a journal JSON to a .bujo file
 */
public class BujoWriter {

  /**
   * Writes a journal to a .bujo file at the given path
   *
   * @param path the path
   * @param journal the journal
   * @throws IOException if an IOException occurs
   */
  public static void writeJournal(Path path, JournalJson journal) throws IOException {
    if (path.toString().endsWith(".bujo")) {
      new ObjectMapper().writeValue(path.toFile(), journal);
    } else {
      throw new IOException();
    }
  }
}
