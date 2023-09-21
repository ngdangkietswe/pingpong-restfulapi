package dev.ngdangkiet.pingpongrestfulapi.csv;

import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

@Component
public class CsvHelper {
    public static ByteArrayInputStream customer2CSV(List<CustomerDTO> customers) {
        String[] headers = {"ID", "Customer Name", "Customer Email", "Gender", "Age"};
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(false)
                .build();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(); CSVPrinter printer = new CSVPrinter(new PrintWriter(out), csvFormat)) {
            List<List<String>> records = new ArrayList<>();
            customers.forEach(i -> records.add(Arrays.asList(
                    String.valueOf(i.id()),
                    i.name(),
                    i.email(),
                    i.gender(),
                    String.valueOf(i.age())
            )));
            printer.printRecords(records);
            printer.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException ex) {
            throw new RuntimeException("Fail to export data to CSV: " + ex.getMessage());
        }
    }
}
