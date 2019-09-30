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
        super(fileName, false);
    }

    @Override
    public List<StationData> read() {
        if(!prepared) {
            throw new RuntimeException("Reader for " + fileName + " not prepared");
        }
        List<StationData> resultList = new ArrayList<>();
        for (CSVRecord record : parser) {
            double temperature = Double.parseDouble(record.get("temperature").trim());
            long epochMillis = Long.parseLong(record.get("epochMillis").trim());
            resultList.add(new StationData(record.get("name").trim(), temperature, epochMillis));
        }
        return resultList;
    }

    @Override
    public StationDataReaderFromFile init() throws IOException {
        parser = new CSVParser(new FileReader(fileName), CSVFormat.DEFAULT.withHeader());
        log.info("Initiated csv parser for " + fileName);
        prepared = true;
        return this;
    }
}
