package code;

import code.service.LevelParser;

import java.io.FileNotFoundException;

/**
 * Created by eric on 10/26/15.
 */
public class Main {
    public static void main(String[] arg) {
        try {
            LevelParser.parse("./src/main/resources/level1.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
