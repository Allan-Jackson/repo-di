package screens;

import repodi.FileDataSource;

import java.util.Scanner;

//todo: tratamento da exceção de searchInFile
public class SearchMovieScreen implements Screen{
    @Override
    public void initialize() {
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
    }
}
