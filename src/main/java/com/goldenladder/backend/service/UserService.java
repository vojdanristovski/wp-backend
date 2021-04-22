package com.goldenladder.backend.service;

import com.goldenladder.backend.model.DateCustom;
import com.goldenladder.backend.model.Role;
import com.goldenladder.backend.model.User;
import com.goldenladder.backend.model.dto.UserDto;
import com.goldenladder.backend.model.exception.InvalidArgumentsException;
import com.goldenladder.backend.model.exception.PasswordsDoNotMatchException;
import com.goldenladder.backend.model.exception.UsernameAlreadyExistsException;
import com.goldenladder.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User updateUser(UserDto dto) {
        User user = this.loadUserByUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setBirthday(DateCustom.getLocalDateTimeFromDateStringDateDate(dto.getBirthday()));
        return this.userRepository.save(user);
    }

    @Transactional
    public Optional<User> save(UserDto dto) {
        if (dto.getUsername()==null || dto.getPassword()==null || dto.getRepeatPassword()==null ||
                dto.getEmail()==null || dto.getBirthday()==null)
            throw new InvalidArgumentsException();
        if (this.userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(dto.getUsername());
        }
        if (!dto.getPassword().equals(dto.getRepeatPassword()))
            throw new PasswordsDoNotMatchException();
        User user = new User(dto.getUsername(), passwordEncoder.encode(dto.getPassword()), dto.getEmail(),
                DateCustom.getLocalDateTimeFromDateStringDateDate(dto.getBirthday()), Role.ROLE_USER);
        this.userRepository.save(user);

        return Optional.of(this.userRepository.save(user));
    }


    private void setUser(User user) {
        User update = this.userRepository.findByUsername(user.getUsername()).get();
        update.setEmail(user.getEmail());
        update.setPassword(passwordEncoder.encode(user.getPassword()));
        update.setRole(user.getRole());
        update.setBirthday(user.getBirthday());
        userRepository.save(update);
    }


}