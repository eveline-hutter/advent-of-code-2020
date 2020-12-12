package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R2 {

    public static int getManhattanDistance() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day12input.txt");
        List<String> input = Files.readAllLines(path);
        int wpN = 1;
        int wpE = 10;
        int north = 0;
        int east = 0;
        for (String instr : input) {
            char dir = instr.charAt(0);
            int units = Integer.parseInt(instr.substring(1));
            switch (dir) {
            case 'N':
                wpN += units;
                break;
            case 'S':
                wpN -= units;
                break;
            case 'E':
                wpE += units;
                break;
            case 'W':
                wpE -= units;
                break;
            case 'L':
                int tmpL = wpN;
                switch (units) {
                case 90:
                    wpN = wpE;
                    wpE = tmpL * -1;
                    break;
                case 180:
                    wpN *= -1;
                    wpE *= -1;
                    break;
                case 270:
                    wpN = wpE * -1;
                    wpE = tmpL;
                    break;
                default:
                    System.out.println("could not decode units");
                    break;
                }
                break;
            case 'R':
                int tmpR = wpN;
                switch (units) {
                case 90:                    
                    wpN = wpE * -1;
                    wpE = tmpR;
                    break;
                case 180:
                    wpN *= -1;
                    wpE *= -1;
                    break;
                case 270:
                    wpN = wpE;
                    wpE = tmpR * -1;
                    break;
                default:
                    System.out.println("could not decode units");
                    break;
                }                
                break;
            case 'F':
                north += units * wpN;
                east += units * wpE;
                break;
            default:
                System.out.println("could not decode direction");
                break;                                
            }
        }
        System.out.println("n: " + north);
        System.out.println("e: " + east);
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
