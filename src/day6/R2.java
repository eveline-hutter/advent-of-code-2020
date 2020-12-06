package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R2 {

    public static long getCollectiveYesAnswers() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day6input.txt");
        List<String> input = Files.readAllLines(path);
        String collectiveYes = "";
        int yesCount = 0;
        boolean newWord = true;
        for(String line : input) {
            if(line.equals("")) {
                yesCount += collectiveYes.length();
                System.out.println(collectiveYes + ":" + collectiveYes.length());
                collectiveYes = "";
                newWord = true;
            } else {
                if (newWord) {
                    collectiveYes = line;
                    newWord = false;
                } else {
                    StringBuilder builder = new StringBuilder();
                    for(int i = 0; i < line.length(); i++) {
                        if (collectiveYes.contains("" + line.charAt(i))) {
                            builder.append(line.charAt(i));
                        }
                    }
                    collectiveYes = builder.toString();
                }
            }
        }        
        return yesCount;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getCollectiveYesAnswers());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
