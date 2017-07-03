package letterCalculator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by gvaradi on 2017.07.03..
 */
public class LetterCalculator {

    public static void main(String[] args) {

        System.out.println("Add meg a szöveget: ");
        Scanner scanner = new Scanner(System.in);
        String strOrigin = scanner.nextLine();
        String str = strOrigin.toLowerCase().replaceAll("\\W", "").replaceAll("\\d", "");

        System.out.println(str);
        Set<Character> letterSet = new HashSet<>();

        for (char c: str.toCharArray()) {
            letterSet.add(c);
        }

        System.out.println(strOrigin + "-> " + letterSet.size() + "-féle karakter");

    }


}
