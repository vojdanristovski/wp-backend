package com.goldenladder.backend.web.rest;

import com.goldenladder.backend.model.Actor;
import com.goldenladder.backend.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a")
@CrossOrigin(origins = "http://localhost:3000")
public class ActorController {
    private final ActorService actorService;


    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> findById(@PathVariable String id)
    {
        return this.actorService.findById(id)
                .map(actor -> ResponseEntity.ok().body(actor))
                .orElseGet(()->ResponseEntity.notFound().build());
    }


}
