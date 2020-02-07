package DataOverflowOps;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args) throws Exception {
    // Reads a repair csv file and outputs the results for a report.

    // The repair file I will give to you (so everyone is using same)
    try {
      Path repairsFile = Paths.get("src/Data/SEOExample.csv");
      FileReader fileReader = new FileReader(repairsFile.toFile());
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line = bufferedReader.readLine();
      while (line != null) {

        if(!line.equals("")) {
          System.out.println(line);
        }
        line = bufferedReader.readLine();
      }
      bufferedReader.close();
    }
    catch (Exception e){
      System.out.println("Error");
    }
  }

}
