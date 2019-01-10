package com.utilities;

import com.utilities.csv.CSVUtility;
import com.utilities.csv.CSVUtilityImpl;
import com.utilities.model.Person;

import java.io.InputStream;
import java.util.List;

public class CSVUtilityTestApp {

    public static void main(String[] args) throws Exception {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("PersonData.csv");
        CSVUtility csvUtility = new CSVUtilityImpl();
        List<Person> personList = csvUtility.readIntoList(inputStream, ',');
        System.out.println("FILE WRITE SUCCESS :: " + csvUtility.writeFromList(personList, ',', "./src/main/resources/person.csv"));

    }
}
