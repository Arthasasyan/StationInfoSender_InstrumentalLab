package org.turingsquad;

import lombok.extern.slf4j.Slf4j;
import org.turingsquad.sender.Sender;
import org.turingsquad.station.StationData;
import org.turingsquad.station.StationDataReaderFromFile;
import org.turingsquad.station.csv.StationDataCsvReader;
import org.turingsquad.util.Config;

import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception{
        String fileName;
        if(args.length == 0) {
            log.info("No args provided");
            fileName = "station.csv";
        } else {
            fileName = args[0];
        }
        Sender sender = Config.INSTANCE.getSender();
        StationDataReaderFromFile reader = new StationDataCsvReader(fileName);
        reader.init();
        List<StationData> stationDataList = reader.read();
        sender.send(stationDataList);
        log.info("All data successfully sent to {}", Config.INSTANCE.getServerType());
    }
}
