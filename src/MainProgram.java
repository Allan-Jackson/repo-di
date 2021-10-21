import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

//programa vai ser inicialmente um monólito

public class MainProgram {


    public static void main(String[] args) {
        String movieName = "";
        String movieGenre = "";
        String movieDate = "";
        String movieDirector = "";

        //todo: criar interface para adicionar/buscar filmes

        //todo: receber dados sobre o filme
        //List.of() método de utilizada da interface [default] que retorna uma lista imutável
        //mesma ideia do listOf() do Kotlin *a partir do Java 9
        var dataQuestions = List.of(
                "Nome do filme",
                "Gênero do filme",
                "Data de lançamento do filme",
                "Nome do diretor do filme"
                );
        List<String> data = new ArrayList<>(); //recebe os dados inseridos

        var scanner = new Scanner(System.in);

        //todo: fazer ciclo para receber os dados
        for(String question: dataQuestions){
            System.out.println(question + ": ");
            data.add(scanner.nextLine());
        }
        //todo: fazer ciclo para associar os dados às variáveis correspondentes
//        movieName = data.get(0);
//        movieGenre = data.get(1);
//        movieDate = data.get(2);
//        movieDirector = data.get(3);

        //todo: salvar os dados num arquivo
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            //var outputStream= new FileOutputStream("/home/lt-sw-195/Área de Trabalho/db.csv");
            fileWriter = new FileWriter("/home/lt-sw-195/Área de Trabalho/db.csv",true);
            bufferedWriter = new BufferedWriter(fileWriter);

            //cria o ID do filme
            bufferedWriter.write(UUID.randomUUID() + ";");
            for(int i=0; i<data.size();i++){
                if(i==data.size()-1){
                    bufferedWriter.write(data.get(i) + "\n");
                    break;
                }
                bufferedWriter.write(data.get(i) + ";");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(Exception e){
            System.out.println("===============================");
            System.out.println("Arquivo não pôde ser aberto!");
        }finally {
            if(fileWriter!=null && bufferedWriter!=null){
                try{
                    fileWriter.close();
                    bufferedWriter.close();
                }catch (IOException ioe){
                    ioe.getStackTrace();
                }
            }
        }
    }


}
