package day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * class that uses TreeMap for the floor layout
 */
public class R1 {

    public static SortedMap<Integer, List<Integer>> getBlackTiles() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day24input.txt");
        List<String> input = Files.readAllLines(path);
        SortedMap<Integer, List<Integer>> blackTiles = new TreeMap<>();
        // process direction instructions
        for (String instruction : input) {            
            int row = 0;
            int col = 0;
            for (int i = 0; i < instruction.length(); i++) {
                switch (instruction.charAt(i)) {
                case 'e':
                    col += 2;
                    break;
                case 'w':
                    col -= 2;
                    break;
                case 'n':
                    switch (instruction.charAt(i + 1)) {
                    case 'e':
                        col += 1;
                        row -= 1;
                        i++;
                        break;
                    case 'w':
                        col -= 1;
                        row -= 1;
                        i++;
                        break;
                    default:
                        System.out.println("could not find direction at pos " + i + ": " + instruction);
                        break;
                    }
                    break;
                case 's':
                    switch (instruction.charAt(i + 1)) {
                    case 'e':
                        col += 1;
                        row += 1;
                        i++;
                        break;
                    case 'w':
                        col -= 1;
                        row += 1;
                        i++;
                        break;
                    default:
                        System.out.println("could not find direction at pos "+ i + ": " + instruction);
                        break;
                    }
                    break;
                default:
                    System.out.println("could not find direction at pos " + i + ": " + instruction);
                    break;
                }
            }
            // flip tiles
            Integer keyRow = Integer.valueOf(row);
            Integer valueCol = Integer.valueOf(col);
            if (!blackTiles.containsKey(keyRow)) {
                List<Integer> valuesForKeyCol = new ArrayList<>();
                valuesForKeyCol.add(valueCol);
                blackTiles.put(keyRow, valuesForKeyCol); // flip tile from white to black                
            } else {
                List<Integer> valuesForKeyCol = blackTiles.get(keyRow);
                if (!valuesForKeyCol.contains(valueCol)) {
                    valuesForKeyCol.add(valueCol); // flip tile from white to black
                } else {                    
                    valuesForKeyCol.remove(valueCol); // flip tile back from black to white
                }
            }
        }        
        return blackTiles;
    }

    public static void main(String[] args) {
        try {
            SortedMap<Integer, List<Integer>> blackTiles = getBlackTiles();
            // count number of black tiles
            int noOfBlackTiles = 0;
            for (List<Integer> valuesForKeyRow : blackTiles.values()) {
                noOfBlackTiles += valuesForKeyRow.size();
            }
            System.out.println(noOfBlackTiles);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
