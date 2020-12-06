package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static long getYesAnswers() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day6input.txt");
        List<String> input = Files.readAllLines(path);
        StringBuilder builder = new StringBuilder();
        int yesCount = 0;
        for(String line : input) {
            if(line.equals("")) {
                yesCount += builder.chars().distinct().count();
                builder.setLength(0);
            } else {
                builder.append(line);
            }
        }
        return yesCount;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getYesAnswers());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
