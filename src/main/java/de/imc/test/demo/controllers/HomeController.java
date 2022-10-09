package de.imc.test.demo.controllers;

import de.imc.test.demo.dal.services.TaskService;
import de.imc.test.demo.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/")
@RequiredArgsConstructor
public class HomeController {

    private final TaskService service;

    @GetMapping("/{id}")
    public TaskDto get(@PathVariable int id) {
        return this.service.getTask(id);
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto dto) {
        return this.service.addTask(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.service.deleteTask(id);
    }

    @PatchMapping
    public TaskDto update(@RequestBody TaskDto dto) {
        return this.service.updateTask(dto);
    }
}