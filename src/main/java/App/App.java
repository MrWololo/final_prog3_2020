package App;

import com.alee.laf.WebLookAndFeel;

import App.Data.Provider;
import App.Data.Storage;
import App.GUI.MainMenu;

public class App {
    public static void main(String[] args) {
        WebLookAndFeel.install();
        if (Storage.exists()) {
            Provider.processFetch(Storage.fetchRegistro());
        }
        new MainMenu();
    }
}
