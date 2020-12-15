package day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R2 {

    public static long getMemorySum() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day14input.txt");
        List<String> input = Files.readAllLines(path);
        Map<Long, Integer> memory = new HashMap<>(); // key: memory address
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
                String binAddress = Integer.toBinaryString(address); // binary representation of memory address
                // fill binary string with leading zeros up to a length of 36 bits
                String binLen36 = new String(new char[36 - binAddress.length()]).replace('\0', '0') + binAddress;
                // apply bitmask and save positions of floating bits ('X')
                List<Integer> floatingBits = new ArrayList<>();
                for (int i = 0; i < mask.length; i++) {                        
                    if (mask[i] == '1') {
                        binLen36 = binLen36.substring(0, i) + '1' + binLen36.substring(i + 1);
                    } else if (mask[i] == 'X') {
                        floatingBits.add(0, 35 - i); // save floating bits reversed --> 2^pos
                        binLen36 = binLen36.substring(0, i) + '0' + binLen36.substring(i + 1); // replace 'X' with O
                    }                   
                }
                long offset = Long.parseLong(binLen36, 2); // first address (all 'X' = O)
                long[] allAddresses = getAllVariations(floatingBits, offset);
                // write updated value to memory                
                for (Long pos : allAddresses) {
                    if (memory.containsKey(pos)) {
                        memory.remove(pos);
                    }
                    memory.put(pos, value);
                }                
            }            
        }
        long memorySum = 0;
        for (Long key : memory.keySet()) {
            memorySum += memory.get(key);
        } 
        return memorySum;
    }

    public static long[] getAllVariations(List<Integer> floatingBits, long offset) {
        long[] variations = new long[(int) Math.pow(2, floatingBits.size())]; // no. of variations: 2 ^ no. of 'X'
        for (int i = 0; i < floatingBits.size(); i++) {
            int j = 0;
            boolean swapZeroOne = false;
            while (j < variations.length) {
                if (!swapZeroOne) {
                    variations[j] += Math.pow(2, floatingBits.get(i));
                } 
                if ( (j + 1) % Math.pow(2, i) == 0) {
                    swapZeroOne = !swapZeroOne;
                }
                j++;
            }
        }
        for (int i = 0; i < variations.length; i++) {
            variations[i] += offset;
        }
        return variations;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getMemorySum());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
