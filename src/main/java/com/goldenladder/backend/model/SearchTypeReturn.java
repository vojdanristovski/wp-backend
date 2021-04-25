package com.goldenladder.backend.model;

import lombok.Data;

@Data
public class SearchTypeReturn {
    private String id;
    private String name;
    private String type;

    public SearchTypeReturn(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
