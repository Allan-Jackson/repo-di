package app.console.screens.initializer;

import app.console.screens.IScreen;
import org.jetbrains.annotations.NotNull;
import repodi.FileDataSource;
import repodi.Property;
import repodi.PropertyUtils;

public class ScreenInitializer implements IScreenInitializer{
    private final String MENU_SCREEN = "MenuScreen";
    private final String ADD_MOVIE_SCREEN = "AddMovieScreen";
    private final String LIST_MOVIE_SCREEN = "ListMovieScreen";
    private final String SEARCH_MOVIE_SCREEN = "SearchMovieScreen";


    @Override
    public <T extends IScreen> T getInstance(@NotNull Class<T> clazz) throws Exception {

        T retorno = null;
        switch (clazz.getSimpleName()){
            case MENU_SCREEN:
              retorno = clazz.getConstructor(null).newInstance();
              break;
            default:
                retorno = clazz.getConstructor(FileDataSource.class)
                        .newInstance(new FileDataSource(PropertyUtils.getString(Property.MOVIE_DATABASE_FILENAME.name())));
        }

        return retorno;
    }
}
