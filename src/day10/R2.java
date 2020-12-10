package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class R2 {

    public static long getNoOfWaysToCharge() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day10input.txt");
        List<String> input = Files.readAllLines(path);
        List<Integer> adapters = input.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        // add charging outlet (0) at beginning and device (highest adapter + 3)
        adapters.add(0, 0);
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        int oneJoltsCounter = 0;
        List<Integer> noOfOneJolts = new ArrayList<>(); 
        // calculate how many series of oneJolts are in the list
        for (int i = 0; i < adapters.size() - 1; i++) {
            int diff = adapters.get(i + 1) - adapters.get(i);
            if (diff == 1) {
                oneJoltsCounter++;
            } else {
                if (oneJoltsCounter != 0) {
                    noOfOneJolts.add(oneJoltsCounter);
                oneJoltsCounter = 0;
                }               
            } 
        }
        long waysToCharge = 1;
        for (Integer oneJolts : noOfOneJolts) {
            // ratio series of oneJolts : possibilities is
            // 1:1     2:2     3:4     4:7     5:11
            // --> n * (n-1) / 2 + 1
            waysToCharge *= oneJolts * (oneJolts - 1) / 2 + 1;
        }
        return waysToCharge;        
    }

    public static void main(String[] args) {
        try {
            System.out.println(getNoOfWaysToCharge());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
