package edu.easysoft.controller;

import com.alibaba.fastjson.JSON;
import edu.easysoft.comparator.FilmReleaseDateComparator;
import edu.easysoft.entity.Film;
import edu.easysoft.entity.Planet;
import edu.easysoft.entity.page.*;
import edu.easysoft.entity.Person;
import java.util.ArrayList;
import java.util.List;

public class PageProcessor {

    public String ProcessSWAPI(String namePattern,String url,
                               SWAPIClient client){
        StringBuilder sb = new StringBuilder();

        /*call & process root page */
        Root root = JSON.parseObject(client.getRequest(url),Root.class);

        /*call & process people, process all films for each valid person */
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
        /*cal & process planets */
        List<Planet> planetList =
                getAllPlanet(JSON.parseObject(client.getRequest(root.getPlanets()),
                        PagePlanets.class),client);

        /*cal & process films */
        List<Film> filmList =
                getAllFilms(JSON.parseObject(client.getRequest(root.getFilms()),
                        PageFilms.class),client);
        /*cal & process species */
        List<Film> speciesList =
                getAllSpecies(JSON.parseObject(client.getRequest(root.getSpecies()),
                        PageSpecies.class),client);
        /*cal & process vehicles */
        List<Film> vehiclesList =
                getAllVehicles(JSON.parseObject(client.getRequest(root.getVehicles()),
                        PageVehicles.class),client);
        /*cal & process starships */
        List<Film> starshipsList =
                getAllStarships(JSON.parseObject(client.getRequest(root.getStarships()),
                        PageStarships.class),client);

        return sb.toString();
    }

    private List<Planet> getAllPlanet(PagePlanets pagePlanets,
                                      SWAPIClient client) {
        List planetList = new ArrayList<Planet>();
        do{
            planetList.addAll(pagePlanets.getResults());

            if(pagePlanets.getNext()!=null) {
                pagePlanets = JSON.parseObject(client.getRequest(pagePlanets.getNext()),
                        PagePlanets.class);

            }
        }
        while( pagePlanets.getCount() > planetList.size());
        return planetList;
    }

    private List<Film> getAllFilms(PageFilms pageFilms,
                                      SWAPIClient client) {
        List filmList = new ArrayList<Planet>();
        do{
            filmList.addAll(pageFilms.getResults());

            if(pageFilms.getNext()!=null) {
                pageFilms = JSON.parseObject(client.getRequest(pageFilms.getNext()),
                        PageFilms.class);

            }
        }
        while( pageFilms.getCount() > filmList.size());
        return filmList;
    }
    private List<Film> getAllSpecies(PageSpecies pageSpecies,
                                   SWAPIClient client) {
        List speciesList = new ArrayList<Planet>();
        do{
            speciesList.addAll(pageSpecies.getResults());

            if(pageSpecies.getNext()!=null) {
                pageSpecies = JSON.parseObject(client.getRequest(pageSpecies.getNext()),
                        PageSpecies.class);

            }
        }
        while( pageSpecies.getCount() > speciesList.size());
        return speciesList;
    }

    private List<Film> getAllVehicles(PageVehicles pageVehicles,
                                     SWAPIClient client) {
        List vehiclesList = new ArrayList<Planet>();
        do{
            vehiclesList.addAll(pageVehicles.getResults());

            if(pageVehicles.getNext()!=null) {
                pageVehicles = JSON.parseObject(client.getRequest(pageVehicles.getNext()),
                        PageVehicles.class);

            }
        }
        while( pageVehicles.getCount() > vehiclesList.size());
        return vehiclesList;
    }
    private List<Film> getAllStarships(PageStarships pageStarShips,
                                       SWAPIClient client) {
        List starShipsList = new ArrayList<Planet>();
        do{
            starShipsList.addAll(pageStarShips.getResults());

            if(pageStarShips.getNext()!=null) {
                pageStarShips = JSON.parseObject(client.getRequest(pageStarShips.getNext()),
                        PageStarships.class);

            }
        }
        while( pageStarShips.getCount() > starShipsList.size());
        return starShipsList;
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
