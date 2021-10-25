package app.console.screens;

import app.console.constants.Constants;
import repodi.FileDataSource;
import repodi.beans.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//todo: utilizar FileDataSource para manipular o arquivo com os dados
public class ListMovieScreen extends BaseScreen {
    private final Scanner mScanner = new Scanner(System.in);
    private final FileDataSource fileDataSrc = new FileDataSource();

    @Override
    public void onCreate() {
        setMainScreen(MenuScreen.class);

//        FileReader fileReader = null;
//        BufferedReader bufferedReader = null;
        try {
//            fileReader = new FileReader("/home/lt-sw-195/Área de Trabalho/db.csv");
//            bufferedReader = new BufferedReader(fileReader);
            List<Movie> movieList = fileDataSrc.searchAll();
            System.out.println("ID  |  Nome");
            for(Movie movie:movieList){
                System.out.println("[" + movie.getUuid() + "]" + " " + movie.getMovieName());
            }
//            while (bufferedReader.ready()) {
//                var line = bufferedReader.readLine().strip();
//                if (!line.isEmpty() || line.isBlank()) {
//                    var info = line.split(";");
//                    System.out.println("[" + info[0] + "]" + " " + info[1]);
//                }
//            }
        } catch (IOException e) {
            System.out.println("===============================");
            System.out.println("Ocorreu um problema ao buscar o filme.");
        }
//        finally {
//            try {
//                if (fileReader != null && bufferedReader != null) {
//                    bufferedReader.close();
//                    fileReader.close();
//                }
//            } catch (Exception e) {
//                e.getStackTrace();
//            }
//
//        }
        returnToMain(Constants.TEXT_TO_MAIN);
    }
}
