package com.example.mongoproject.config;

import java.io.Serializable;

public interface Entity<T> extends Serializable {
    T getId();

    void setId(T id);
}
