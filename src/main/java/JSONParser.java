import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParser implements ResourceParser {

  // can reuse locally
  private ObjectMapper mapper = new ObjectMapper();
  private String file = "user.json"; // Default file
  User user;

  /** LoadJSONMap just calls the Jackson method which loads & maps the data */
  private void loadJSONMap(String file) {
    try {
      user = mapper.readValue(new File(PATH + file),
          User.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Random FirstName just to show how this would be accessed
  protected void setFirstName(String name) {
    user.getName().setFirst(name);
  }

  /**
   * The loading procedures - load data then process it for cleaning (remove nulls & lowercase)
   * load(file) = main loading procedure load() = if default file is used (calls load(file)
   * internally
   *
   * @param file - file to write to
   */
  @Override
  public List load(String file) {
    loadJSONMap(file);
    try {
      return Arrays.asList(
          mapper.convertValue(
              mapper.readTree(String.valueOf(user)).get("values"),
              String[].class
          ));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List load() {
    return load(file);
  }

  @Override
  public void clean() { /* See Regex lesson */ }


  /** If we update the data we will either want to overwrite it or write to a new file
   *  Again - file is main write process; () calls main process internally to use default
   *
   * @param file - file to write to
   */
  @Override
  public void insertNewEntries(String file) {
    /* bunch of setter operations */
    try {
      mapper.writeValue(new File(PATH + file), user);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void insertNewEntries() {
    insertNewEntries(file);
  }

  @Override
  public void updateEntries(String file) {
    /* in this case we'd loop through user and update items where needed then */
    try {
      mapper.writeValue(new File(PATH + file), user);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateEntries() {
    updateEntries(file);
  }

  /** Standard search methods - search for one item or return all after looping */
  // TODO: Add bodies
  public List selectAllEntries() { return null; }
  public List selectSpecificEntry(String searchCriteria) { return null; }
}