package com.goldenladder.backend.web.rest;

import com.goldenladder.backend.model.Actor;
import com.goldenladder.backend.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
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
