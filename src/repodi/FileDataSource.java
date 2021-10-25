package repodi;

import repodi.beans.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//todo; utilizar DI no filename e deixar o tratamento da exceção para quem chamar
public class FileDataSource {
    public void saveMovie(Movie movie) throws IOException{
        FileWriter fileWriter = new FileWriter("/home/lt-sw-195/Área de Trabalho/db.csv", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        //cria o ID do filme
        bufferedWriter.write(UUID.randomUUID() + ";");
        bufferedWriter.write(movie.getMovieName() + ";");
        bufferedWriter.write(movie.getMovieGenre() + ";");
        bufferedWriter.write(movie.getMovieDate() + ";");
        bufferedWriter.write(movie.getMovieDirector() + "\n");

        bufferedWriter.close();
        fileWriter.close();
    }

    public Movie searchMovie(String uuid) throws IOException{
        Movie movie = null;
        FileReader fR = new FileReader("/home/lt-sw-195/Área de Trabalho/db.csv");
        BufferedReader bR = new BufferedReader(fR);
        String[] info = null;

        while (bR.ready()) {
            var line = bR.readLine().strip();
            if (!line.isEmpty() && !line.isBlank()) {
                info = line.split(";");
                if (info[0].equals(uuid)) {
                    movie = new Movie(
                            info[1],
                            info[2],
                            info[3],
                            info[4]
                    );
                    movie.setUuid(info[0]);
                    break;
                } //todo: consertar a lógica caso o uuid não exista na base
            }
        }
        bR.close();
        fR.close();
        return movie;
    }

    /**
     * deve passar por cada linha do arquivo, gerar um Movie para cada e adicionar na lista, no final deve retorná-la
     * @return lista contendo todos os filmes armazenados.
     */
    public List<Movie> searchAll() throws IOException{
        List<Movie> movieList = new ArrayList<>();
        FileReader fR = new FileReader("/home/lt-sw-195/Área de Trabalho/db.csv");
        BufferedReader bR = new BufferedReader(fR);
        String[] info = null;

        while (bR.ready()) {
            var line = bR.readLine().strip();
            if (!line.isEmpty() && !line.isBlank()) {
                info = line.split(";");
                var movie = new Movie(info[1], info[2], info[3] , info[4]);
                movie.setUuid(info[0]);
                movieList.add(movie);
            }
        }
        bR.close();
        fR.close();
        return movieList;
    }
}
