package com.tmx.logwatcher;

import com.tmx.logwatcher.entities.LogWatcher;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface LogWatcherRepository extends CrudRepository<LogWatcher, Long> {

    List<LogWatcher> findByTimestamp(Timestamp timestamp);

    List<LogWatcher> findAllByOrderByTimestampDesc(Pageable pageable);

}
