package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class R1 {
    
    public static List<String> getPasswords() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day4input.txt");
        List<String> input = Files.readAllLines(path);
        List<String> passwords = new ArrayList<>();
        StringBuilder pwBuilder = new StringBuilder();
        for (String data : input) {
            if (!data.equals("")) {
                pwBuilder.append(data).append(" ");
            } else {
                passwords.add(pwBuilder.toString());
                pwBuilder.setLength(0); // empty string
            }
        }
        return passwords;
    }
    
    public static boolean isPasswordValid(String pw) {
        if (!pw.contains("byr:") || !pw.contains("iyr:") || !pw.contains("eyr:") ||
            !pw.contains("hgt:") || !pw.contains("hcl:") || !pw.contains("ecl:") ||
            !pw.contains("pid:")) {
            return false;
        } else {
            return true;
        }
    }
    
    public static void main(String[] args) {
        try {
            int validPasswords = 0;
            List<String> passwords = getPasswords();
            for (String password : passwords) {
                if (isPasswordValid(password)) {
                    validPasswords++;
                }
            }
            System.out.println(validPasswords);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
