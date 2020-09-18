package com.tmx.logwatcher;

import com.tmx.logwatcher.categorizer.Categorizer;
import com.tmx.logwatcher.entities.LogWatcher;
import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class LogwatcherApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LogwatcherApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(LogwatcherApplication.class);

    @Autowired
    private LogWatcherRepository logWatcherRepository;

    @Autowired
    private Categorizer categorizer;

    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        logWatcherRepository.save(new LogWatcher("[request_id=03543cf33966458bba006db6f0428723] LSH_STORE missing mandatory attribute.", "portal", "INFO", "PE1/otin1001"));
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logWatcherRepository.save(new LogWatcher("[request_id=03543cf33966458bba006db6f0428723] LSH_STORE missing mandatory attribute2.", "portal", "INFO", "PE1/otin1001"));

        System.out.println("\nfindAll()");
        logWatcherRepository.findAll().forEach(x -> System.out.println(x));
        AtomicReference<Long> xs = new AtomicReference<>(0L);
        System.out.println("\nfindById(1L)");
        logWatcherRepository.findById(1l).ifPresent(x -> {
            System.out.println(x);
            xs.set(x.getTimestamp().getTime());
        });

        System.out.println("\nfindByTimeStamp('timestamp')");
        logWatcherRepository.findByTimestamp(new Timestamp(xs.get())).forEach(x -> System.out.println(x));

        // Find last 1000 records based on timestamp
        Pageable pageable = PageRequest.of(0, 1000, Sort.Direction.ASC,"timestamp");

        logWatcherRepository.findAllByOrderByTimestampDesc(pageable).forEach(x -> System.out.println(x));

        //Pass values from From DB -> Categorizer();
        categorizer.compute(logWatcherRepository.findAllByOrderByTimestampDesc(pageable));
    }
}