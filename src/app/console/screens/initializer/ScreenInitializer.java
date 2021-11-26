package app.console.screens.initializer;

import app.console.screens.IScreen;
import org.jetbrains.annotations.NotNull;
import repodi.*;
import repodi.persistence.repositories.MovieRepository;
import repodi.persistence.repositories.daos.FileMovieDAO;
import repodi.persistence.repositories.daos.MovieDAO;
import repodi.persistence.repositories.daos.MySqlMovieDAO;

public class ScreenInitializer implements IScreenInitializer{
    private final String MENU_SCREEN = "MenuScreen";
    private final String ADD_MOVIE_SCREEN = "AddMovieScreen";
    private final String LIST_MOVIE_SCREEN = "ListMovieScreen";
    private final String SEARCH_MOVIE_SCREEN = "SearchMovieScreen";
    private final String CLEAR_ALL_SCREEN = "ClearAllScreen";

    @Override
    public <T extends IScreen> T getInstance(@NotNull Class<T> clazz) throws Exception {

        T retorno = null;
        switch (clazz.getSimpleName()){
            case MENU_SCREEN:
              retorno = clazz.getConstructor(null).newInstance();
              break;
            case LIST_MOVIE_SCREEN:
                retorno = clazz.getConstructor(MovieRepository.class)
                        .newInstance(new MovieRepository(new MySqlMovieDAO()));
                break;
            case ADD_MOVIE_SCREEN:
                retorno = clazz.getConstructor(MovieRepository.class)
                        .newInstance(new MovieRepository(new MySqlMovieDAO()));
                break;
            case SEARCH_MOVIE_SCREEN:
                retorno = clazz.getConstructor(MovieRepository.class)
                        .newInstance(new MovieRepository(new MySqlMovieDAO()));
                break;
            case CLEAR_ALL_SCREEN:
                retorno = clazz.getConstructor(MovieRepository.class)
                        .newInstance(new MovieRepository(new MySqlMovieDAO()));
                break;
            default:
                retorno = clazz.getConstructor(MovieDAO.class)
                        .newInstance(new FileMovieDAO(PropertyUtils.getString(Property.MOVIE_DATABASE_FILENAME.name())));
        }

        return retorno;
    }
}
