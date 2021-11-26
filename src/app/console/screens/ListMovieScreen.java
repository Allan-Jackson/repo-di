package app.console.screens;

import app.console.constants.Constants;
import repodi.persistence.repositories.MovieRepository;
import repodi.persistence.exceptions.MovieNotFoundException;
import repodi.persistence.beans.Movie;

import java.util.List;


public class ListMovieScreen extends BaseScreen {

    private final MovieRepository movieRepository;

    public ListMovieScreen(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public void onCreate() {
        setMainScreen(MenuScreen.class);

        try {
            List<Movie> movieList = movieRepository.findAll();
            if(!movieList.isEmpty()){
                System.out.println("ID  |  Nome");
                for(Movie movie:movieList){
                    System.out.println("[" + movie.getUuid() + "]" + " " + movie.getMovieName());
                }
            }else{
                System.out.println("--- Não existem filmes para listar ---");
            }

        } catch (MovieNotFoundException e) {
            System.out.println("===============================");
            System.out.println("Ocorreu um problema ao buscar os filmes.");
        }
        returnToMain(Constants.TEXT_TO_MAIN);
    }
}
