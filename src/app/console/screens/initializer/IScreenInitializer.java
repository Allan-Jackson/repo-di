package app.console.screens.initializer;

import app.console.screens.IScreen;
import org.jetbrains.annotations.NotNull;

/**
 * Responsável por construir instâncias para cada classe que implementa a interface IScreen.
 * O método getInstance() deve ser sobrescrito e deve fazer toda a configuração necessária
 * para instanciar uma Screen de um tipo específico.
 */
public interface IScreenInitializer {

    /**
     * Configura e retorna a instância de uma implementação de IScreen.
     * @param clazz<T> classe da implementação de IScreen desejada.
     * @return instância da implementação de IScreen.
     */
    <T extends IScreen> T getInstance(@NotNull Class<T> clazz) throws Exception;
}
