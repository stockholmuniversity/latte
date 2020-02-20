package se.su.it.latte.teexam.models;

import java.util.List;

@lombok.Data
public class DataImpl {
    private long count;
    private List<Row> rows;
}
