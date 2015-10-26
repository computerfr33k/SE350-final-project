package code.service;

import code.model.LayoutMap;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by eric on 10/26/15.
 */
public class LevelParser {
    public static void parse(String filename) throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(filename));

        System.out.println(gson.fromJson(br, LayoutMap.class));
    }
}
