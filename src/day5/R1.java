package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static int getHighestSeatID() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day5input.txt");
        List<String> input = Files.readAllLines(path);
        int maxID = 0;
        for(String bP : input) {
            int row = 0;
            int col = 0;
            for(int i = 6; i >= 0; i--) {
                if(bP.charAt(i) == 'B') {
                    row += Math.pow(2, 6-i);
                }
            }
            for(int i = 2; i >= 0; i--) {
                if(bP.charAt(7 + i) == 'R') {
                    col += Math.pow(2, 2-i);
                }
            }
            int id = 8 * row + col;
            maxID = Math.max(maxID, id);
        }
        return maxID;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getHighestSeatID());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
