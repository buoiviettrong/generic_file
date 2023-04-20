package com.nixagh.generic.repositories;

import com.nixagh.generic.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends GenericRepository<UserModel> {
    @Override
    protected Class<UserModel> getClazz() {
        return UserModel.class;
    }
    @Override
    protected String getCollectionName() {
        return "User";
    }

}
