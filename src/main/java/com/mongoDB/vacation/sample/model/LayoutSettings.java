package com.mongoDB.vacation.sample.model;

import com.mongodb.annotations.Beta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LayoutSettings {
    private long id;
    private String name;

    private Map<String, Object> settings;

    }
