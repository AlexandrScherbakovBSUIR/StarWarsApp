package edu.easysoft.entity.page;

import edu.easysoft.entity.Vehicle;

import java.util.List;

public class PageVehicles {
    private int count;
    private String next;
    private String previous;
    private List<Vehicle> results;
    @Override
    public String toString() {
        return "Page{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Vehicle> getResults() {
        return results;
    }

    public void setResults(List<Vehicle> results) {
        this.results = results;
    }


}

