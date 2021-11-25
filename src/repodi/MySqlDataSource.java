package repodi;

import org.jetbrains.annotations.NotNull;
import repodi.beans.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySqlDataSource implements MovieDAO{

    public List<Movie> searchAll() throws MovieNotFoundException {
        List<Movie> movieList = new ArrayList<>();
        //try with resources automatically will close stm and result
        try (Connection connection = DBConnection.getConnection();
             Statement stm = connection.createStatement();
             ResultSet result = stm.executeQuery("SELECT * FROM TB_MOVIE");) {
            while (result.next()) {
                String uuid = result.getString("id_movie");
                String movieName = result.getString("name");
                String movieGenre = result.getString("genre");
                String movieDate = result.getString("release_date");
                String movieDirector = result.getString("director");
                var movie = new Movie(movieName, movieGenre, movieDate, movieDirector);
                movie.setUuid(uuid);
                movieList.add(movie);
            }
            return movieList;
        } catch (SQLException e) {
            throw new MovieNotFoundException("Um problema de operação ocorreu com o banco de dados", e);
        }
    }
    public void saveMovie(@NotNull Movie movie) throws Exception {
        try(Connection connection = DBConnection.getConnection();
            var stm = connection.createStatement();){
            var affectedRows = stm.executeUpdate("INSERT INTO TB_MOVIE(id_movie,name,genre,release_date,director)\n" +
                    "VALUES('" + UUID.randomUUID() + "','"+movie.getMovieName() +"','"+movie.getMovieGenre()+"','"+movie.getMovieDate()+"','"+movie.getMovieDirector()+"');");
        }catch(SQLException e){
            throw new Exception(e);
        }
    }
    public Movie searchMovie(@NotNull String uuid) throws MovieNotFoundException{
        try (Connection connection = DBConnection.getConnection();
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM TB_MOVIE WHERE id_movie='"+uuid+"'");) {
            result.next();
            String movieName = result.getString("name");
            String movieGenre = result.getString("genre");
            String movieDate = result.getString("release_date");
            String movieDirector = result.getString("director");
            var movie = new Movie(movieName, movieGenre, movieDate, movieDirector);
            movie.setUuid(uuid);
            return movie;
        } catch (SQLException e) {
            throw new MovieNotFoundException("Um problema de operação ocorreu com o banco de dados", e);
        }
    }
    public void clearAll() throws Exception {
        try(Connection connection = DBConnection.getConnection();
            var stm = connection.createStatement();){
            stm.executeUpdate("TRUNCATE TABLE TB_MOVIE");
        }catch(SQLException e){ //lançar exceção diferente de SQLException desacopla a aplicação da API de JDBC
            throw new Exception(e);
        }
    }
}
