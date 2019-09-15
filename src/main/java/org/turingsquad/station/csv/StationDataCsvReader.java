package org.turingsquad.station.csv;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.turingsquad.station.StationData;
import org.turingsquad.station.StationDataReaderFromFile;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StationDataCsvReader extends StationDataReaderFromFile {
    private CSVParser parser;

    public StationDataCsvReader(String fileName) {
        super(fileName);
    }

    @Override
    public List<StationData> read() {
        List<StationData> resultList = new ArrayList<>();
        for (CSVRecord record : parser) {
            int temperature = Integer.parseInt(record.get("temperature"));
            long epochMillis = Long.parseLong(record.get("epochTime"));
            resultList.add(new StationData(record.get("name"), temperature, epochMillis));
        }
        return resultList;
    }

    @Override
    public void init() throws IOException {
        parser = new CSVParser(new FileReader(fileName), CSVFormat.DEFAULT.withHeader());
        log.info("Initiated csv parser for " + fileName);
    }
}
