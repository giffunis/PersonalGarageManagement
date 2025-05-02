package com.giffuniscode.infraestructure.data.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.giffuniscode.db.sqlite.BaseRepository;
import com.giffuniscode.pgm.core.repositories.IUserRepository;
import com.giffuniscode.pgm.core.models.User;

public class UserDbRepository extends BaseRepository<User> implements IUserRepository {

    private static final String TABLE = "User";
    private static final String USERNAME_FIELD = "Username";
    private static final String PASSWORD_FIELD = "Password";

    public UserDbRepository(@Nullable Context context) {
        super(context, "db-test", null, 1);
    }

    @Override
    protected User extract(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));

        return user;
    }

    @Override
    protected String getTableName() {
        return TABLE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " ("+
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USERNAME_FIELD + " TEXT, "+
                PASSWORD_FIELD + " TEXT)");

        db.execSQL("INSERT INTO " + TABLE + " VALUES (null, "+
                "'usuario', '123456')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public User findByUsername(String username) {
        return this.findBy(USERNAME_FIELD, String.format("'%s'", username));
    }
}

