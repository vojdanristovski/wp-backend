package com.goldenladder.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Table(name = "`user`")
@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler",
        "password",
        "followers", "following", "faved", "rated", "watchlist",
        "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"})
public class User implements UserDetails {

    @Id
    private String username;

    private String password;

    private String email;

    private LocalDateTime birthday;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "following")
    private List<User> followers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "following",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "following_username"))
    private List<User> following;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "favorites",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "SeriesAndMovies_id"))
    private Set<Movie> faved;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Review> rated;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "watchlist",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "SeriesAndMovies_id"))
    private Set<Movie> watchlist;

    public User(String username, String password, String email, LocalDateTime birthday, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.faved = new HashSet<>();
        //this.rated = new HashSet<>();
        this.watchlist = new HashSet<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return isAccountNonExpired == user.isAccountNonExpired && isAccountNonLocked == user.isAccountNonLocked && isCredentialsNonExpired == user.isCredentialsNonExpired && isEnabled == user.isEnabled && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(birthday, user.birthday) && role == user.role && Objects.equals(followers, user.followers) && Objects.equals(following, user.following) && Objects.equals(faved, user.faved) && Objects.equals(rated, user.rated) && Objects.equals(watchlist, user.watchlist);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email);
    }
}