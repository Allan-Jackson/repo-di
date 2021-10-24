package screens;

import repodi.FileDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//todo: tratamento da exceção de writeFile
public class AddMovieScreen implements Screen{
    @Override
    public void initialize() {
        FileDataSource fileDataSrc = new FileDataSource();
        var mScanner = new Scanner(System.in);
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

        //salva os dados num arquivo
        fileDataSrc.writeToFile(data);
    }

}
