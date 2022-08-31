import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserEncryptor {
    private static final String DELIMITER = ",";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private static void userEncryptor() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int cyphersQuantity = 0;
        cyphersQuantity = Integer.parseInt(reader.readLine());

        for (int userNum = 0; userNum < cyphersQuantity; userNum++) {


            String[] userInfo = reader.readLine().split(DELIMITER);

            Set<Character> nameCharacters = userInfo[0].chars()
                    .mapToObj(e -> (char) e).collect(Collectors.toSet());
            Set<Character> surnameCharacters = userInfo[1].chars()
                    .mapToObj(e -> (char) e).collect(Collectors.toSet());
            Set<Character> fatherCharacters = userInfo[2].chars()
                    .mapToObj(e -> (char) e).collect(Collectors.toSet());

            Set<Character> totalSet = new HashSet<>();
            totalSet.addAll(nameCharacters);
            totalSet.addAll(surnameCharacters);
            totalSet.addAll(fatherCharacters);

            int birthNumber = 0;
            for (int day = Integer.parseInt(userInfo[3]); day > 0; day /= 10) {
                birthNumber += day % 10;
            }
            for (int month = Integer.parseInt(userInfo[4]); month > 0; month /= 10) {
                birthNumber += month % 10;
            }

            int cypherValue = totalSet.size()
                    + birthNumber * 64
                    + (ALPHABET.indexOf(userInfo[0].toLowerCase().toCharArray()[0]) + 1) * 256;

            String hex = Integer.toHexString(cypherValue);
            if (hex.length() >= 4) {
                hex = hex.substring(hex.length() - 3);
            } else while (hex.length() != 3) {
                hex = "0".concat(hex);
            }
            System.out.print(hex.toUpperCase() + " ");
        }
    }

    public static void main(String[] args) {
        try {
            userEncryptor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
