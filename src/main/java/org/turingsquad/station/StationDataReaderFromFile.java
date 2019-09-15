package org.turingsquad.station;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public abstract class StationDataReaderFromFile {
    protected final String fileName;

    public abstract List<StationData> read();
    public abstract void init() throws IOException;
}
