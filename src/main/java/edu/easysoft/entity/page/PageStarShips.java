package edu.easysoft.entity.page;

import edu.easysoft.entity.StarShip;

import java.util.List;

public class PageStarShips {
    private int count;
    private String next;
    private String previous;
    private List<StarShip> results;
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

    public List<StarShip> getResults() {
        return results;
    }

    public void setResults(List<StarShip> results) {
        this.results = results;
    }


}

