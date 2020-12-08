package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class R2 {

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
    
    public static boolean isPasswordStructureValid(String pw) {
        if (!pw.contains("byr:") || !pw.contains("iyr:") || !pw.contains("eyr:") ||
            !pw.contains("hgt:") || !pw.contains("hcl:") || !pw.contains("ecl:") ||
            !pw.contains("pid:")) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean isPasswordValid(String pw) {
        for (int i = 0; i < pw.length() - 3; i++) {
            String code = pw.substring(i, i + 4);
            if (code.equals("byr:")) {
                int end = pw.length() - 1;
                for (int j = i + 4; j < pw.length(); j++) {
                    if (Character.isWhitespace(pw.charAt(j))) {
                        end = j;
                        break;
                    }
                }
                String byr = pw.substring(i + 4, end);
                if (Integer.parseInt(byr) < 1920 || Integer.parseInt(byr) > 2002) {
                    System.out.println("byr wrong: " + byr);
                    return false;
                }
            } else if (code.equals("iyr:")) {
                int end = pw.length() - 1;
                for (int j = i + 4; j < pw.length(); j++) {
                    if (Character.isWhitespace(pw.charAt(j))) {
                        end = j;
                        break;
                    }
                }
                String iyr = pw.substring(i + 4, end);
                if (Integer.parseInt(iyr) < 2010 || Integer.parseInt(iyr) > 2020) {
                    System.out.println("iyr wrong: " + iyr);
                    return false;
                }
            } else if (code.equals("eyr:")) {
                int end = pw.length() - 1;
                for (int j = i + 4; j < pw.length(); j++) {
                    if (Character.isWhitespace(pw.charAt(j))) {
                        end = j;
                        break;
                    }
                }
                String eyr = pw.substring(i + 4, end);
                if (Integer.parseInt(eyr) < 2020 || Integer.parseInt(eyr) > 2030) {
                    System.out.println("eyr wrong: " + eyr);
                    return false;
                }
            } else if (code.equals("hgt:")) {
                int end = pw.length() - 1;
                for (int j = i + 4; j < pw.length(); j++) {
                    if (Character.isWhitespace(pw.charAt(j))) {
                        end = j;
                        break;
                    }
                }
                String hgt = pw.substring(i + 4, end);
                if (hgt.charAt(hgt.length() - 2) == 'c' && hgt.charAt(hgt.length() - 1) == 'm') {
                    String cm = hgt.substring(0, hgt.length() - 2);
                    if (Integer.parseInt(cm) < 150 || Integer.parseInt(cm) > 193) {
                        System.out.println("hgt: wrong cm: " + hgt);
                        return false;
                    }
                } else if (hgt.charAt(hgt.length() - 2) == 'i' && hgt.charAt(hgt.length() - 1) == 'n') {
                    String in = hgt.substring(0, hgt.length() - 2);
                    if (Integer.parseInt(in) < 59 || Integer.parseInt(in) > 76) {
                        System.out.println("hgt: wrong in: " + hgt);
                        return false;
                    }
                } else {
                    System.out.println("hgt: wrong ending: " + hgt);
                    return false;
                }
            } else if (code.equals("hcl:")) {
                int end = pw.length() - 1;
                for (int j = i + 4; j < pw.length(); j++) {
                    if (Character.isWhitespace(pw.charAt(j))) {
                        end = j;
                        break;
                    }
                }
                String hcl = pw.substring(i + 4, end);
                if (!hcl.matches("#[0-9a-f]{6}")) {
                    System.out.println("hcl wrong: " + hcl);
                    return false;
                }
            } else if (code.equals("ecl:")) {
                int end = pw.length() - 1;
                for (int j = i + 4; j < pw.length(); j++) {
                    if (Character.isWhitespace(pw.charAt(j))) {
                        end = j;
                        break;
                    }
                }
                String ecl = pw.substring(i + 4, end);
                if (!ecl.matches("amb|blu|brn|gry|grn|hzl|oth")) {
                    System.out.println("ecl wrong: " + ecl);
                    return false;
                }                
            } else if (code.equals("pid:")) {
                int end = pw.length() - 1;
                for (int j = i + 4; j < pw.length(); j++) {
                    if (Character.isWhitespace(pw.charAt(j))) {
                        end = j;
                        break;
                    }
                }
                String pid = pw.substring(i + 4, end);
                if (!pid.matches("[0-9]{9}")) {
                    System.out.println("pid wrong: " + pid);
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            List<String> passwords = getPasswords();
            List<String> passwordsToValidate = new ArrayList<>();
            for (String password : passwords) {
                if (isPasswordStructureValid(password)) {
                    passwordsToValidate.add(password);
                }
            }
            int validPasswords = 0;
            for (String password : passwordsToValidate) {
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
