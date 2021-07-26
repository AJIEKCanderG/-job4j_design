package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

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
        Animal animal = new Animal(false, 3, new Cat("meat"), "Barsik", "Murka", "Genry");

        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Animal.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String str;
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(animal, writer);
            str = writer.getBuffer().toString();
            System.out.println(str);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(str)) {
            // десериализуем
            Animal result = (Animal) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
