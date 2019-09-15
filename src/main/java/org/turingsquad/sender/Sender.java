package org.turingsquad.sender;

import org.turingsquad.station.StationData;

import java.util.List;

public interface Sender {
    boolean send(List<StationData> dataList);
}
