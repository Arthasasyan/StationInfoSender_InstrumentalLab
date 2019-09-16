package org.turingsquad.csv;

import org.testng.annotations.Test;
import org.turingsquad.station.StationData;
import org.turingsquad.station.StationDataReaderFromFile;
import org.turingsquad.util.Mocks;

import java.util.List;

import static org.testng.AssertJUnit.*;
import static org.testng.Assert.assertThrows;

public class CsvFormatTest {

    @Test
    public void testIncorrectFile() throws Exception {
        StationDataReaderFromFile reader = Mocks.csvReaderForIncorrectFile();
        assertThrows(reader::read);
    }

    @Test
    public void testCorrectFile() throws Exception {
        StationDataReaderFromFile reader = Mocks.csvReaderForCorrectFile();
        assertNotNull(reader.read());
    }

    @Test
    public void testReadData() throws Exception {
        StationDataReaderFromFile reader = Mocks.csvReaderForCorrectFile();
        List<StationData> stationDataList = reader.read();
        assertEquals(1, stationDataList.size());
        StationData expectedData = Mocks.CORRECT_STATION_DATA;
        assertEquals(expectedData, stationDataList.get(0));
    }
}
