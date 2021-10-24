package screens;

import repodi.FileDataSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddMovieScreen implements Screen{
    @Override
    public void show() {
        Screen menu = new MenuScreen();
        FileDataSource fileDataSrc = new FileDataSource();

        //todo: receber dados sobre o filme
        var mScanner = new Scanner(System.in);
        //List.of() método de utilizada da interface [default] que retorna uma lista imutável
        //mesma ideia do listOf() do Kotlin *a partir do Java 9
        var dataQuestions = List.of(
                "Nome do filme",
                "Gênero do filme",
                "Data de lançamento do filme",
                "Nome do diretor do filme"
        );
        List<String> data = new ArrayList<>(); //recebe os dados inseridos

        //todo: ciclo para receber os dados
        for (String question : dataQuestions) {
            System.out.println(question + ": ");
            data.add(mScanner.nextLine());
        }

        //todo: salvar os dados num arquivo
        fileDataSrc.writeToFile(data);
        returnToMenu();
    }


    private void returnToMenu() {
        Screen menu = new MenuScreen();
        var mScanner = new Scanner(System.in);
        System.out.println("\nTecle \"Enter\" para voltar ao menu...");
        mScanner.nextLine();
        System.out.println("\n\n\n");
        menu.show();
    }
}
