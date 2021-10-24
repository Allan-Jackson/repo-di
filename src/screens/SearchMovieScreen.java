package screens;

import repodi.FileDataSource;

import java.util.Scanner;

public class SearchMovieScreen implements Screen{
    @Override
    public void show() {
        FileDataSource fileDataSrc = new FileDataSource();
        var mScanner = new Scanner(System.in);
        System.out.print("Digite o ID do filme: ");
        var uuid = mScanner.nextLine();
        var info = fileDataSrc.searchInFile(uuid);
        if (info != null && info.length > 0) {
            System.out.println("\n=====Informações do Filme=====");
            System.out.println("UUID: " + info[0]);
            System.out.println("Nome: " + info[1]);
            System.out.println("Gênero: " + info[2]);
            System.out.println("Lançamento: " + info[3]);
            System.out.println("Diretor: " + info[4]);
        }
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
