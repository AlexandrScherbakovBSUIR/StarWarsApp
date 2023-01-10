package edu.easysoft.comparator;

import edu.easysoft.entity.Film;
import java.time.LocalDate;
import java.util.Comparator;
public class FilmReleaseDateComparator implements Comparator<Film> {
    @Override
    public int compare(Film film1, Film film2) {
        if(LocalDate.parse(film1.getRelease_date())
                .isEqual(LocalDate.parse(film2.getRelease_date()))){
            return 0;
        } else if (LocalDate.parse(film1.getRelease_date())
                .isAfter(LocalDate.parse(film2.getRelease_date()))) {
            return 1;
        } else {
            return -1;
        }

    }
}
