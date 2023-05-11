import java.io.*;
import java.util.*;

/**
 * This class is used to display the catalog of the products
 *
 * @author Asmaa Heikal
 * @version 1.0
 * @since 11 May 2023
 */
public class Catalog {

    /**
     * This method is used to display the catalog of the products
     */
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
