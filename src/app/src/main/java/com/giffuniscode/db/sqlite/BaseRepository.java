package com.giffuniscode.db.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.giffuniscode.db.sqlite.interfaces.IRepository;

import java.util.ArrayList;

public abstract class BaseRepository<T extends BaseEntity> extends SQLiteOpenHelper implements IRepository<T> {

    public BaseRepository(@Nullable Context context, @Nullable String nameDbFile, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameDbFile, factory, version);
    }

    @Override
    public T findBy(String key, Object value) {
        SQLiteDatabase db = getReadableDatabase();
        String where = String.format("%s = %s", key, value.toString());
        Cursor cursor = db.query(getTableName(), null, where, null, null, null, null);
        return cursor.moveToFirst() ? extract(cursor) : null;
    }

    public ArrayList<T> findAll() {
        ArrayList<T> entities = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(getTableName(),null, null,null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                entities.add(extract(cursor));
            }while  (cursor.moveToNext());
        }
        return entities;
    }

    public long add(T entity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = entity.getContentValues();
        return db.insert(getTableName(),null, values);
    }

    public long update(T entity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = entity.getContentValues();
        String where = String.format("%s = %s", T.ID, entity.getId());
        return db.update(getTableName(),values, where, null);
    }

    @Override
    public long delete(T entity) {
        SQLiteDatabase db = getWritableDatabase();
        String where = String.format("%s = %s", T.ID, entity.getId());
        return db.delete(getTableName(), where, null);
    }

    protected abstract T extract(Cursor cursor);

    protected abstract String getTableName();
}