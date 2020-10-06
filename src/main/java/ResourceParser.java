import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

interface ResourceParser {
  String PATH = "./src/data/";
  // Default path for files & other data

  /**
   * The loading procedures - load data then process it for cleaning (remove nulls & lowercase)
   * load(file) = main loading procedure
   * load() = if default file is used (calls load(file) internally
   *
   * @param file - file to write to
    */
  List load(String file);
  List load() throws FileNotFoundException;
  void clean();

  /** Standard search methods - search for one item or return all */
  List selectAllEntries();
  List selectSpecificEntry(String searchCriteria);

  /** If we update the data we will either want to overwrite it or write to a new file
   *  Again - file is main write process; () calls main process internally to use default
   *
   * @param file - file to write to
   */
  void insertNewEntries(String file);
  void insertNewEntries() throws IOException;
  void updateEntries(String file);
  void updateEntries() throws IOException;
}
