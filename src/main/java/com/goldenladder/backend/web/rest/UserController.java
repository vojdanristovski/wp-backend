package com.goldenladder.backend.web.rest;

import com.goldenladder.backend.model.Movie;
import com.goldenladder.backend.model.Review;
import com.goldenladder.backend.model.User;
import com.goldenladder.backend.model.dto.UserDto;
import com.goldenladder.backend.model.exception.InvalidArgumentsException;
import com.goldenladder.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "https://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    ResponseEntity<User> details(@PathVariable String username) {
        try {
            User user = this.userService.loadUserByUsername(username);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{username}/edit")
    ResponseEntity<User> editUser(@PathVariable String username, @RequestBody UserDto userDto) {
        try {
            User user = this.userService.updateUser(userDto);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{username}/follow/{username2}")
    ResponseEntity<User> followUser(@PathVariable String username, @PathVariable String username2) {
        try {
            this.userService.follow(username, username2);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{username}/unfollow/{username2}")
    ResponseEntity<User> unfollowUser(@PathVariable String username, @PathVariable String username2) {
        try {
            this.userService.unfollow(username, username2);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{username}/favourites")
    ResponseEntity<Set<Movie>> getFavourites(@PathVariable String username) {
        try {
            User user = this.userService.loadUserByUsername(username);
            Set<Movie> set = user.getFaved();
            return ResponseEntity.ok().body(set);
        } catch (InvalidArgumentsException exception) {
            return ResponseEntity.ok().body(new HashSet<Movie>()); // EMPTY
        }
    }

    @GetMapping("/{username}/watchlist")
    ResponseEntity<Set<Movie>> getWatchlist(@PathVariable String username) {
        try {
            User user = this.userService.loadUserByUsername(username);
            Set<Movie> set = user.getWatchlist();
            return ResponseEntity.ok().body(set);
        } catch (InvalidArgumentsException exception) {
            return ResponseEntity.ok().body(new HashSet<Movie>()); // EMPTY
        }
    }

    @GetMapping("/{username}/followers")
    ResponseEntity<List<User>> getFollowers(@PathVariable String username) {
        try {
            User user = this.userService.loadUserByUsername(username);
            List<User> set = user.getFollowers();
            return ResponseEntity.ok().body(set);
        } catch (InvalidArgumentsException exception) {
            return ResponseEntity.ok().body(new ArrayList<User>()); // EMPTY
        }
    }

    @GetMapping("/{username}/following")
    ResponseEntity<List<User>> getFollowing(@PathVariable String username) {
        try {
            User user = this.userService.loadUserByUsername(username);
            List<User> set = user.getFollowing();
            return ResponseEntity.ok().body(set);
        } catch (InvalidArgumentsException exception) {
            return ResponseEntity.ok().body(new ArrayList<User>()); // EMPTY
        }
    }

    public static String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}