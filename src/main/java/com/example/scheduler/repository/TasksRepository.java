package com.example.scheduler.repository;

import com.example.scheduler.Entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findAllByName(String name);

    Tasks findByNameAndLine_id(String name, String line_id);

}
