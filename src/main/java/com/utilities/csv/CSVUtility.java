package com.utilities.csv;

import com.utilities.model.Person;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface CSVUtility {

    List<Person> readIntoList(final InputStream inputStream, final Character separator);

    OutputStream writeFromList(final List<Person> list, final Character separator);
}
