package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserUserId(Long userId);
}
