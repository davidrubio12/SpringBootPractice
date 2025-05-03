package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Task;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;

    public TaskServiceImpl(TaskRepository taskRepository, AppUserRepository appUserRepository) {
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<Task> findAllUserTasks() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        AppUser appUser = appUserRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", userName)));

        Long userId = appUser.getUserId();
        return taskRepository.findAllByUserUserId(userId);
    }
}
