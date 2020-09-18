package com.tmx.logwatcher;

import com.tmx.logwatcher.entities.Rule;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface RuleRepository extends CrudRepository<Rule, Long> {

    List<Rule> findAllById(@NonNull Long id);
    List<Rule> findAll();
}
