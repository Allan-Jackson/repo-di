package app.console.screens;

import java.util.Scanner;

//todo: ver como melhorar o generics aqui
public abstract class BaseScreen<A extends Screen> implements Screen{
    private   Class<A> mainScreen;
//    public <Y extends Screen> Class<Y> get;

    public Class<A> getMainScreen() {
        return mainScreen;
    }

    public void setMainScreen(Class<A> mainScreen) {
        this.mainScreen = mainScreen;
    }

    protected void returnToMain(String msg) {
        try{
            var scanner = new Scanner(System.in);
            System.out.println("\n"+msg+" ");
            scanner.nextLine();
            startScreen(mainScreen);
        }catch (Exception e){
            System.out.println("Houve um problema para iniciar a tela :(");
            System.exit(0);
        }
    }
}
