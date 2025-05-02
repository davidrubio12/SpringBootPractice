package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.security;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.TaskDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Task;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.TaskService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")

public class TaskController {
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    // Obtiene las tareas del usuario.
    @GetMapping
    public ResponseEntity<List<TaskDto>> getUserTasks() {
        List<TaskDto> tasks = mapToDto(taskService.findAllUserTasks());
        return ResponseEntity.ok(tasks);
    }

    private TaskDto mapToDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    private List<TaskDto> mapToDto(List<Task> entities) {
        Type listType = new TypeToken<List<TaskDto>>() {
        }.getType();
        return modelMapper.map(entities, listType);
    }

}
