package App.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import App.BackEnd.Avion;
import App.BackEnd.Viaje;

public abstract class Provider {
    private static Map<String, String> currentUser = new HashMap<String, String>();
    private static ArrayList<Map<String, String>> users = new ArrayList<Map<String, String>>();

    private static ArrayList<Avion> aviones = new ArrayList<Avion>();

    private static Map<String, Integer> distancias = Stream
            .of(new Object[][] { { "Buenos Aires-Cordoba", 695 }, { "Buenos Aires-Santiago", 1400 },
                    { "Buenos Aires-Montevideo", 950 }, { "Cordoba-Montevideo", 1190 }, { "Cordoba-Santiago", 1050 },
                    { "Montevideo-Santiago", 2100 }, })
            .collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    private static Map<String, ArrayList<Viaje>> viajesContratados = new HashMap<String, ArrayList<Viaje>>();

    public static Map<String, String> getCurrentUser() {
        return Provider.currentUser;
    }

    public static void setCurrentUser(Map<String, String> currentUser) {
        Provider.currentUser = currentUser;
    }

    public static ArrayList<Map<String, String>> getUsers() {
        return Provider.users;
    }

    public static void setUsers(ArrayList<Map<String, String>> users) {
        Provider.users = users;
    }

    public static void addToUsers(Map<String, String> map) {
        Provider.users.add(map);
    }

    public static void addAvion(Avion avion) {
        aviones.add(avion);
    }

    public static ArrayList<Avion> getAviones() {
        return Provider.aviones;
    }

    public static void setAviones(ArrayList<Avion> aviones) {
        Provider.aviones = aviones;
    }

    public static Map<String, Integer> getDistancias() {
        return distancias;
    }

    public static void setViajes(Map<String, ArrayList<Viaje>> viajes) {
        Provider.viajesContratados = viajes;
    }

    public static Map<String, ArrayList<Viaje>> getViajes() {
        System.out.println(Provider.viajesContratados);
        return Provider.viajesContratados;
    }

    public static Viaje addViaje(Viaje viaje) {
        if (Provider.viajesContratados.containsKey(Provider.currentUser.get("username"))) {
            ArrayList<Viaje> viajes = Provider.viajesContratados.get(Provider.currentUser.get("username"));
            viajes.add(viaje);
            Provider.viajesContratados.put(Provider.currentUser.get("username"), viajes);
        } else {
            Provider.viajesContratados.putIfAbsent(Provider.currentUser.get("username"),
                    new ArrayList<Viaje>(Arrays.asList(viaje)));
        }
        return viaje;
    }

    public static String[][] getViajesTable() {

        if (Provider.getViajes().get(Provider.getCurrentUser().get("username")) != null
                && !Provider.getViajes().get(Provider.getCurrentUser().get("username")).isEmpty()) {
            return Provider.getViajes().get(Provider.getCurrentUser().get("username")).stream()
                    .map((Viaje value) -> value.getValuesString()).toArray(size -> new String[size][]);
        }
        return new String[0][0];
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