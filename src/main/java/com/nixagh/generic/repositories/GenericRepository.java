package com.nixagh.generic.repositories;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.nixagh.generic.commons.database.DBAccessor;
import com.nixagh.generic.models.BaseModel;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
@Repository
public abstract class GenericRepository<T> {
    private final MongoTemplate accessor = DBAccessor.getInstance();
    private final Class<T> clazz;
    private final String collectionName;
    public GenericRepository() {
        this.collectionName = getCollectionName();
        this.clazz = getClazz();
    }
    protected abstract Class<T> getClazz();
    protected abstract String getCollectionName();
    public List<T> findAll() { return accessor.findAll(clazz, collectionName); }
    List<T> findByConditions(Query query) {
        return accessor.find(query, clazz, collectionName);
    }
    public List<T> findAllById(String id) {
        Query query = new Query(new Criteria("_id").is(new ObjectId(id)));
        return findByConditions(query);
    }
    public T findOne(Query query) {
        return accessor.findOne(query, clazz, collectionName);
    }
    public T findById(String id) {
        Query query = new Query(new Criteria("_id").is(new ObjectId(id)));
        return findOne(query);
    }
    public T save(T entity) {
        return accessor.save(entity, collectionName);
    }
    public UpdateResult update(String id, @NotNull T entity) throws NoSuchFieldException {
        try {
            Query query = new Query(new Criteria("_id").is(new ObjectId(id)));
            Field[] fields = entity.getClass().getFields();
            Update update = new Update();
            for(Field field : fields) {
                String fieldName = field.getName();
                update.set(fieldName, entity.getClass().getField(fieldName));
            }
            return accessor.updateFirst(query, update, clazz, collectionName);
        }
        catch (NoSuchFieldException e){
            throw new NoSuchFieldException(entity.getClass().getName());
        }
    }
    public DeleteResult delete(String id) {
        Query query = new Query(new Criteria("_id").is(new ObjectId(id)));
        return accessor.remove(query, collectionName);
    }
}
