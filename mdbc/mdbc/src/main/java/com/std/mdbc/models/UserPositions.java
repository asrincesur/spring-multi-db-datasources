package com.std.mdbc.models;

import org.springframework.http.HttpStatus;

public enum UserPositions {
    MANAGER(0L),
    DEVELOPER(1L),
    PRODUCTOWNER(2L),
    PRODUCTMANAGER(3L);
    private Long id;

    UserPositions(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static UserPositions fromID(Long id)  {
        for (UserPositions type : values()){
            if(type.getId() == id ){
                return type;
            }
        }
        return null;
    }
}
