package com.uniovi.socialnetwork.repositories;

import com.uniovi.socialnetwork.entities.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogsRepository extends CrudRepository<Log,Long> {

    List<Log> findByType(String type);
}
