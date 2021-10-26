package repodi;



import app.console.screens.MenuScreen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


//programa vai ser inicialmente um monólito

public class MainProgram {

    public static void main(String[] args) {
//        var prop = PropertyUtils.getInt("");
//        System.out.println(prop);
        MenuScreen menuScreen = new MenuScreen();
        menuScreen.onCreate();
    }
}