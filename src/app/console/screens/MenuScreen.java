package app.console.screens;

import app.console.ChoiceHandler;
import app.console.Menu;
import app.console.constants.Constants;


public class MenuScreen extends BaseScreen {

    Menu mMenu = new Menu();

    @Override
    public void onCreate() {
        configureMenu();
        setHandlers();
        mMenu.show();
    }

    void configureMenu(){
        mMenu.setMenuTitle(Constants.MOVIE_REPOSITORY_MANAGER);
        mMenu.addItemToMenu(Constants.ADD_MOVIE);
        mMenu.addItemToMenu(Constants.LIST_MOVIES);
        mMenu.addItemToMenu(Constants.SEARCH_MOVIE);
        mMenu.addItemToMenu(Constants.CLEAR_ALL);
    }

    void setHandlers(){

        mMenu.setChoiceHandler(opText->{
            try{
                switch (opText) {
                    case Constants.ADD_MOVIE:
                        startScreen(AddMovieScreen.class);
                        break;
                    case Constants.LIST_MOVIES:
                        startScreen(ListMovieScreen.class);
                        break;
                    case Constants.SEARCH_MOVIE:
                        startScreen(SearchMovieScreen.class);
                        break;
                    case Constants.CLEAR_ALL:
                        startScreen(ClearAllScreen.class);
                        break;
                    default:
                        System.exit(0);
                }
            }catch (Exception e){
                System.out.println("Houve um problema para iniciar a tela :(");
                System.exit(0);
            }
        });
    }
}
