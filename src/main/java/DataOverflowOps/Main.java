package DataOverflowOps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args){
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
