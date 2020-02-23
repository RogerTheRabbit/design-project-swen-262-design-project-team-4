import com.company.FileIO.FileParser;

import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //System.out.println("Alans a bing S T I N K Y");

        FileParser artistParse = new FileParser("src/data/global/", "artists.csv");
        System.out.println(artistParse.readFile());
    }
}
