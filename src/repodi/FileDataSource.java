package repodi;

import repodi.beans.Movie;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileDataSource implements MovieDAO{
    private final String FILE_PATH;

    public FileDataSource(String filename){
        FILE_PATH = filename;
    }

    public void saveMovie(Movie movie) throws IOException{
        FileWriter fileWriter = new FileWriter(FILE_PATH, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        //cria o ID do filme
        bufferedWriter.write(UUID.randomUUID() + ";");
        bufferedWriter.write(movie.getMovieName() + ";");
        bufferedWriter.write(movie.getMovieGenre() + ";");
        String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(movie.getMovieDate());
        bufferedWriter.write(formattedDate + ";");
        bufferedWriter.write(movie.getMovieDirector() + "\n");

        bufferedWriter.close();
        fileWriter.close();
    }

    public Movie searchMovie(String uuid) throws MovieNotFoundException{
        Movie movie = null;
        String[] info;

        try (FileReader fR = new FileReader(FILE_PATH); BufferedReader bR = new BufferedReader(fR)) {
            while (bR.ready()) {
                var line = bR.readLine().strip();
                if (!line.isEmpty() && !line.isBlank()) {
                    info = line.split(";");
                    if (info[0].equals(uuid)) {
                        movie = new Movie(
                                info[1],
                                info[2],
                                (new SimpleDateFormat("yyyy/MM/dd")).parse(info[3]), //data
                                info[4]
                        );
                        movie.setUuid(info[0]);
                        break;
                    }
                }
            }
            //verifica se o filme com o UUID passado foi encontrado
            if(movie!=null){
                return movie;
            }else{
                throw new MovieNotFoundException("Não existe um filme com o UUID informado.");
            }
        } catch (Exception e) {
            throw new MovieNotFoundException("Houve um problema na leitura do arquivo de filmes.", e);
        }
    }

    public List<Movie> searchAll() throws MovieNotFoundException{
        List<Movie> movieList = new ArrayList<>();
        String[] info;

        try(FileReader fR = new FileReader(FILE_PATH);
            BufferedReader bR = new BufferedReader(fR)){
            while (bR.ready()) {
                var line = bR.readLine().strip();
                if (!line.isEmpty() && !line.isBlank()) {
                    info = line.split(";");
                    var movie = new Movie(info[1], info[2], (new SimpleDateFormat("dd/MM/yyyy")).parse(info[3]), info[4]);
                    movie.setUuid(info[0]);
                    movieList.add(movie);
                }
            }
            return movieList;
        }catch(Exception e){
            throw new MovieNotFoundException("Houve um problema na leitura do arquivo de filmes.", e);
        }
    }

    public void clearAll() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("");
    }
}
