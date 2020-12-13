package day13;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R2 {

    public static BigInteger getTimestampFromTheseStupidBusses() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day13input.txt");
        List<String> input = Files.readAllLines(path);
        String[] busses = input.get(1).split(",");
        Map<Integer, Integer> busMap = new HashMap<>(); // map with bus no. and offset mod busNo
        BigInteger M = BigInteger.ONE;     
        for (int i = 0; i < busses.length; i++) {
            if (busses[i].charAt(0) != 'x') {
                Integer busNo = Integer.valueOf(busses[i]);
                busMap.put(busNo, (busNo - i) % busNo); // timestamp === negative offset mod busNo
                M = M.multiply(BigInteger.valueOf(busNo));
            }
        }
        // chinese remainder theorem (Teschl Band 1, S. 104/105)
        BigInteger timestamp = BigInteger.ZERO;
        for (Integer busNo : busMap.keySet()) {
            BigInteger Mk = M.divide(BigInteger.valueOf(busNo));
            BigInteger Nk = Mk.modInverse(BigInteger.valueOf(busNo));
            BigInteger combo = BigInteger.valueOf(busMap.get(busNo)).multiply(Mk).multiply(Nk);
            timestamp = timestamp.add(combo);
        }        
        return timestamp.mod(M);
    }

    public static void main(String[] args) {
        try {
            System.out.println(getTimestampFromTheseStupidBusses());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
