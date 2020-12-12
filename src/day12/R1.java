package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static int getManhattanDistance() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day12input.txt");
        List<String> input = Files.readAllLines(path);
        int north = 0;
        int east = 0;
        int currDir = 90;        // 90 = east, 180 = south, 270 = west, 360 = north
        for (String instr : input) {
            char dir = instr.charAt(0);
            int units = Integer.parseInt(instr.substring(1));
            switch (dir) {
            case 'N':
                north += units;
                break;
            case 'S':
                north -= units;
                break;
            case 'E':
                east += units;
                break;
            case 'W':
                east -= units;
                break;
            case 'L':
                currDir = (currDir + 360 - units) % 360;
                break;
            case 'R':
                currDir = (currDir + 360 + units) % 360;
                break;
            case 'F':
                switch (currDir) {
                case 0:
                    north += units;
                    break;
                case 90:
                    east += units;
                    break;
                case 180:
                    north -= units;
                    break;
                case 270:
                    east -= units;
                    break;
                default:
                    System.out.println("could not decode current direction" + currDir);
                }
                break;
            default:
                System.out.println("could not decode direction");
                break;                                
            }
        }
        return Math.abs(north) + Math.abs(east);        
    }

    public static void main(String[] args) {
        try {
            System.out.println(getManhattanDistance());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
