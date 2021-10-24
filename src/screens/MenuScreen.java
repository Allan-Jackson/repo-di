package screens;

import java.util.*;

public class MenuScreen implements Screen{
    private int idController = 0;
    Menu mMenu = new Menu();
    ChoiceHandler handleChoice;

    public void setChoiceHandler(ChoiceHandler choiceHandler){
        handleChoice = choiceHandler;
    }

    private class Menu{
        String title;
        Set<MenuItem> mMenuItems = new TreeSet<>();

        public void show(){
            System.out.println(this.title);
            for(var menuItem: mMenuItems){
                System.out.println(menuItem.id + " - " + menuItem.text);
            }
            System.out.print("\nEscolha a operação: ");
        }
    }

    private class MenuItem implements Comparable{
        int id;
        String text;
        public MenuItem(String text){
            this.text = text;
        }
        public void setId() {
            this.id = ++idController;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MenuItem)) return false;
            MenuItem menuItem = (MenuItem) o;
            return text.equals(menuItem.text);
        }
        @Override
        public int hashCode() {
            return Objects.hash(text);
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(((MenuItem)o).id, id);
        }
    }

    //todo: métodos para configurar o menu
    public void setMenuTitle(String menuTitle){
        mMenu.title = menuTitle;
    }
    public boolean removeMenuItem(String item){
        MenuItem itemToRemove = null;
        for(MenuItem menuItem:mMenu.mMenuItems){
            if(menuItem.text.equals(item)){
                itemToRemove = menuItem;
                break;
            }
        }
        return itemToRemove != null && mMenu.mMenuItems.remove(itemToRemove);
    }

    public boolean addItemToMenu(String text){
        var menuItem = new MenuItem(text);
        if(mMenu.mMenuItems.add(menuItem)){
            menuItem.setId();
            return true;
        };
        return false;
    }
    public List<String> getMenuItems(){
        List<String> menuItemsList = new ArrayList<>();
        for(MenuItem menuItem:mMenu.mMenuItems){
            menuItemsList.add(menuItem.text);
        }
        return menuItemsList;
    }


    @Override
    public void initialize() {
        mMenu.show();
        var mScanner = new Scanner(System.in);
        var op = mScanner.nextInt();
        mScanner.nextLine(); //pegar o 'n' que sobrou no buffer
        chooseOption(op);
    }
    private void chooseOption(int op){
        String optionString = "";
        //todo: pegar o texto do MenuItem e passar para handler
        for(var menuItem:mMenu.mMenuItems){
            if(menuItem.id==op){
                optionString = menuItem.text;
                break;
            }
        }
        handleChoice.handleChoice(optionString);
    }
}
