import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

//programa vai ser inicialmente um monólito

public class MainProgram {

    //todo: melhorar a leitura e fluxo do monólito utilizando funções

    public static void menuScreen() {
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
                addMovieScreen();
                break;
            case 2: //List
                listMoviesScreen();
                break;
            case 3: //Search
                searchMovieScreen();
                break;
            default:
                System.exit(0);
        }
    }

    public static void addMovieScreen() {
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
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            //var outputStream= new FileOutputStream("/home/lt-sw-195/Área de Trabalho/db.csv");
            fileWriter = new FileWriter("/home/lt-sw-195/Área de Trabalho/db.csv", true);
            bufferedWriter = new BufferedWriter(fileWriter);

            //cria o ID do filme
            bufferedWriter.write(UUID.randomUUID() + ";");
            for (int i = 0; i < data.size(); i++) {
                if (i == data.size() - 1) {
                    bufferedWriter.write(data.get(i) + "\n");
                    break;
                }
                bufferedWriter.write(data.get(i) + ";");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("===============================");
            System.out.println("Ocorreu um problema na escrita do arquivo.");
        } finally {
            if (fileWriter != null && bufferedWriter != null) {
                try {
                    fileWriter.close();
                    bufferedWriter.close();
                } catch (IOException ioe) {
                    ioe.getStackTrace();
                }
            }
        }
        returnToMenu();
    }

    private static void searchMovieScreen() {
        var mScanner = new Scanner(System.in);
        System.out.print("Digite o ID do filme: ");
        var uuid = mScanner.nextLine();

        FileReader fR = null;
        BufferedReader bR = null;
        try {
            fR = new FileReader("/home/lt-sw-195/Área de Trabalho/db.csv");
            bR = new BufferedReader(fR);
            String[] info = null;
            while (bR.ready()) {
                var line = bR.readLine().strip();
                if (!line.isEmpty() || line.isBlank()) {
                    info = line.split(";");
                    if (info[0].equals(uuid)) {
                        break;
                    }
                }
            }
            if (info != null && info.length > 0) {
                System.out.println("\n=====Informações do Filme=====");
                System.out.println("UUID: " + info[0]);
                System.out.println("Nome: " + info[1]);
                System.out.println("Gênero: " + info[2]);
                System.out.println("Lançamento: " + info[3]);
                System.out.println("Diretor: " + info[4]);
            }
        } catch (Exception e) {
            System.out.println("===============================");
            System.out.println("Ocorreu um problema na leitura do arquivo.");
        } finally {
            try {
                if (fR != null && bR != null) {
                    bR.close();
                    fR.close();
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        returnToMenu();
    }

    private static void listMoviesScreen() {
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
        returnToMenu();
    }

    public static void returnToMenu() {
        var mScanner = new Scanner(System.in);
        System.out.println("\nTecle \"Enter\" para voltar ao menu...");
        mScanner.nextLine();
        System.out.println("\n\n\n");
        menuScreen();
    }

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String movieName = "";
        String movieGenre = "";
        String movieDate = "";
        String movieDirector = "";

        //todo: função para desenhar o menu
        menuScreen();

        //todo: fazer ciclo para associar os dados às variáveis correspondentes
          //  movieName = data.get(0);
          //  movieGenre = data.get(1);
          //  movieDate = data.get(2);
          //  movieDirector = data.get(3);
    }
}