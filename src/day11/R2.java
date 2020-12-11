package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R2 {

    public static int getNoOfOccupiedSeats() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day11input.txt");
        List<String> input = Files.readAllLines(path);
        char[][] seats = new char[input.size()][input.get(0).length()];
        for (int x = 0; x < seats.length; x++) {
            for (int y = 0; y < seats[0].length; y++) {
                seats[x][y] = input.get(x).charAt(y);
            }
        }                
        return getOccupiedSeats(seats, occupySeats(seats));
    }

    public static int getOccupiedSeats(char[][] seats, char[][] changedSeats) {
        if (isSeatLayoutIdentical(seats, changedSeats)) {
            int occupiedSeats = 0;
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[0].length; j++) {
                        if (seats[i][j] == '#') {
                            occupiedSeats++;
                        }
                    }
                }
            return occupiedSeats;
        } else {
            return getOccupiedSeats(changedSeats, occupySeats(changedSeats));
        }        
    }
    
    public static boolean isSeatLayoutIdentical(char[][] before, char[][] after) {
        for (int i = 0; i < before.length; i++) {
            for (int j = 0; j < before[0].length; j++) {
                if (before[i][j] != after[i][j]) {
                    return false;
                }
            }
        }
        return true;    
    }    

    public static char[][] occupySeats(char[][] before) {
        char[][] after = new char[before.length][before[0].length];
        // copy data
        for (int i = 0; i < before.length; i++) {
            for (int j = 0; j < before[0].length; j++) {
                after[i][j] = before[i][j];
            }
        }
        
        for (int i = 0; i < before.length; i++) {
            for (int j = 0; j < before[0].length; j++) {
                int occSeats = 0;
                // check fields to the left
                boolean seatFound = false;
                int x = i;
                int y = j - 1;
                while (!seatFound && y >= 0) {
                    if (before[x][y] == 'L') {
                        seatFound = true;
                    } else if (before[x][y] == '#') {
                        seatFound = true;
                        occSeats++;
                    } else {
                        y--;
                    }
                }
                // check fields to the right
                seatFound = false;
                x = i;
                y = j + 1;
                while (!seatFound && y < before[0].length) {
                    if (before[x][y] == 'L') {
                        seatFound = true;
                    } else if (before[x][y] == '#') {
                        seatFound = true;
                        occSeats++;
                    } else {
                        y++;
                    }
                }
                // check fields to the top
                seatFound = false;
                x = i - 1;
                y = j;
                while (!seatFound && x >= 0) {
                    if (before[x][y] == 'L') {
                        seatFound = true;
                    } else if (before[x][y] == '#') {
                        seatFound = true;
                        occSeats++;
                    } else {
                        x--;
                    }
                }
                // check fields to the bottom
                seatFound = false;
                x = i + 1;
                y = j;
                while (!seatFound && x < before.length) {
                    if (before[x][y] == 'L') {
                        seatFound = true;
                    } else if (before[x][y] == '#') {
                        seatFound = true;
                        occSeats++;
                    } else {
                        x++;
                    }
                }
                // check fields to the top left
                seatFound = false;
                x = i - 1;
                y = j - 1;
                while (!seatFound && y >= 0 && x >= 0) {
                    if (before[x][y] == 'L') {
                        seatFound = true;
                    } else if (before[x][y] == '#') {
                        seatFound = true;
                        occSeats++;
                    } else {
                        x--;
                        y--;
                    }
                }
                // check fields to the bottom left
                seatFound = false;
                x = i + 1;
                y = j - 1;
                while (!seatFound && y >= 0 && x < before.length) {
                    if (before[x][y] == 'L') {
                        seatFound = true;
                    } else if (before[x][y] == '#') {
                        seatFound = true;
                        occSeats++;
                    } else {
                        x++;
                        y--;
                    }
                }
                // check fields to the top right
                seatFound = false;
                x = i - 1;
                y = j + 1;
                while (!seatFound && y < before[0].length && x >= 0) {
                    if (before[x][y] == 'L') {
                        seatFound = true;
                    } else if (before[x][y] == '#') {
                        seatFound = true;
                        occSeats++;
                    } else {
                        x--;
                        y++;
                    }
                }
                // check fields to the bottom right
                seatFound = false;
                x = i + 1;
                y = j + 1;
                while (!seatFound && y < before[0].length && x < before.length) {
                    if (before[x][y] == 'L') {
                        seatFound = true;
                    } else if (before[x][y] == '#') {
                        seatFound = true;
                        occSeats++;
                    } else {
                        x++;
                        y++;
                    }
                }
                // only change seats (not floor)
                if (after[i][j] == 'L' && occSeats == 0) {
                    after[i][j] = '#';
                } else if (after[i][j] == '#' && occSeats >= 5) {
                    after[i][j] = 'L';                   
                }
            }
        }
        return after;
    }   
    
    public static void main(String[] args) {
        try {
            System.out.println(getNoOfOccupiedSeats());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
