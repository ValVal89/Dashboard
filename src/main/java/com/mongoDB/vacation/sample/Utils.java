package com.mongoDB.vacation.sample;

import com.mongoDB.vacation.sample.model.LayoutSettings;
import com.mongoDB.vacation.sample.model.User;
import org.json.JSONObject;

import java.util.*;

public class Utils
{
    private static Map<String, Object> settings = new HashMap<>();
    private static Map<String, Object> settings2 = new HashMap<>();
    private static LayoutSettings layoutSettings = new LayoutSettings(1, "name", settings);
    private static LayoutSettings layoutSettings2 = new LayoutSettings(2, "name", settings2);
    private static Set<LayoutSettings> list= new HashSet<>();

    public static void main(String[] args) {

        fill();

        User createdUser = new User("id", "firstName", "lastName", "email", "pass", list);
            System.out.println(createdUser);

        JSONObject json = new JSONObject(settings);
        System.out.println(json);

        JSONObject json2 = new JSONObject(settings2);
        System.out.println(json2);
        }

        public static void fill()
        {
            settings.put("x", null);
            settings.put("y", null);
            settings.put("rows", null);

            settings2.put("x", null);
            settings2.put("y", null);
            settings2.put("rows", null);
            settings2.put("maxItemRows", null);

            list.add(layoutSettings);
            list.add(layoutSettings2);
        }

}
