package com.goldenladder.backend.web.rest;

import com.goldenladder.backend.model.Actor;
import com.goldenladder.backend.model.Movie;
import com.goldenladder.backend.model.SearchTypeReturn;
import com.goldenladder.backend.model.User;
import com.goldenladder.backend.service.ActorService;
import com.goldenladder.backend.service.MovieService;
import com.goldenladder.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @GetMapping("/{searchText}")
    ResponseEntity<List<SearchTypeReturn>> search(@PathVariable String searchText) {
        try {
            String text = "%"+searchText+"%";
            List<User> userList = this.userService.search(text);
            List<Actor> actorList = this.actorService.search(text);
            List<Movie> movieList = this.movieService.search(text);
            List<SearchTypeReturn> searchTypeReturnList1= userList.stream()
                    .map(user -> new SearchTypeReturn(user.getUsername(),user.getUsername(),"user"))
                    .collect(Collectors.toList());
            List<SearchTypeReturn> searchTypeReturnList2= actorList.stream()
                    .map(actor -> new SearchTypeReturn(actor.getActorId(),actor.getName(),"actor"))
                    .collect(Collectors.toList());
            List<SearchTypeReturn> searchTypeReturnList3= movieList.stream()
                    .map(movie -> new SearchTypeReturn(movie.getMovieId(),movie.getTitle(),"movie"))
                    .collect(Collectors.toList());
            searchTypeReturnList1.addAll(searchTypeReturnList2);
            searchTypeReturnList1.addAll(searchTypeReturnList3);
            return ResponseEntity.ok().body(searchTypeReturnList1);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
