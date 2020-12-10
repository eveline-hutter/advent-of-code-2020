package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class R1 {

    public static int getJoltDifferences() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day10input.txt");
        List<String> input = Files.readAllLines(path);
        // create sorted int list
        List<Integer> adapters = input.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        // add charging outlet (0) at beginning and device (highest adapter + 3)
        adapters.add(0, 0);
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        int oneJolts = 0;
        int threeJolts = 0;
        for (int i = 0; i < adapters.size() - 1; i++) {
            int diff = adapters.get(i + 1) - adapters.get(i);
            if (diff == 1) {
                oneJolts++;
            } else if (diff == 3) {
                threeJolts++;
            }
        }
        System.out.println("one jolts: " + oneJolts + " , three jolts: " + threeJolts);
        return oneJolts * threeJolts;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getJoltDifferences());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
