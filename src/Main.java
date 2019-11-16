import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Double overTime = 5.0;
        Double timeSample = 0.1;
        Double amplitude = 1.0;
        Double frequency = 0.67;
        Double shift = Math.PI / 2;

        List<Double> results = calculateResults(overTime, timeSample, amplitude, frequency, shift);
        printResults(results);
        exportResultsToCsv(results);
    }

    private static List<Double> calculateResults(Double overTime, Double sample, Double amplitude, Double frequency, Double shift) {
        List<Double> results = new ArrayList<>();
        for (Double i = sample; i < overTime; i = i + sample) {
            Double result = calculateSimpleTon(amplitude, frequency, i, shift);
            results.add(result);
        }
        return results;
    }

    private static void printResults(List<Double> results) {
        int counter = 1;
        for (Double result : results) {

            System.out.println(counter + ". " + result);
            counter++;
        }
    }

    private static void exportResultsToCsv(List<Double> results) throws IOException {
        FileWriter writer = new FileWriter("results.csv");
        StringBuilder stringBuilder = new StringBuilder();
        for(Double result : results){
            stringBuilder.append(result + ",");
        }
        //String collect = results.stream().collect(Collectors.joining(","));
        //System.out.println(collect);

        writer.write(stringBuilder.toString());
        writer.close();
    }

    private static Double calculateSimpleTon(Double amplitude, Double frequency, Double time, Double shift) {
        double pi = Math.PI;
        return amplitude * Math.sin(2 * pi * frequency * time + shift);
    }
}
