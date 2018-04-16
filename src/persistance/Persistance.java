package persistance;

import java.util.List;

public interface Persistance {

    boolean checkForExistingSource(String sourcePath);

    void writeSourceToFile(String sourcePath, String source);

    List<String> readSourceFromFile(String sourcePath);

}
