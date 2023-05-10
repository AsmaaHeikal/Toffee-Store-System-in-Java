import java.io.*;
import java.util.*;

public class Catalog {
    public void displayCatalog() throws IOException {
        System.out.println("--------------------Catalog--------------------");
        FileReader reader = new FileReader("catalog.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("------------------------------------------------");
        bufferedReader.close();
    }
}
