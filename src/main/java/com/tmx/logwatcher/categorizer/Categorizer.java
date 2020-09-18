package com.tmx.logwatcher.categorizer;

import com.tmx.logwatcher.RuleRepository;
import com.tmx.logwatcher.entities.LogWatcher;
import com.tmx.logwatcher.entities.Rule;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class Categorizer {
    private static final Logger log = LoggerFactory.getLogger(Categorizer.class);

    @Autowired
    private RuleRepository ruleRepository;

    @Getter
    private List<Rule> rulesList;

    @PostConstruct
    public void init() {
        ruleRepository.save(new Rule("LSH", "contains", "LSH_STORE", "ERROR"));
        ruleRepository.save(new Rule("LSH ERRORS", "regex", "LSH_STORE", "ERROR"));
        ruleRepository.save(new Rule("LSH INFO", "contains", ".*LSH_STORE.*", "INFO"));
        rulesList = ruleRepository.findAll();

    }

    public List<LogWatcher> compute(@NonNull List<LogWatcher> fetchedFromDB) {

        log.info("Inside Copute method------------------------->START");
        getRulesList().stream().forEach(x -> System.out.println(x));

        //Core Logic of Categorizer goes here
        //
        log.info("Inside Copute method------------------------->END");
        return null;
    }
}
