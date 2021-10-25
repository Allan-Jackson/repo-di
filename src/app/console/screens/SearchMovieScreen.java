package app.console.screens;

import app.console.constants.Constants;
import repodi.FileDataSource;
import repodi.beans.Movie;

import java.io.IOException;
import java.util.Scanner;

//todo: tratamento da exceção de searchInFile
public class SearchMovieScreen extends BaseScreen{
    private final Scanner mScanner = new Scanner(System.in);
    private final FileDataSource fileDataSrc = new FileDataSource();

    @Override
    public void onCreate() {
        setMainScreen(MenuScreen.class);

        System.out.print("Digite o ID do filme: ");
        var uuid = mScanner.nextLine();
        try{
            var movie = fileDataSrc.searchMovie(uuid);
            if (movie != null) {
                displayMovieInfo(movie);
            }else{
                System.out.println("Filme não encontrado");
            }
        }catch (IOException e){
            System.out.println("===============================");
            System.out.println("Ocorreu um problema na leitura do arquivo.");
        }
        returnToMain(Constants.TEXT_TO_MAIN);
    }

    private void displayMovieInfo(Movie movie){
        System.out.println("\n=====Informações do Filme=====");
        System.out.println("UUID: " + movie.getUuid());
        System.out.println("Nome: " + movie.getMovieName());
        System.out.println("Gênero: " + movie.getMovieGenre());
        System.out.println("Lançamento: " + movie.getMovieDate());
        System.out.println("Diretor: " + movie.getMovieDirector());
    }
}
