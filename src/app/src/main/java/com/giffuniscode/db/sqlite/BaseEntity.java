package com.giffuniscode.db.sqlite;

import android.content.ContentValues;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntity {

    private long id = 0;
    public static final String ID = "Id";
    private static final String SPACE_CHAR = " ";

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        List<Field> fs = getPrivateFields();
        for (Field f : fs ) {
            try {
                f.setAccessible(true);
                Object fValue = f.get(this);
                values.put(f.getName(), fValue != null ? fValue.toString() : "");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return values;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    protected List<Field> getPrivateFields(){
        List<Field> privateFields = new ArrayList<>();
        Field[] allFields = this.getClass().getDeclaredFields();
        for (Field field : allFields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                privateFields.add(field);
            }
        }
        return privateFields;
    }
}
