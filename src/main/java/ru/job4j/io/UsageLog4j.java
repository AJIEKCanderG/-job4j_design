package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Ivan";
        int age = 35;
        double salary = 10000.0;
        char gender = 'm';
        byte bloodType = 1;
        short sh = 2;
        long height = 188;
        boolean noSmoke = true;
        LOG.debug("User info name : {}, age : {}, salary : {}, gender : {}, bloodType : {}, "
                + "sh : {}, height : {}, smoke : {}", name, age, salary, gender, bloodType, sh, height, noSmoke);
    }
}