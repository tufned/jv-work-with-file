package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String SUPPLY_LABEL = MarketItemType.SUPPLY.toString().toLowerCase();
    private static final String BUY_LABEL = MarketItemType.BUY.toString().toLowerCase();
    private static final String RESULT_LABEL = "result";
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getStatistic(String fromFileName, String toFileName) {
        MarketData marketData = new MarketData();

        readFromFile(fromFileName, (line) -> {
            String[] lineData = line.split(",");
            String type = lineData[0].toLowerCase().trim();
            int value = Integer.parseInt(lineData[1].trim());
            if (type.equals(SUPPLY_LABEL)) {
                marketData.addSupplyTotal(value);
            } else if (type.equals(BUY_LABEL)) {
                marketData.addBuyTotal(value);
            }
        });

        String statistic = getStatisticString(marketData);
        writeStatisticToFile(toFileName, statistic);

        return statistic;
    }

    private void readFromFile(String fileName, LineProcessor processor) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processor.execute(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    private String getStatisticString(MarketData data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(SUPPLY_LABEL).append(',').append(data.getSupplyTotal())
                .append(LINE_SEPARATOR)
                .append(BUY_LABEL).append(',').append(data.getBuyTotal())
                .append(LINE_SEPARATOR)
                .append(RESULT_LABEL).append(',').append(data.getResult());
        return stringBuilder.toString();
    }

    private void writeStatisticToFile(String fileName, String statistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(statistic);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
