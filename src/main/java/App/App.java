package App;

import com.alee.laf.WebLookAndFeel;

import App.Data.Provider;
import App.Data.Storage;
import App.GUI.MainMenu;

public class App {
    public static void main(String[] args) {
        WebLookAndFeel.install();
        if (Storage.usersExist()) {
            Provider.processFetch(Storage.fetchRegistro());
            System.out.println(Provider.getUsers());
        }
        if (Storage.avionesExist()) {
            Provider.setAviones(Storage.fetchAviones());
        }
        if (Storage.viajesExist()) {
            Provider.setViajes(Storage.fetchViajes());
        }

        new MainMenu();
    }
}
