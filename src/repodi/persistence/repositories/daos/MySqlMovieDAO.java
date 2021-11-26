package repodi.persistence.repositories.daos;

import org.jetbrains.annotations.NotNull;
import repodi.persistence.repositories.ConnectionFactory;
import repodi.persistence.exceptions.MovieNotFoundException;
import repodi.persistence.beans.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySqlMovieDAO implements MovieDAO{
    private final String RETRIEVE_MOVIES = "SELECT * FROM TB_MOVIE";
    private final String ADD_MOVIE = "INSERT INTO TB_MOVIE(id_movie,name,genre,release_date,director) VALUES(?, ?, ?, ?, ?);";
    private final String CLEAR_MOVIES = "TRUNCATE TABLE TB_MOVIE";
    private final String GET_MOVIE_BY_ID = "SELECT * FROM TB_MOVIE WHERE id_movie= ?";
    private final String UPD_MOVIE = "UPDATE TB_MOVIE SET name=?,genre=?,release_date=?,director=? WHERE id_movie=?";

    public List<Movie> selectAll() throws MovieNotFoundException {
        List<Movie> movieList = new ArrayList<>();
        //try with resources automatically will close stm and result
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stm = connection.createStatement();
             ResultSet result = stm.executeQuery(RETRIEVE_MOVIES);) {
            while (result.next()) {
                String uuid = result.getString("id_movie");
                String movieName = result.getString("name");
                String movieGenre = result.getString("genre");
                java.util.Date movieDate = result.getDate("release_date");
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
    public void add(@NotNull Movie movie) throws Exception {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stm = connection.prepareStatement(ADD_MOVIE);){
            stm.setString(1, UUID.randomUUID().toString());
            stm.setString(2, movie.getMovieName());
            stm.setString(3, movie.getMovieGenre());
            stm.setDate(4, new Date(movie.getMovieDate().getTime()));
            stm.setString(5, movie.getMovieDirector());
            var affectedRows = stm.executeUpdate();
        }catch(SQLException e){
            throw new Exception(e);
        }
    }

    @Override
    public void update(@NotNull Movie movie) throws Exception {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stm = connection.prepareStatement(UPD_MOVIE);){
            stm.setString(1, movie.getMovieName());
            stm.setString(2, movie.getMovieGenre());
            stm.setDate(3, new Date(movie.getMovieDate().getTime()));
            stm.setString(4, movie.getMovieDirector());
            stm.setString(5, movie.getUuid());
            var affectedRows = stm.executeUpdate();
        }catch(SQLException e){
            throw new Exception(e);
        }
    }

    public Movie select(@NotNull String uuid) throws MovieNotFoundException{
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(GET_MOVIE_BY_ID);) {
            stm.setString(1,uuid);
            ResultSet result = stm.executeQuery();
            result.next();
            String movieName = result.getString("name");
            String movieGenre = result.getString("genre");
            java.util.Date movieDate = result.getDate("release_date");
            String movieDirector = result.getString("director");
            var movie = new Movie(movieName, movieGenre, movieDate, movieDirector);
            movie.setUuid(uuid);
            result.close();
            return movie;
        } catch (SQLException e) {
            throw new MovieNotFoundException("Um problema de operação ocorreu com o banco de dados", e);
        }
    }
    public void clearAll() throws Exception {
        try(Connection connection = ConnectionFactory.getConnection();
            var stm = connection.createStatement();){
            stm.executeUpdate(CLEAR_MOVIES);
        }catch(SQLException e){ //lançar exceção diferente de SQLException desacopla a aplicação da API de JDBC
            throw new Exception(e);
        }
    }
}
