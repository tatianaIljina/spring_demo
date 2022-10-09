package de.imc.test.demo.dal.services;

import de.imc.test.demo.dal.entities.TaskEntity;
import de.imc.test.demo.dal.repository.TaskRepository;
import de.imc.test.demo.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    // @Autowired -- default action
    // public TaskService(TaskRepository repository) {
    //    this.repository = repository;
    // }

    public TaskDto addTask(TaskDto dto) {
        final TaskEntity entity = convertToEntity(dto);
        final TaskEntity saved = this.repository.save(entity);
        return convertToDto(saved);
    }

    public TaskDto getTask(int id) {
        final TaskEntity entity = this.repository.getReferenceById(id);
        return convertToDto(entity);
    }

    public void deleteTask(int id) {
        this.repository.deleteById(id);
    }

    public TaskDto updateTask(TaskDto dto) {
        final TaskEntity entity = this.repository.getReferenceById(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        final TaskEntity saved = this.repository.save(entity);
        return convertToDto(saved);
    }

    private TaskEntity convertToEntity(TaskDto dto){
        final TaskEntity entity = new TaskEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreated(dto.getCreated());
        entity.setUpdated(dto.getUpdated());
        return entity;
    }

    private TaskDto convertToDto(TaskEntity entity){
        final TaskDto dto = new TaskDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }
}
