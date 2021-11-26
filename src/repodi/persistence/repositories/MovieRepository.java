package repodi.persistence.repositories;

import org.jetbrains.annotations.NotNull;
import repodi.persistence.beans.Movie;
import repodi.persistence.exceptions.MovieNotFoundException;
import repodi.persistence.repositories.daos.MovieDAO;
import repodi.persistence.repositories.daos.MySqlMovieDAO;

import java.util.List;

public class MovieRepository {

    MovieDAO movieDao;

    public MovieRepository(MovieDAO dao){
        this.movieDao = dao;
    }

    public void save(@NotNull Movie movie) throws Exception{
        var movieList = movieDao.selectAll();
        var exists = movieList.contains(movie);
        if(exists){
            movieDao.update(movie);
        }else{
            movieDao.add(movie);
        }
    };


    public List<Movie> findAll() throws MovieNotFoundException {
        return movieDao.selectAll();
    };


    public Movie find(@NotNull String uuid) throws MovieNotFoundException{
        return movieDao.select(uuid);
    };


    public void clearAll() throws Exception{
        movieDao.clearAll();
    };
}
