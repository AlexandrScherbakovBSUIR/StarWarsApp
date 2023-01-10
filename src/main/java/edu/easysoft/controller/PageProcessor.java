package edu.easysoft.controller;

import com.alibaba.fastjson.JSON;
import edu.easysoft.comparator.FilmReleaseDateComparator;
import edu.easysoft.entity.Film;
import edu.easysoft.entity.page.*;
import edu.easysoft.entity.Person;
import java.util.ArrayList;
import java.util.List;

public class PageProcessor {

    public String ProcessSWAPI(String namePattern,String url,
                               SWAPIClient client){
        Root root = JSON.parseObject(client.getRequest(url),Root.class);
        StringBuilder sb = new StringBuilder();
        List<Person> peopleList =
                getAllPeople(JSON.parseObject(client.getRequest(root.getPeople()),
                        PagePeople.class),client);
        for (Person person: peopleList) {
            if(person.getName().startsWith(namePattern)){
                List<Film> filmsObjectList = getAllPersonFilms(person,client);

                sb.append("name: " + person.getName() +"\n"+ " films: ");
                for (Film film: filmsObjectList) {

                    sb.append(film.getEpisode_id() + ": " + film.getTitle() + " ");
                }
                sb.append("\n");
            }

        }
        return sb.toString();
    }
    public   List<Person> getAllPeople(PagePeople pagePeople,
                                       SWAPIClient client ) {

        List peopleList = new ArrayList<Person>();
        do{
            peopleList.addAll(pagePeople.getResults());

            if(pagePeople.getNext()!=null) {
                pagePeople = JSON.parseObject(client.getRequest(pagePeople.getNext()),
                            PagePeople.class);

            }
        }
        while( pagePeople.getCount() > peopleList.size());

        return peopleList;
    }
    public List<Film> getAllPersonFilms(Person person, SWAPIClient client) {
        String[] filmsUrlList = person.getFilms().split(",");
        List filmsObjectList = new ArrayList<>();

        for (String filmUrl :filmsUrlList) {
            Film film =
                    JSON.parseObject(client.getRequest(ReplaceUnnecessaryCharacters(filmUrl)),
                            Film.class);
            filmsObjectList.add(film);
        }
        filmsObjectList.sort(new FilmReleaseDateComparator());

        return filmsObjectList;
    }

    public  String ReplaceUnnecessaryCharacters(String str){

        str = str.replace('[', ' ');
        str = str.replace(']', ' ');
        str = str.replace('"', ' ');

        return str.trim();
    }
}
