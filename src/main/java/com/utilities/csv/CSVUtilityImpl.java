package com.utilities.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.utilities.model.Person;
import com.utilities.model.PersonConstant;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtilityImpl implements CSVUtility {

    @Override
    public List<Person> readIntoList(final InputStream inputStream, final Character separator) {
        //List<Person> personList = readByLines(inputStream,separator);
        List<Person> personList = readByCsvToBean(inputStream, separator);
        return personList;
    }

    private List<Person> readByLines(final InputStream inputStream, final Character separator) {

        List<Person> personList = null;
        List<String[]> data = null;

        try (final CSVReader reader = new CSVReader(new InputStreamReader(inputStream), separator)) {
            data = reader.readAll();
            personList = new ArrayList<>();
            int count = 0;
            for (String[] recordCells : data) {
                count++;
                Person person = new Person();
                person.setName(recordCells[0]);
                person.setAddress(recordCells[1]);
                person.setEmailId(recordCells[2]);
                if (count == 1
                        && person.getName().equalsIgnoreCase(PersonConstant.NAME)
                        && person.getAddress().equalsIgnoreCase(PersonConstant.ADDRESS)
                        && person.getEmailId().equalsIgnoreCase(PersonConstant.EMAIL_ID)) {
                    continue;
                } else {
                    personList.add(person);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("RECORDS FOUND :: " + (!personList.isEmpty() ? personList.size() : 0));
        return personList;
    }

    private List<Person> readByCsvToBean(final InputStream inputStream, final Character separator) {

        List<Person> personList = null;
        try (final CSVReader reader = new CSVReader(new InputStreamReader(inputStream), separator,'"')) {

            /*//use when file dont have column header
            ColumnPositionMappingStrategy<Person> beanStrategy = new ColumnPositionMappingStrategy<Person>();
            beanStrategy.setType(Person.class);
            beanStrategy.setColumnMapping(new String[]{PersonConstant.NAME, PersonConstant.ADDRESS, PersonConstant.EMAIL_ID});*/

            HeaderColumnNameMappingStrategy<Person> beanStrategy = new HeaderColumnNameMappingStrategy<Person>();
            beanStrategy.setType(Person.class);
            CsvToBean<Person> csvToBean = new CsvToBean<Person>();
            personList = csvToBean.parse(beanStrategy, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("RECORDS FOUND :: " + (!personList.isEmpty() ? personList.size() : 0));
        return personList;
    }

    @Override
    public Boolean writeFromList(final List<Person> list, final Character separator, final String path) {
        Boolean success = writeByLines(list, separator, path);
        return success;
    }

    private Boolean writeByLines(List<Person> data, Character separator, String path) {
        Boolean success = false;
        try (final FileWriter fileWriter = (new FileWriter(path.toString()));
             CSVWriter writer = new CSVWriter(fileWriter, separator)) {
            writer.writeAll(toStringArray(data));

            /**
             * try with resources will close fileWriter, writer
             * close when not using try with resources
             * writer.close();fileWriter.close();
             */
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    private static List<String[]> toStringArray(List<Person> personList) {
        List<String[]> records = new ArrayList<String[]>();
        records.add(new String[]{PersonConstant.NAME, PersonConstant.ADDRESS, PersonConstant.EMAIL_ID});
        for (Person person : personList) {
            records.add(new String[]{person.getName(), person.getAddress(), person.getEmailId()});
        }
        return records;
    }
}
