package app.console.screens;

import app.console.screens.initializer.IScreenInitializer;
import org.jetbrains.annotations.NotNull;

public interface IScreen {
    /**
     * método responsável por inicializar a Screen.
     */
    void onCreate();

    /**
     * Inicia a Screen especificada, usando initializer como configurador responsável por
     * realizar a instanciação da classe e fazer as devidas configurações necessárias.
     * Se initializer == null, então o método vai tentar instanciar screenClass usando um construtor vazio.
     * @param screenClass classe Screen que deve ser inicializada.
     * @param initializer objeto responsável por instanciar e configurar a Screen informada.
     */
    default <T extends IScreen> void startScreen(@NotNull Class<T> screenClass, IScreenInitializer initializer) throws Exception{
        System.out.println("\n\n\n\n\n");
        IScreen screen;
        if(initializer==null){
            screen = screenClass.getConstructor(null).newInstance();
        }else{
            screen = initializer.getInstance(screenClass);
        }
        screen.onCreate();
    }
}
