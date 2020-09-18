package com.tmx.logwatcher.entities;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LogWatcher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @CreationTimestamp
    private Timestamp timestamp;
    private String message;
    private String source;
    private String level;
    private String machine;

    public LogWatcher(@NonNull String message, @NonNull String source, @NonNull String level, @NonNull String machine) {
        this.message= message;
        this.source= source;
        this.level= level;
        this.machine= machine;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
