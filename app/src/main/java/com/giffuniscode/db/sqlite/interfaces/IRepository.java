package com.giffuniscode.db.sqlite.interfaces;

import java.util.ArrayList;

public interface IRepository <TEntity>{
    TEntity findBy(String key, Object value);
    ArrayList<TEntity> findAll();
    long add(TEntity entity);
    long update(TEntity entity);
    long delete(TEntity entity);
}
