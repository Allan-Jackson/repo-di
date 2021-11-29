package app.console;

import app.console.constants.Constants;
import app.console.screens.BaseScreen;
import app.console.screens.MenuScreen;
import repodi.persistence.beans.Movie;
import repodi.persistence.exceptions.MovieNotFoundException;
import repodi.persistence.repositories.MovieRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateMovieScreen extends BaseScreen {

    MovieRepository movieRepository;

    public UpdateMovieScreen(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    Scanner scanner = new Scanner(System.in);

    List<String> data = new ArrayList<>();

    @Override
    public void onCreate() {
        setMainScreen(MenuScreen.class);
        try {
            System.out.print("Digite o UUID: ");
            var uuid = scanner.nextLine();
            var movie = movieRepository.find(uuid);
            System.out.println("\n\n");
            var dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            System.out.print("Vc realmente deseja alterar o filme \"" + movie.getMovieName() + "\" de " + dateFormatter.format(movie.getMovieDate()) + "?(y/n): ");
            var confirm = scanner.nextLine();
            if(confirm.equalsIgnoreCase("y")){
                updateMovie(movie);
            }
        } catch (MovieNotFoundException e) {
            System.out.println("\n--Filme não encontrado--");
        } catch (Exception e) {
            System.out.println("\nUm problema ocorreu, tente novamente mais tarde:(");
        }
        returnToMain(Constants.TEXT_TO_MAIN);
    }

    private void updateMovie(Movie movie) throws Exception {
        System.out.println("\n\n\n\n");
        System.out.println("ATUALIZAR FILME");
        System.out.println("=====================");

        String confirm;
        boolean hasChange = false;

        //NOME
        System.out.println("");
        System.out.print("Deseja alterar o nome?" + "(y/n): ");
        confirm = scanner.nextLine();
        if(confirm.equalsIgnoreCase("y")){
            hasChange = true;
            System.out.println("");
            System.out.print("Insira o novo nome: ");
            movie.setMovieName(scanner.nextLine());
            System.out.println("OK");
        }
        //GÊNERO
        System.out.println("");
        System.out.print("Deseja alterar o gênero?" + "(y/n): ");
        confirm = scanner.nextLine();
        if(confirm.equalsIgnoreCase("y")){
            hasChange = true;
            System.out.println("");
            System.out.print("Insira o novo gênero: ");
            movie.setMovieGenre(scanner.nextLine());
            System.out.println("OK");
        }
        //DATA LANÇAMENTO
        System.out.println("");
        System.out.print("Deseja alterar a data de lançamento?" + "(y/n): ");
        confirm = scanner.nextLine();
        if(confirm.equalsIgnoreCase("y")){
            hasChange = true;
            System.out.println("");
            System.out.print("Insira a nova data de lançamento: ");
            movie.setMovieDate(new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine()));
            System.out.println("OK");
        }
        //DIRETOR
        System.out.println("");
        System.out.print("Deseja alterar o diretor?" + "(y/n): ");
        confirm = scanner.nextLine();
        if(confirm.equalsIgnoreCase("y")){
            hasChange = true;
            System.out.println("");
            System.out.print("Insira o novo diretor: ");
            movie.setMovieDirector(scanner.nextLine());
            System.out.println("OK");
        }
        if(hasChange){
            movieRepository.save(movie);
            System.out.println("\nFilme atualizado com sucesso!");
        }else{
            System.out.println("\n--Nenhuma alteração realizada--");
        }
    }
}
