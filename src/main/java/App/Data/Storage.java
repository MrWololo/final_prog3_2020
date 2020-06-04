package App.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import App.BackEnd.Avion;

public class Storage {

    public static boolean usersExist() {
        File file = new File("Users.txt");
        return file.exists();
    }
    public static boolean avionesExist() {
        File file = new File("Aviones.txt");
        return file.exists();
    }

    public static void guardarRegistro(ArrayList<Map<String, String>> data) {

        try {
            FileOutputStream f = new FileOutputStream("Users.txt", false);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(data);

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Encontrado");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void guardarAviones(ArrayList<Avion> aviones) {

        try {
            FileOutputStream f = new FileOutputStream("Aviones.txt", false);
            ObjectOutputStream o = new ObjectOutputStream(f);

            Gson gson = new Gson();
            String avionesJson = gson.toJson(aviones);

            o.writeObject(avionesJson);

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Encontrado");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Avion> fetchAviones() {

        try {
            FileInputStream f = new FileInputStream("Aviones.txt");
            ObjectInputStream o = new ObjectInputStream(f);

            Gson gson = new Gson();

            String json = (String) o.readObject();
            System.out.println(json);

            o.close();
            f.close();
            return gson.fromJson(json, new TypeToken<ArrayList<Avion>>() {
            }.getType());

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Encontrado");
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Map<?, ?>> fetchRegistro() {
        try {
            FileInputStream f = new FileInputStream("Users.txt");
            ObjectInputStream o = new ObjectInputStream(f);

            ArrayList<Map<?, ?>> parsedList = new ArrayList<>();
            Object valuesHolder = o.readObject();
            if (valuesHolder instanceof ArrayList<?>) {
                ArrayList<?> valuesList = (ArrayList<?>) valuesHolder;
                if (valuesList.size() > 0) {
                    for (Object value : valuesList) {
                        if (value instanceof Map<?, ?>) {
                            Map<?, ?> map = (Map<?, ?>) value;
                            parsedList.add(map);
                        }
                    }
                }
            }

            o.close();
            f.close();

            return parsedList;

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Encontrado");
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}