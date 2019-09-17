package org.turingsquad.station;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public abstract class StationDataReaderFromFile {
    protected final String fileName;
    protected boolean prepared;

    /**
     * Parse data from file
     * @return List of {@link StationData} objects parsed from file
     */
    public abstract List<StationData> read();

    /**
     * Prepare reader to parse data
     * @return
     * @throws IOException if file was not found
     */
    public abstract StationDataReaderFromFile init() throws IOException;
}
