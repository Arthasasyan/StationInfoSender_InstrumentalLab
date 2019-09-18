# StationInfoSender_InstrumentalLab

This program is a study project for ITMO University.

## Input format 

CSV file format:

```csv
name,temperature,epochMillis
br-1905,60,1568577444927
```

Station name can be any string.
Pass file name as a aommand line argument.

## Properties format

```properties
protocol=http
serverName=172.17.0.2
port=8088
username=axibase
password=password
serverType=ATSD
```

At the moment the only supported `serverType` is [`ATSD`](https://github.com/axibase/atsd)

## Contributing
Please, write your commit titles in **English**

For test use [`mock.properties`](https://github.com/Arthasasyan/StationInfoSender_InstrumentalLab/blob/master/src/test/resources/mock.properties) file
