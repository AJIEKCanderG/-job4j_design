package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "animal")
@XmlAccessorType(XmlAccessType.FIELD)

public class Animal {


    @XmlAttribute
    private boolean spotted;

    @XmlAttribute
    private int age;
    private Cat cat;
    @XmlElementWrapper(name = "names")
    @XmlElement(name = "name")
    private String[] names;

    public Animal() {
    }

    public Animal(boolean spotted, int age, Cat cat, String... names) {
        this.spotted = spotted;
        this.age = age;
        this.cat = cat;
        this.names = names;
    }

    public boolean isSpotted() {
        return spotted;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "spotted=" + spotted
                + ", age=" + age
                + ", cat=" + cat
                + ", names=" + Arrays.toString(names)
                + '}';
    }

    public static void main(String[] args) throws Exception {

        JSONObject jsonAnimal = new JSONObject("{\"food\":\"fish\"}");

        List<String> list = new ArrayList<>();
        list.add("Murzik");
        list.add("Stesha");
        JSONArray jsonStatuses = new JSONArray(list);

        Animal animal = new Animal(false, 3, new Cat("meat"), "Barsik", "Murka", "Genry");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("spotted", animal.isSpotted());
        jsonObject.put("age", animal.getAge());
        jsonObject.put("cat", jsonAnimal);
        jsonObject.put("names", jsonStatuses);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(animal));
    }
}
