package persistance;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FilePersistance implements Persistance{

    @Override
    public boolean checkForExistingSource(String sourcePath) {
        Path path = Paths.get(new File("").getAbsolutePath() + sourcePath);
        return Files.exists(path);
    }

    @Override
    public void writeSourceToFile(String sourcePath, String source) {
        try {
            byte[] strToByte = source.getBytes();
            Path path = Paths.get(new File("").getAbsolutePath() + sourcePath);
            Files.write(path, strToByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> readSourceFromFile(String sourcePath) {
        List<String> source = null;
        try {
            Path path = Paths.get(new File("").getAbsolutePath() + sourcePath);
            source = Files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return source;
    }
}
