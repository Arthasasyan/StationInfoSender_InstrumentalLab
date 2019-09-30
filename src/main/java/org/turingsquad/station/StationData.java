package org.turingsquad.station;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StationData {
    private final String name;
    private final double temperature;
    private final long epochDate;
}
