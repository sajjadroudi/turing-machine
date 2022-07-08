package ir.roudi.turingmachine;

import java.util.List;

public class Utils {

    public static void addAll(List<Character> list, String input) {
        for(int i = 0; i < input.length(); i++) {
            list.add(input.charAt(i));
        }
    }

}
