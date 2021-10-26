package app.console.screens;

import app.console.constants.Constants;
import repodi.FileDataSource;
import repodi.Property;
import repodi.PropertyUtils;
import repodi.beans.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//todo: tratamento da exceção de writeFile
public class AddMovieScreen extends BaseScreen {
    private final Scanner mScanner = new Scanner(System.in);
    private final FileDataSource fileDataSrc = new FileDataSource(PropertyUtils.getString(Property.MOVIE_DATABASE_FILENAME.name()));
                
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
            fileDataSrc.saveMovie(new Movie(
                    data.get(0),
                    data.get(1),
                    data.get(2),
                    data.get(3)
            ));
        }catch (IOException e){
            System.out.println("===============================");
            System.out.println("Ocorreu um problema ao salvar o filme.");
        }
        returnToMain(Constants.TEXT_TO_MAIN);
    }
}
