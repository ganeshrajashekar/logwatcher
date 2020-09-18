package com.tmx.logwatcher.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String category;
    private String type;
    private String text;
    private String level;

    public Rule(@NonNull String category, @NonNull String type, @NonNull String text, @NonNull String level) {
        this.category=category;
        this.type=type;
        this.text=text;
        this.level=level;
    }
    @Override
    public String toString(){
        return "Rule Info is "+category+ " "+ type +" "+ text+" "+level;
    }
}

