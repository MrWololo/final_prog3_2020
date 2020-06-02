package App.BackEnd;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import App.Data.Avion;

public abstract class LocalData {
    private static Map<String, String> currentUser = new HashMap<String, String>();
    private static ArrayList<Map<String, String>> users = new ArrayList<Map<String, String>>();

    public static Map<String, String> getCurrentUser() {
        return LocalData.currentUser;
    }

    public static void setCurrentUser(Map<String, String> currentUser) {
        LocalData.currentUser = currentUser;
    }

    public static ArrayList<Map<String, String>> getUsers() {
        return LocalData.users;
    }

    public static void setUsers(ArrayList<Map<String, String>> users) {
        LocalData.users = users;
    }

    public static void addToUsers(Map<String, String> map) {
        LocalData.users.add(map);
    }

    public static boolean userExists(Map<String, String> map) {
        boolean match = false;
        for (Map<String, String> user : users) {
            match = user.get("username").equals(map.get("username"));
        }
        return match;
    }

    public static void processFetch(ArrayList<Map<?, ?>> data) {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map<?, ?> dataMap : data) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("nombre", (String) dataMap.get("nombre"));
            map.put("apellido", (String) dataMap.get("apellido"));
            map.put("dni", (String) dataMap.get("dni"));
            map.put("edad", (String) dataMap.get("edad"));
            map.put("username", (String) dataMap.get("username"));
            map.put("contraseña", (String) dataMap.get("contraseña"));

            list.add(map);
        }
        setUsers(list);
    }
}