package com.academy.demo_ws.controller;

import com.academy.demo_ws.model.New;
import com.academy.demo_ws.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Controlador que maneja las operaciones relacionadas con novedades o noticias.
// A su  vez, notifica a los clientes conectados a través de WebSockets.
@RestController
@RequestMapping("/api/news")
public class NewController {

    private final NewService newService; //Para CRUD de novedad.
    private final NewWebSocketController wsController; //Para gestionar mensajes de WebSockets.

    public NewController(NewService newService, NewWebSocketController wsController) {
        this.newService = newService;
        this.wsController = wsController;
    }

    @GetMapping
    public List<New> getAll() {
        return newService.getAll();
    }

    @PostMapping
    public New create(@RequestBody New news) { //crea novedad y se guarda en la base de datos
        New saved = newService.save(news);
        wsController.sendNewCreated(saved); // Notificar a todos los clientes conectados a través de WebSockets.
        return saved;
    }
}


