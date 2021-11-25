package app.console.screens;

import app.console.constants.Constants;
import repodi.FileDataSource;
import repodi.MovieDAO;
import repodi.MySqlDataSource;
import repodi.beans.Movie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AddMovieScreen extends BaseScreen {
    private final Scanner mScanner = new Scanner(System.in);
    private final MovieDAO dataSource;


    public AddMovieScreen(MovieDAO dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void onCreate() {
        setMainScreen(MenuScreen.class);
        var dataQuestions = List.of(
                "Nome do filme",
                "Gênero do filme",
                "Data de lançamento do filme",
                "Nome do diretor do filme"
        );
        List<String> data = new ArrayList<>(); //recebe os dados inseridos

        //ciclo para receber os dados
        for (String question : dataQuestions) {
            System.out.println(question + ": ");
            data.add(mScanner.nextLine());
        }

        try{
            dataSource.saveMovie(new Movie(
                    data.get(0), //nome
                    data.get(1), //gênero
                    (new SimpleDateFormat("dd/MM/yyyy")).parse(data.get(2)), //data
                    data.get(3) //diretor
            ));
            System.out.println("----------------");
            System.out.println("Filme salvo com sucesso!");
        }catch (Exception e){
            System.out.println("===============================");
            System.out.println("Ocorreu um problema ao salvar o filme.");
        }
        returnToMain(Constants.TEXT_TO_MAIN);
    }
}
