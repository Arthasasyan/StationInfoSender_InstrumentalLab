package org.turingsquad.sender;

import com.axibase.tsd.client.DataService;
import com.axibase.tsd.client.HttpClientManager;
import com.axibase.tsd.model.data.command.AddSeriesCommand;
import com.axibase.tsd.model.data.series.Sample;
import com.axibase.tsd.model.system.ClientConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.turingsquad.station.StationData;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AtsdSender implements Sender {
    private final DataService atsdDataService;

    public AtsdSender(String url, String username, String password) {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder(url, username, password).
                build();
        atsdDataService = new DataService(new HttpClientManager(clientConfiguration));
    }

    @Override
    public boolean send(List<StationData> dataList) {
        final List<AddSeriesCommand> seriesList = dataList.stream()
                .map(this::toCommand)
                .collect(Collectors.toList());
        for(AddSeriesCommand command: seriesList) {
            boolean inserted = atsdDataService.addSeries(command);
            if(!inserted) {
                log.warn("Failed to insert command in ATSD");
                return false;
            }
        }
        return true;
    }

    private AddSeriesCommand toCommand(StationData data) {
        AddSeriesCommand series = new AddSeriesCommand();
        series.setMetricName("temperature");
        series.setEntityName(data.getName());
        Sample sample = Sample.ofTimeDouble(data.getEpochDate(), data.getTemperature());
        series.addSeries(sample);
        return series;
    }
}
