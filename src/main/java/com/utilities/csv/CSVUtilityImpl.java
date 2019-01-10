package com.utilities.csv;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.utilities.model.Person;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CSVUtilityImpl implements CSVUtility {

    @Override
    public List<Person> readIntoList(final InputStream inputStream, final Character separator) {
        //List<Person> personList = readByLines(inputStream);
        List<Person> personList = readByCsvToBean(inputStream, separator);
        return personList;
    }

    private List<Person> readByLines(final InputStream inputStream, final Character separator) {

        List<Person> personList = null;
        List<String[]> data = null;

        try (final CSVReader reader = new CSVReader(new InputStreamReader(inputStream), separator)) {
            data = reader.readAll();
            personList = new ArrayList<>();
            for (String[] recordCells : data) {
                Person person = new Person();
                person.setName(recordCells[0]);
                person.setAddress(recordCells[1]);
                person.setEmailId(recordCells[2]);
                personList.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("RECORDS FOUND :: " + (!personList.isEmpty() ? personList.size() : 0));
        return personList;
    }

    private List<Person> readByCsvToBean(final InputStream inputStream, final Character separator) {

        List<Person> personList = null;

        try (final CSVReader reader = new CSVReader(new InputStreamReader(inputStream), separator)) {
            ColumnPositionMappingStrategy<Person> beanStrategy = new ColumnPositionMappingStrategy<Person>();
            beanStrategy.setType(Person.class);
            beanStrategy.setColumnMapping(new String[]{"name", "address", "emailId"});

            CsvToBean<Person> csvToBean = new CsvToBean<>();
            personList = csvToBean.parse(beanStrategy, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("RECORDS FOUND :: " + (!personList.isEmpty() ? personList.size() : 0));
        return personList;
    }

    @Override
    public OutputStream writeFromList(final List<Person> list, final Character separator) {

        return null;
    }

   /* public static void main(String[] args) throws IOException {
        StringWriter writer = new StringWriter();

        //using custom delimiter and quote character
        CSVWriter csvWriter = new CSVWriter(writer, '#', '\'');

        List<Employee> emps = OpenCSVParseToBeanExample.parseCSVWithHeader();

        List<String[]> data = toStringArray(emps);

        csvWriter.writeAll(data);

        csvWriter.close();

        System.out.println(writer);

    }

    private static List<String[]> toStringArray(List<Employee> emps) {
        List<String[]> records = new ArrayList<String[]>();

        // adding header record
        records.add(new String[] { "ID", "Name", "Age", "Country" });

        Iterator<Employee> it = emps.iterator();
        while (it.hasNext()) {
            Employee emp = it.next();
            records.add(new String[] { emp.getId(), emp.getName(), emp.getAge(), emp.getCountry() });
        }
        return records;
    }*/
}
