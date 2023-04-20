package com.nixagh.generic.services;

import com.nixagh.generic.models.BaseModel;
import com.nixagh.generic.repositories.GenericRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public abstract class GenericService<T> {
    private final Logger LOG = LoggerFactory.getLogger(BaseModel.class);
    private final GenericRepository<T> repository;
    @Autowired
    protected GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }
    public List<T> getAll() { return repository.findAll(); }
    public List<T> getAllById(String id) { return repository.findAllById(id); }
    public T save(T model) {
        return repository.save(model);
    }
    public long delete(String id) {
        return repository.delete(id).getDeletedCount();
    }
    public String update(String id, T model) throws NoSuchFieldException {
        return Objects.requireNonNull(repository.update(id, model).getUpsertedId()).asObjectId().getValue().toString();
    }
    public T getById(String id) {
        return repository.findById(id);
    }
}
