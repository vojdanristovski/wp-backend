package com.goldenladder.backend.web.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.goldenladder.backend.config.jwt.JwtResponse;
import com.goldenladder.backend.config.jwt.JwtUtils;
import com.goldenladder.backend.model.Role;
import com.goldenladder.backend.model.User;
import com.goldenladder.backend.model.dto.UserDto;
import com.goldenladder.backend.repository.UserRepository;
import com.goldenladder.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDto userDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        Role role = userDetails.getRole();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                role));
//        try {
//            User user = this.userService.login(userDto.getUsername(), userDto.getPassword());
//            List<User> set = user.getFollowing();
//            return ResponseEntity.ok().body(new LogedInUserDto(user,set));
//        } catch (InvalidUserCredentialsException ex) {
//            return ResponseEntity.badRequest().build();
//        }

//        this.userService.save(userDto);
//        return ResponseEntity.ok().build();
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
//    }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            this.userService.save(userDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
        }
    }
}