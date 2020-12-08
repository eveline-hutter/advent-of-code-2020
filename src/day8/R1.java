package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static int getAccValue() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day8input.txt");
        List<String> input = Files.readAllLines(path);
        boolean[] visitedInstruction = new boolean[input.size()];
        int acc = 0;
        int i = 0;
        while(!visitedInstruction[i]) {
            visitedInstruction[i] = true;
            String instr = input.get(i);
            String cmd = instr.substring(0, 3);
            char operator = instr.charAt(4);
            int value = Integer.parseInt(instr.substring(5, instr.length()));
            switch(cmd) {
            case "acc":
                acc = (operator == '+') ? acc + value : acc - value;
                i++;
                break;
            case "jmp":
                i = (operator == '+') ? i + value : i - value;               
                break;
            case "nop":
                i++;
                break;
            default:
                System.out.println("wrong instruction command: " + cmd);
            }            
        }        
        return acc;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getAccValue());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
