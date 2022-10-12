package de.imc.test.demo.controllers;

import de.imc.test.demo.dal.services.AppUserDetailService;
import de.imc.test.demo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserDetailService service;

    @GetMapping("/{id}")
    public UserDto get(@PathVariable int id) {
        return this.service.getUser(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto dto) {
        return this.service.addUser(dto);
    }
}
