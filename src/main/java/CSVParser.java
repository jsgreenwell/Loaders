import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class CSVParser implements ResourceParser {
  private String delimiter;

  public CSVParser() {
    this.delimiter = ",";
  }

  public CSVParser(String delimiter) {
    this.delimiter = delimiter;
  }

  protected String getDelimiter() {
    return delimiter;
  }

  /**
   * The loading procedures - load data then process it for cleaning (remove nulls & lowercase)
   * load(file) = main loading procedure load() = if default file is used (calls load(file)
   * internally
   *
   * @param file - file to write to
   */
  public abstract List load(String file);
  public abstract List load() throws FileNotFoundException;
  public abstract void clean();

  /**
   * Standard search methods - search for one item or return all
   */
  public abstract List selectAllEntries();
  public abstract List selectSpecificEntry(String searchCriteria);

  /**
   * If we update the data we will either want to overwrite it or write to a new file Again - file
   * is main write process; () calls main process internally to use default
   *
   * @param file - file to write to
   */
  public abstract void insertNewEntries(String file);
  public abstract void insertNewEntries() throws IOException;
  public abstract void updateEntries(String file);
  public abstract void updateEntries() throws IOException;
}