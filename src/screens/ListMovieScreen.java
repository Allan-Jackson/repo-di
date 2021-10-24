package screens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

//todo: utilizar FileDataSource para manipular o arquivo com os dados
public class ListMovieScreen implements Screen{

    @Override
    public void initialize() {

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("/home/lt-sw-195/Área de Trabalho/db.csv");
            bufferedReader = new BufferedReader(fileReader);

            System.out.println("ID  |  Nome");
            while (bufferedReader.ready()) {
                var line = bufferedReader.readLine().strip();
                if (!line.isEmpty() || line.isBlank()) {
                    var info = line.split(";");
                    System.out.println("[" + info[0] + "]" + " " + info[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("===============================");
            System.out.println("Ocorreu um problema na leitura do arquivo.");
        } finally {
            try {
                if (fileReader != null && bufferedReader != null) {
                    bufferedReader.close();
                    fileReader.close();
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
