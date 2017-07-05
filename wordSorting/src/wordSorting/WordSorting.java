package wordSorting;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by gvaradi on 2017.07.03..
 */
public class WordSorting {

    public static void main(String[] args) {

        System.out.println("Add meg a szöveget: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().trim().replaceAll("[\\W\\d]+]", "").trim();
        List<String> wordList = Arrays.asList(str.split(" "));
        Collections.sort(wordList);

        System.out.println(wordList);
        System.out.println("Adj meg egy eleresi utvonalat es a file nevet: ");
        String resultPath = scanner.nextLine();

        try (FileWriter fw = new FileWriter(resultPath,true)){
            fw.write(wordList.toString() + "\r\n");
        } catch (IOException ioe) {
            throw new IllegalArgumentException(ioe.getMessage());
        }
    }

}
