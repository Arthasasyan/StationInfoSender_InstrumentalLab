package org.turingsquad.sender;

import org.testng.annotations.Test;
import org.turingsquad.station.StationData;
import org.turingsquad.util.Mocks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class AtsdSenderTest {
    private static final Sender ATSD_SENDER = Mocks.ATSD_SENDER;
    private static final StationData CORRECT_STATION_DATA = Mocks.CORRECT_STATION_DATA;

    @Test
    public void testAtsdSender() {
        assertTrue(ATSD_SENDER.send(Collections.singletonList(CORRECT_STATION_DATA)));
    }

    @Test
    public void testMultipleSamplesSend() {
        List<StationData> stationDataList = Arrays.asList(
          Mocks.CORRECT_STATION_DATA,
          new StationData("br-1906", CORRECT_STATION_DATA.getTemperature(), CORRECT_STATION_DATA.getEpochDate())
        );
        assertTrue(ATSD_SENDER.send(stationDataList));
    }
}
