package repodi;

import repodi.beans.Movie;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySqlDataSource {
    public Connection conn = null;

    public MySqlDataSource() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/repo_di",
                    "root_980","sql@1234");
    }

    public List<Movie> searchAll() throws MovieNotFoundException {
        List<Movie> movieList = new ArrayList<>();
        //try with resources automatically will close stm and result
        try (var stm = conn.createStatement();
             var result = stm.executeQuery("SELECT * FROM TB_MOVIE");) {
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
    public void saveMovie(Movie movie) throws SQLException {
        var stm = conn.createStatement();
        var affectedRows = stm.executeUpdate("INSERT INTO TB_MOVIE(id_movie,name,genre,release_date,director)\n" +
                "VALUES('" + UUID.randomUUID() + "','"+movie.getMovieName() +"','"+movie.getMovieGenre()+"','"+movie.getMovieDate()+"','"+movie.getMovieDirector()+"');");
    }
    public Movie searchMovie(String uuid) throws MovieNotFoundException{
        try (var stm = conn.createStatement();
            var result = stm.executeQuery("SELECT * FROM TB_MOVIE WHERE id_movie='"+uuid+"'");) {
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
    public void clearAll() throws SQLException {
        var stm = conn.createStatement();
        stm.executeUpdate("TRUNCATE TABLE TB_MOVIE");
    }
}
