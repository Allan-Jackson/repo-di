package repodi.beans;


import java.util.Objects;

public class Movie {
    private String uuid;
    private String movieName;
    private String movieGenre;
    private String movieDate;
    private String movieDirector;

    public Movie(String movieName, String movieGenre, String movieDate, String movieDirector) {
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.movieDate = movieDate;
        this.movieDirector = movieDirector;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return movieName.equals(movie.movieName) && movieGenre.equals(movie.movieGenre) && movieDate.equals(movie.movieDate) && movieDirector.equals(movie.movieDirector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, movieGenre, movieDate, movieDirector);
    }
}
