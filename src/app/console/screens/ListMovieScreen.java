package app.console.screens;

import app.console.constants.Constants;
import repodi.persistence.daos.MovieDAO;
import repodi.persistence.exceptions.MovieNotFoundException;
import repodi.persistence.beans.Movie;

import java.util.List;


public class ListMovieScreen extends BaseScreen {

    private final MovieDAO dataSource;

    public ListMovieScreen(MovieDAO dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void onCreate() {
        setMainScreen(MenuScreen.class);

        try {
            List<Movie> movieList = dataSource.searchAll();
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
