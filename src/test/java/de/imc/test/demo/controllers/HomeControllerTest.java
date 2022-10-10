package de.imc.test.demo.controllers;

import de.imc.test.demo.dal.entities.UserEntity;
import de.imc.test.demo.dal.repository.UserRepository;
import de.imc.test.demo.dto.TaskDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase
class HomeControllerTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private TestRestTemplate rest;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init(){
        final UserEntity user = new UserEntity();
        user.setUsername("test_login");
        user.setPassword("$2a$12$fmh.RjZoZDlXFOJnT5PGE.D5ru64lwnhXGsOSrcwko0f57acj.3p.");
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Test
    void postTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("title 1");

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("test_login", "test_password");
        final HttpEntity<TaskDto> entity = new HttpEntity<TaskDto>(taskDto, httpHeaders);
        //act
        final ResponseEntity<TaskDto> response = rest.postForEntity("/tasks/", entity, TaskDto.class);
        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}