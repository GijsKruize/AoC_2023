import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day_2 {
    static int sum = 0;
    static int red = 12;
    static int green = 13;
    static int blue = 14;

    public static int printSum() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("/Users/gijskruize/Developer/Advent of Code/day_2.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                boolean gameIsValid = true;
                String[] gameParts = line.split(": ");

                // Extract game ID
                String gameId = gameParts[0].trim().replaceAll("[^\\d.]", "");
                int gameInt = Integer.parseInt(gameId);
                // Split the rest based on ';'
                String[] moves = gameParts[1].split("; ");
                for (String move : moves) {
                    String[] hands = move.split(", ");
                    for (String hand : hands) {
                        String[] items = hand.split(" ");
                        int amount = Integer.parseInt(items[0]);
                        if (items[1].equals("red")){
                            if (amount > red){
                                gameIsValid = false;
                            }
                        } else if (items[1].equals("green")){
                            if (amount > green){
                                gameIsValid = false;
                            }
                        }else if (items[1].equals("blue")){
                            if (amount > blue){
                                gameIsValid = false;
                            }
                        }
                    }
                }
                if (gameIsValid) {
                    sum = sum + gameInt;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(printSum());
    }

    // Other methods and class members can be added here

}