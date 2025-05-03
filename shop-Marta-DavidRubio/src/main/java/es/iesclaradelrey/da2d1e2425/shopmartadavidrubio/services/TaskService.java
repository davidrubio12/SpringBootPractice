package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllUserTasks();
}