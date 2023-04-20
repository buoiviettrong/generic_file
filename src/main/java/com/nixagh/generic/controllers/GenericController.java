package com.nixagh.generic.controllers;

import com.nixagh.generic.models.BaseModel;
import com.nixagh.generic.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public abstract class GenericController<T> {
    protected GenericService<T> service;
    public abstract GenericService<T> getService();

    @GetMapping
    public List<T> getAll() {
        return getService().getAll();
    }
    @GetMapping("{id}")
    public List<T> getAllById(@PathVariable String id) { return service.getAllById(id); }
    public T getById(@PathVariable(name = "id") String id) { return service.getById(id); }
}
