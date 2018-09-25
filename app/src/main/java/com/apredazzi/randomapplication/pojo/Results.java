package com.apredazzi.randomapplication.pojo;

import java.util.List;

public class Results {
    private List<Users> results;

    public Results(final List<Users> results) {
        this.results = results;
    }

    public List<Users> getResults() {
        return results;
    }
}
