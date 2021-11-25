package app.console.screens;

import app.console.screens.initializer.ScreenInitializer;

import java.util.Scanner;


//todo: ver como melhorar o generics aqui
public abstract class BaseScreen<A extends IScreen> implements IScreen {
    /**
     * Tela principal para qual o método {@link BaseScreen#returnToMain(String)} deve redirecionar.
     */
    private Class<A> mainScreen;

    public Class<A> getMainScreen() {
        return mainScreen;
    }

    public void setMainScreen(Class<A> mainScreen) {
        this.mainScreen = mainScreen;
    }

    public <T extends IScreen> void startScreen(Class<T> screenClass) throws Exception{
        var initializer = new ScreenInitializer();
        startScreen(screenClass,initializer);
    }

    /**
     * Redireciona para a Screen especificada no atributo {@link BaseScreen#mainScreen}, após a exibição da mensagem informada.
     * @param msg a mensagem para ser exibida antes do redirecionamento.
     */
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
