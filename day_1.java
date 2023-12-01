import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class day_1 {
    static int sum = 0;

    // create map from number to integer variable of number
    public static String replaceNumbers(String line) {
        Map<String, String> numberMap = new HashMap<>();
        numberMap.put("one", "o1e");
        numberMap.put("two", "t2o");
        numberMap.put("three", "t3e");
        numberMap.put("four", "f4r");
        numberMap.put("five", "f5e");
        numberMap.put("six", "s6x");
        numberMap.put("seven", "s7n");
        numberMap.put("eight", "e8t");
        numberMap.put("nine", "n9e");

        int currentPosition = 0;
        while (currentPosition < line.length()) {
            boolean foundMatch = false;

            for (Map.Entry<String, String> entry : numberMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                if (line.startsWith(key, currentPosition)) {
                    line = line.substring(0, currentPosition) + value + line.substring(currentPosition + key.length());
                    currentPosition += value.length();
                    foundMatch = true;
                    break;
                }
            }

            if (!foundMatch) {
                currentPosition++;
            }
        }

        return line;
    }

    public static int printsum() {

        try (BufferedReader br = new BufferedReader(
                new FileReader("/Users/gijskruize/Developer/Advent of Code/day_1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String number = "0";
                // Process each line here
                line = replaceNumbers(replaceNumbers(line)); // coment out to get solution of week one
                // System.out.println(line);
                line = line.replaceAll("[^\\d.]", "");
                // System.out.println(line);
                int n = line.length();
                if (n >= 2) {
                    char first = line.charAt(0);
                    char last = line.charAt(n - 1);
                    number = new StringBuilder().append(first).append(last).toString();
                }
                if (n < 2) {
                    char first = line.charAt(0);
                    number = new StringBuilder().append(first).append(first).toString();
                }
                // System.out.println(number);
                // Parse String to integer
                int line_int = Integer.parseInt(number);
                sum = sum + line_int;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(printsum());
    }

}