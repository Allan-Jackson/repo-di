package repodi;



import screens.*;

import java.util.Scanner;

//programa vai ser inicialmente um monólito

public class MainProgram {

    public static void main(String[] args) {
        
        String movieName = "";
        String movieGenre = "";
        String movieDate = "";
        String movieDirector = "";

        MenuScreen menuScreen = new MenuScreen();
        menuScreen.setMenuTitle(MenuOptions.MOVIE_REPOSITORY_MANAGER);
        menuScreen.addItemToMenu(MenuOptions.ADD_MOVIE);
        menuScreen.addItemToMenu(MenuOptions.LIST_MOVIES);
        menuScreen.addItemToMenu(MenuOptions.SEARCH_MOVIE);
        menuScreen.setChoiceHandler(opText->{
            switch (opText) {
                case MenuOptions.ADD_MOVIE: //Add
                    Screen addMovie = new AddMovieScreen();
                    addMovie.initialize();
                    break;
                case MenuOptions.LIST_MOVIES: //List
                    Screen listMovie = new ListMovieScreen();
                    listMovie.initialize();
                    break;
                case MenuOptions.SEARCH_MOVIE: //Search
                    Screen searchMovie = new SearchMovieScreen();
                    searchMovie.initialize();
                    break;
                default:
                    System.exit(0);
            }
            var mScanner = new Scanner(System.in);
            System.out.println("\nTecle \"Enter\" para voltar ao menu...");
            mScanner.nextLine();
            System.out.println("\n\n\n");
            menuScreen.initialize();
        });
        menuScreen.initialize();
    }
}