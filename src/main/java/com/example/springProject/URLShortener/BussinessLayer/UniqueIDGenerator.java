package com.example.springProject.URLShortener.BussinessLayer;

import java.util.UUID;

public class UniqueIDGenerator {
    private UUID uniqueID;

    public void setUniqueID(){
        UUID uuid = UUID.randomUUID();
        this.uniqueID = uuid;
    }

    public UUID getUniqueID(){
        return this.uniqueID;
    }
}
