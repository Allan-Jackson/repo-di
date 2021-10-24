package screens;

@FunctionalInterface
public interface ChoiceHandler {
    /**
     * Define o comportamento que será executado quando uma opção do menu for escolhida pelo usuário.
     * @param selectedOption o texto da opção que foi selecionada pelo usuário.
     */
    void handleChoice(String selectedOption);
}
