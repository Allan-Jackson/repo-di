package repodi;

import screens.MenuScreen;
import screens.Screen;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

//programa vai ser inicialmente um monólito

public class MainProgram {

    public static void main(String[] args) {

        String movieName = "";
        String movieGenre = "";
        String movieDate = "";
        String movieDirector = "";

        Screen menu = new MenuScreen();
        menu.show();
    }
}