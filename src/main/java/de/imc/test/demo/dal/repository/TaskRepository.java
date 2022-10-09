package de.imc.test.demo.dal.repository;

import de.imc.test.demo.dal.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    // Optional<TaskEntity> GetById(int id);
}
