package com.mongoDB.vacation.sample;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4211")
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

    @PostMapping(value = "/users")
    public DBObject addUser(@RequestBody String data) {
       //mongoOperations().save(user, "users");
        dbCollection.insert(BasicDBObject.parse(data));
        return BasicDBObject.parse(data);
    }


    @PutMapping(value = "/users/{id}")
    public void updateUser(@RequestBody String data, @PathVariable("id") String id) {
        DBObject query = dbCollection.findOne(new BasicDBObject().append("_id", id));
        DBObject update = BasicDBObject.parse(data);
        dbCollection.findAndModify(query, update);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
