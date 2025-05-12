package com.example.foodordering.model;

import java.util.List;

public class MenuUpdateRequest {
    private List<MenuItem> updates;
    
    public List<MenuItem> getUpdates() {
        return updates;
    }

    public void setUpdates(List<MenuItem> updates) {
        this.updates = updates;
    }
}
