package app.console.screens;

public interface Screen {
    /**
     * método responsável por inicializar a Screen.
     */
    void onCreate();

    /**
     * Inicia a Screen especificada, mostrando a mensagem msg antes do usuário teclar 'Enter'.
     * @param screenClass classe Screen que deve ser inicializada.
     */
    default <T extends Screen> void startScreen(Class<T> screenClass) throws Exception{
        //todo: fazer a inicialização para a classe Screen especificada
        System.out.println("\n\n\n\n\n");
        Screen screen = screenClass.getConstructor(null).newInstance();
        screen.onCreate();
    }
}
