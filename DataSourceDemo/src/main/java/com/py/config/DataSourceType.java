package com.py.config;

/**
 * Created by py on 2017/11/24.
 */
public enum DataSourceType {
    master("master", "主 写库"),
    slave("slave", "从 读库");

    private String type;
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

