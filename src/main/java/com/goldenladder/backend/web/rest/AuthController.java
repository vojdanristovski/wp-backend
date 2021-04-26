package com.goldenladder.backend.web.rest;

import com.goldenladder.backend.config.jwt.JwtResponse;
import com.goldenladder.backend.config.jwt.JwtUtils;
import com.goldenladder.backend.model.Role;
import com.goldenladder.backend.model.User;
import com.goldenladder.backend.model.dto.UserDto;
import com.goldenladder.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "https://localhost:3000")
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
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            SecurityContextHolder.getContext().setAuthentication(null);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
        }
    }

    @PostMapping("/login/facebook")
    public ResponseEntity<?> authenticateFacebookUser(@Valid @RequestBody UserDto userDto) {

        try {
            User user = this.userService.loadUserByUsername(userDto.getId());
            try{
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userDto.getId(), userDto.getUsername()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception er){
                System.out.println("--------------------------------");
            }
            String jwt = jwtUtils.generateJwtTokenForFacebookUser(userDto);
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDto.getId(),
                    "",
                    user.getRole()));
        } catch (Exception e) {
            // user not found
            try {
                // save in two tables
                User user = this.userService.saveFacebookUser(userDto);
                String jwt = jwtUtils.generateJwtTokenForFacebookUser(userDto);
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userDto.getId(), userDto.getUsername()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok(new JwtResponse(jwt,
                        userDto.getId(),
                        "",
                        user.getRole()));
            } catch (Exception ex) {
                // user not found
                return ResponseEntity.notFound().build();
            }
        }
    }
}