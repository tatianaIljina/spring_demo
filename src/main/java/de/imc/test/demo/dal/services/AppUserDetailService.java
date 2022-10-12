package de.imc.test.demo.dal.services;

import de.imc.test.demo.configuration.security.AppUserDetails;
import de.imc.test.demo.dal.entities.UserEntity;
import de.imc.test.demo.dal.repository.UserRepository;
import de.imc.test.demo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new AppUserDetails(userEntity);
    }

    public UserDto getUser(int id) {
        final UserEntity entity = this.userRepository.getReferenceById(id);
        return convertToDto(entity);
    }

    public UserDto addUser(UserDto dto) {
        final UserEntity entity = convertToEntity(dto);
        final UserEntity saved = this.userRepository.save(entity);
        return convertToDto(saved);
    }

    private UserEntity convertToEntity(UserDto dto){
        final UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        final String bcryptPassword = encoder.encode(dto.getPassword());
        entity.setPassword(bcryptPassword);
        entity.setUsername(dto.getUsername());
        entity.setEnabled(true);
        return entity;
    }

    private UserDto convertToDto(UserEntity entity){
        final UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEnabled(entity.isEnabled());
        return dto;
    }
}
