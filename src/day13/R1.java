package day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static int getThatStupidBusToTheAirport() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day13input.txt");
        List<String> input = Files.readAllLines(path);
        int timestamp = Integer.parseInt(input.get(0));
        int earliestDpt = Integer.MAX_VALUE;
        int earliestBusNo = 0;
        String[] busses = input.get(1).split(",");
        for (String bus : busses) {
            if (bus.charAt(0) != 'x') {
                int busNo = Integer.parseInt(bus);
                int nextDpt = busNo - (timestamp % busNo);
                if (nextDpt < earliestDpt) {
                    earliestDpt = nextDpt;
                    earliestBusNo = busNo;
                }
            }
        }
        System.out.println("earliest departure in " + earliestDpt + " min with bus no. " + earliestBusNo);
        return earliestBusNo * earliestDpt;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getThatStupidBusToTheAirport());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
