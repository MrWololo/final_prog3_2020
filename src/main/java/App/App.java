package App;

import App.BackEnd.LocalData;
import App.BackEnd.Storage;
import App.GUI.MainMenu;

public class App {
    public static void main(String[] args) {
        if (Storage.exists()) {
            LocalData.processFetch(Storage.fetchRegistro());
        }
        new MainMenu();
    }
}
