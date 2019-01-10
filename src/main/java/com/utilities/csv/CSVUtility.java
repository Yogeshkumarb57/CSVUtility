package com.utilities.csv;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface CSVUtility<T> {

    List<T> readIntoList(final InputStream inputStream);

    OutputStream writeFromList(final List<T> list);
}
