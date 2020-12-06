package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class R2 {

    public static int getSeatID() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day5input.txt");
        List<String> input = Files.readAllLines(path);
        List<Integer> seatIDs = new ArrayList<>();
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
            seatIDs.add(id);
        }
        Collections.sort(seatIDs);
        for(int i = 1; i < seatIDs.size() - 1; i++) {
            int nextID = seatIDs.get(i + 1);
            int seatID = seatIDs.get(i);
            if(seatID + 1 != nextID) {
                return seatID + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        try {
            System.out.println(getSeatID());
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
