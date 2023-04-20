package com.nixagh.generic.services;

import com.nixagh.generic.models.UserModel;
import com.nixagh.generic.repositories.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<UserModel>{
    protected UserService(GenericRepository<UserModel> repository) {
        super(repository);
    }
}
