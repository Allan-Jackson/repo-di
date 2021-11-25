package app.console.screens;

import app.console.constants.Constants;
import app.console.screens.initializer.IScreenInitializer;
import org.jetbrains.annotations.NotNull;
import repodi.FileDataSource;
import repodi.MovieDAO;
import repodi.MySqlDataSource;

import java.io.IOException;
import java.util.Scanner;

public class ClearAllScreen extends BaseScreen{

    private final MovieDAO dataSource;

    public ClearAllScreen(MovieDAO dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void onCreate() {
        setMainScreen(MenuScreen.class);
        System.out.println("   AVISO!!   ");
        System.out.println("---------");
        System.out.println("Essa ação irá excluir todos os filmes armazenados no banco de dados,");
        System.out.print("você tem certeza que deseja continuar (y/n): ");

        var scanner = new Scanner(System.in);
        var op = scanner.next();
        if(op.toUpperCase().equals("Y")){
            try{
                dataSource.clearAll();
                System.out.println("Operação realizada com sucesso!");
            }catch (Exception e){
                System.out.println("Houve um problema na realização da operação.");
            }
        }else{
            System.out.println("Operação não realizada.");
        }
        returnToMain(Constants.TEXT_TO_MAIN);
    }

}
