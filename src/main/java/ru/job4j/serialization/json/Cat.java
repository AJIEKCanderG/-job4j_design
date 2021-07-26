package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cat")
public class Cat {
    @XmlAttribute
    private String food;

    public Cat() {
    }

    public Cat(String food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "food='" + food + '\''
                + '}';
    }
}
