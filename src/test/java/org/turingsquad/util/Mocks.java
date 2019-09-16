package org.turingsquad.util;

import lombok.experimental.UtilityClass;
import org.turingsquad.sender.AtsdSender;
import org.turingsquad.sender.Sender;
import org.turingsquad.station.StationData;
import org.turingsquad.station.StationDataReaderFromFile;
import org.turingsquad.station.csv.StationDataCsvReader;

@UtilityClass
public class Mocks {
    private static final TestConfig TEST_CONFIG = TestConfig.INSTANCE;
    public static final Sender ATSD_SENDER = new AtsdSender(TEST_CONFIG.getAtsdUrl(), TEST_CONFIG.getAtsdUserName(), TEST_CONFIG.getAtsdPassword());
    public static final StationData CORRECT_STATION_DATA = new StationData("br-1905", 60, 1568577444927L);

    public static StationDataReaderFromFile csvReaderForIncorrectFile() throws Exception {
        String file = Mocks.class.getClassLoader().getResource("incorrect_station.csv").getFile();
        return new StationDataCsvReader(file).init();
    }

    public static StationDataReaderFromFile csvReaderForCorrectFile() throws Exception{
        String file = Mocks.class.getClassLoader().getResource("correct_station.csv").getFile();
        return new StationDataCsvReader(file).init();
    }
}
