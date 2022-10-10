package de.imc.test.demo.dal.entities;

import de.imc.test.demo.configuration.security.AppUserDetails;
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

    @ElementCollection
    private List<String> roles;
}
