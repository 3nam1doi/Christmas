/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import java.util.List;
import utils.Utils;

/**
 *
 * @author Nguyen Trung Nam
 */
public class Menu implements I_Menu {

    private String Tmenu;
    private final List<String> optionList = new ArrayList<>();

    public Menu(String Tmenu) {
        this.Tmenu = Tmenu;
    }

    public void addNewOptiont(String option) {
        optionList.add(option);
    }

    @Override
    public void showMenu() {
        if (optionList.isEmpty()) {
            System.out.println("Menu is empty!!!");
            return;
        }

        System.out.println("\n " + Tmenu + "");
        for (String o : optionList) {
            System.out.println(o);
        }
    }

    @Override
    public int getChoice() {
        int max = optionList.size();
        return Utils.getIntMenu(("Choose [1..." + max + "]: "), 0, max + 1);
    }

}
