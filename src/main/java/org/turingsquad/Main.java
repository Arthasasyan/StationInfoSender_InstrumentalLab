package org.turingsquad;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        String fileName;
        if(args.length == 0) {
            log.info("No args provided");
            fileName = "station.csv";
        } else {
            fileName = args[0];
        }
        log.info("File name: " + fileName);
    }
}