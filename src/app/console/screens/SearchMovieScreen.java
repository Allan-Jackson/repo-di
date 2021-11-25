package app.console.screens;

import app.console.constants.Constants;
import org.jetbrains.annotations.NotNull;
import repodi.FileDataSource;
import repodi.MovieDAO;
import repodi.MovieNotFoundException;
import repodi.MySqlDataSource;
import repodi.beans.Movie;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;


public class SearchMovieScreen extends BaseScreen {

    private final MovieDAO dataSource;

    public SearchMovieScreen(MovieDAO dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void onCreate() {
        setMainScreen(MenuScreen.class);

        System.out.print("Digite o ID do filme: ");
        var uuid = new Scanner(System.in).nextLine();
        try{
            var movie = dataSource.searchMovie(uuid);
            displayMovieInfo(movie);
        }catch (MovieNotFoundException e){
            System.out.println("===============================");
            System.out.println("Filme não encontrado");
        }
        returnToMain(Constants.TEXT_TO_MAIN);
    }

    private void displayMovieInfo(@NotNull Movie movie){
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(movie.getMovieDate());
        System.out.println("\n=====Informações do Filme=====");
        System.out.println("UUID: " + movie.getUuid());
        System.out.println("Nome: " + movie.getMovieName());
        System.out.println("Gênero: " + movie.getMovieGenre());
        System.out.println("Lançamento: " + formattedDate);
        System.out.println("Diretor: " + movie.getMovieDirector());
    }
}
