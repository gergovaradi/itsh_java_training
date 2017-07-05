package wordSorting;

import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.*;

/**
 * Created by gvaradi on 2017.07.03..
 */
public class WordSorting {
    
    /**
     * @author Váradi Gergő
     * date: 2017.07.05.
     * Creates a collator with hungarian locale.
     */
    private final Collator hungarianSorting = Collator.getInstance(new Locale("hu"));

    /**
     * @author Váradi Gergő
     * date: 2017.07.05.
     * Writes the collection to a file.
     */
    private void writeFile(List<String> wordList, String resultPath){

        try (FileWriter fw = new FileWriter(resultPath,true)){
            fw.write(wordList.toString() + "\r\n");
        } catch (IOException ioe) {
            throw new IllegalArgumentException(ioe.getMessage());
        }

    }

    /**
     * @author Váradi Gergő
     * date: 2017.07.05.
     * Makes a collection from the input.
     */
    private List<String> makeCollectionFromText(String input){

        String str = input.replaceAll("[^a-zA-Zíéáűúüóö\\s]", "").replaceAll("[\\s]{2,}", " ");
        List<String> wordList = Arrays.asList(str.split(" "));
        return wordList;

    }

    /**
     * @author Váradi Gergő
     * date: 2017.07.05.
     * Main method.
     */
    public static void main(String[] args) {

        System.out.println("Add meg a szöveget: ");
        Scanner scanner = new Scanner(System.in);
        List<String> wordList = new WordSorting().makeCollectionFromText(scanner.nextLine());

        Collections.sort(wordList, new WordSorting().hungarianSorting);

        System.out.println("Adj meg egy eleresi utvonalat es a file nevet: ");
        String resultPath = scanner.nextLine();
        new WordSorting().writeFile(wordList, resultPath);

    }

}
