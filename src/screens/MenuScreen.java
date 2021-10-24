package screens;

import java.util.Scanner;

public class MenuScreen implements Screen{

    @Override
    public void show() {
        var mScanner = new Scanner(System.in);
        System.out.println("MOVIE REPOSITORY MANAGER");
        System.out.println("1 - Adicionar filme");
        System.out.println("2 - Listar filmes");
        System.out.println("3 - Buscar um filme");
        System.out.print("\nEscolha a operação: ");
        var op = mScanner.nextInt();
        mScanner.nextLine(); //pegar o 'n' que sobrou no buffer
        switch (op) {
            case 1: //Add
                Screen addMovie = new AddMovieScreen();
                addMovie.show();
                break;
            case 2: //List
                Screen listMovie = new ListMovieScreen();
                listMovie.show();
                break;
            case 3: //Search
                Screen searchMovie = new SearchMovieScreen();
                searchMovie.show();
                break;
            default:
                System.exit(0);
        }
    }
}
