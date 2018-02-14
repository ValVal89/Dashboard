package com.mongoDB.vacation.sample;

import com.mongoDB.vacation.sample.model.LayoutSettings;
import com.mongoDB.vacation.sample.model.User;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class App {


    private final MongoTemplate mongoTemplate;
    private DBCollection dbCollection;

    @Autowired
    @Lazy
    public App(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public @Bean MongoTemplate mongoTemplate() {
        return new MongoTemplate(new MongoClient("localhost"), "testdb");
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "testdb");
    }

    @Bean
    public MongoOperations mongoOperations() throws UnknownHostException{
        return new MongoTemplate(mongoDbFactory());
    }

    public @Bean CommandLineRunner runner(MongoTemplate mongoTemplate) {
        return args -> {

             dbCollection = mongoTemplate.getCollection("users");
        };
    }

    @GetMapping(value = "/users")
    public List<DBObject> getAllUsers(){

        List<DBObject> allUsers = new ArrayList<>();

        DBCursor cursor = dbCollection.find();
        try {
            while (cursor.hasNext()) {
                allUsers.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return allUsers;
    }

    @DeleteMapping(value = "/users/{id}")
    public void  deleteUser(@PathVariable("id") String id){
        dbCollection.findAndRemove(new BasicDBObject().append("_id", id));
    }

    @GetMapping(value = "/users/{id}")
    public DBObject getUser(@PathVariable("id") String id){
        DBObject user = dbCollection.findOne(new BasicDBObject().append("_id", id));
        return user;
    }

    @PostMapping(value = "/users") //????????
    public void addUser(@RequestBody User user) throws UnknownHostException {
       mongoOperations().save(user, "users");
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}