package com.giffuniscode.pgm.core.repositories;

import com.giffuniscode.db.sqlite.interfaces.IRepository;
import com.giffuniscode.pgm.core.models.User;

public interface IUserRepository extends IRepository<User> {
    public User findByUsername(String username);
}