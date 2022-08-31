import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class RocketTaxi {
    private static final String DELIMITER = " ";

    private static void RocketTaxi() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<Integer> rockets = new HashSet<>();

        Map<Integer, Integer> values = new TreeMap<>();

        List<String> logs = new ArrayList<>();

        int logsQuantity = Integer.parseInt(reader.readLine());

        for (int logNumber = 0; logNumber < logsQuantity; logNumber++) {
            String logInfo = reader.readLine();
            rockets.add(Integer.parseInt(logInfo.split(DELIMITER)[3]));
            logs.add(logInfo);
        }
        List<List<String>> logsPerRocket = new ArrayList<>();

        for (int rocket = 0; rocket < rockets.size(); rocket++) {
            int finalRocket = rocket;
            logsPerRocket.add(
                    logs.stream()
                            .filter(str -> str.contains(" " + rockets.toArray()[finalRocket] + " "))
                            .collect(Collectors.toList()));
        }

        for(int rocket = 0; rocket < logsPerRocket.size(); rocket++){
            List<String> currentRocket = new ArrayList<>(logsPerRocket.get(rocket));

            currentRocket.sort(Comparator
                    .comparingInt((String o) -> Integer.parseInt(o.split(DELIMITER)[0]))
                    .thenComparing((String o) -> Integer.parseInt(o.split(DELIMITER)[1]))
                    .thenComparing((String o) -> Integer.parseInt(o.split(DELIMITER)[2])));

            int totalMinutes = 0, startMinutes = 0;

            for(String log : currentRocket){
                String[] logSplit =  log.split(DELIMITER);
                switch(logSplit[4]){
                    case "A" -> startMinutes = Integer.parseInt(logSplit[0]) * 1440 + Integer.parseInt(logSplit[1]) * 60 + Integer.parseInt(logSplit[2]);
                    case "S", "C" -> totalMinutes += (Integer.parseInt(logSplit[0]) * 1440
                            + Integer.parseInt(logSplit[1]) * 60
                            + Integer.parseInt(logSplit[2])) - startMinutes;
                }

            }
            values.put(Integer.parseInt(currentRocket.get(0).split(DELIMITER)[3]), totalMinutes);
        }
        for (int value:
             values.values()) {
            System.out.print(value + " ");
        }
    }

    public static void main(String[] args) {
        try {
            RocketTaxi();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
