package day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R1 {

    public static long getMemorySum() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day14input.txt");
        List<String> input = Files.readAllLines(path);
        Map<Integer, Long> memory = new HashMap<>();
        char[] mask = new char[36];
        for (String line : input) {
            String[] splitLine = line.split(" = ");
            // adjust bitmask
            if (splitLine[0].contains("mask")) {
                for (int i = 0; i < mask.length; i++) {
                    mask[i] = splitLine[1].charAt(i);
                }
            } else {
                int address = Integer.parseInt(splitLine[0].substring(4, splitLine[0].length()-1));
                int value = Integer.parseInt(splitLine[1]);
                String binValue = Integer.toBinaryString(value); // binary representation of value
                // fill binary string with leading zeros up to a length of 36 bits
                String binLen36 = new String(new char[36 - binValue.length()]).replace('\0', '0') + binValue;
                // apply bitmask
                for (int i = 0; i < mask.length; i++) {                        
                    if (mask[i] != 'X') {
                        binLen36 = binLen36.substring(0, i) + mask[i] + binLen36.substring(i + 1);
                    }                     
                }                
                // write updated value to memory
                if (memory.containsKey(address)) {
                    memory.remove(address);
                }
                memory.put(address, Long.parseLong(binLen36, 2));
            }
            
        }
        long memorySum = 0;
        for (Integer key : memory.keySet()) {
            memorySum += memory.get(key);
        } 
        return memorySum;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getMemorySum());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
