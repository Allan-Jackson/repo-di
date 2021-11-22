package repodi;

import repodi.beans.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileDataSource{
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
        bufferedWriter.write(movie.getMovieDate() + ";");
        bufferedWriter.write(movie.getMovieDirector() + "\n");

        bufferedWriter.close();
        fileWriter.close();
    }

    /**
     * Retorna o filme que possui o uuid informado. Caso ele não exista ou ocorrer algum erro, retorna
     * a exceção MovieNotFoundException.
     * @param uuid identificador do filme que se deseja buscar.
     * @return filme que possui o uuid informado.
     * @throws MovieNotFoundException se não existir um filme com o uuid informado
     * ou se um erro para encontrar o arquivo de filmes ou lê-lo ocorrer.
     */
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
                                info[3],
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
        } catch (IOException io) {
            throw new MovieNotFoundException("Houve um problema na leitura do arquivo de filmes.", io);
        }
    }

    /**
     * deve passar por cada linha do arquivo, gerar um Movie para cada e adicionar na lista, no final deve retorná-la
     * @return lista contendo todos os filmes armazenados.
     */
    public List<Movie> searchAll() throws MovieNotFoundException{
        List<Movie> movieList = new ArrayList<>();
        String[] info;

        try(FileReader fR = new FileReader(FILE_PATH);
            BufferedReader bR = new BufferedReader(fR)){
            while (bR.ready()) {
                var line = bR.readLine().strip();
                if (!line.isEmpty() && !line.isBlank()) {
                    info = line.split(";");
                    var movie = new Movie(info[1], info[2], info[3] , info[4]);
                    movie.setUuid(info[0]);
                    movieList.add(movie);
                }
            }
            return movieList;
        }catch(IOException io){
            throw new MovieNotFoundException("Houve um problema na leitura do arquivo de filmes.", io);
        }
    }

    /**
     * Limpa a lista de filmes do repositório, excluindo todos os filmes existentes.
     * @throws IOException caso ocorra algum problema de leitura/escrita ou para abrir o arquivo contendo os filmes.
     */
    public void clearAll() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("");
    }
}
