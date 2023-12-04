import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day_3 {
    static int numRows = 0;
    static int numCols = 0;

    public static char[][] textTo2DArray() {
        char[][] result = null;
        try (BufferedReader br = new BufferedReader(
                new FileReader("/Users/gijskruize/Developer/Advent of Code/day_3.txt"))) {
            // Read the number of rows and columns from the file
            numCols = (int) br.readLine().length();
            numRows = (int) br.lines().count() + 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reopen the file to read characters
        try (BufferedReader br = new BufferedReader(
                new FileReader("/Users/gijskruize/Developer/Advent of Code/day_3.txt"))) {
            // Initialize the 2D array
            result = new char[numRows][numCols];

            // Read each line and convert it to a character array
            for (int i = 0; i < numRows; i++) {
                String line = br.readLine();
                result[i] = line.toCharArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static int extractNumbers(char[][] array) {
        int sum = 0;
        StringBuilder currentNumber = new StringBuilder();
        boolean hasValidNeighbor = false;
        int[][] directions = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };
        String invalidSymbols = "@#$%&*-+=/";
        hasValidNeighbor = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                char currentChar = array[i][j];
                if (Character.isDigit(currentChar)) {
                    currentNumber.append(currentChar);
                    for (int[] dir : directions) {
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];
                        // Check if the neighbor is within the bounds of the array
                        if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                            char neighborChar = array[newRow][newCol];
                            // Check if the neighbor is not a digit or '.'
                            if (invalidSymbols.contains(Character.toString(neighborChar))) {

                                hasValidNeighbor = true;
                            }
                        }
                    }
                } else if (currentNumber.length() > 0) {
                    int number = Integer
                            .parseInt(currentNumber.toString());
                            System.out.println(number + " | "+ hasValidNeighbor);
                    if(hasValidNeighbor){
                        sum = sum + number;
                    }
                    hasValidNeighbor = false; // Reset
                    currentNumber.setLength(0); // Reset
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        char[][] charArray = textTo2DArray();
        System.out.println(extractNumbers(charArray));
    }
}
