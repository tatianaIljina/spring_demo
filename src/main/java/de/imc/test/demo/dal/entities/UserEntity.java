package de.imc.test.demo.dal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private boolean isEnabled;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
}
