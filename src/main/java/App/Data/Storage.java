package App.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import App.BackEnd.Avion;
import App.BackEnd.Viaje;

public class Storage {
    private static PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().allowIfBaseType(Avion.class)
            .allowIfBaseType(ArrayList.class).allowIfBaseType(Map.class).allowIfBaseType(LocalDate.class)
            .allowIfBaseType(Viaje.class).build();

    private static ObjectMapper oMapper = JsonMapper.builder().activateDefaultTyping(ptv, DefaultTyping.NON_FINAL)
            .build();

    public static boolean usersExist() {
        File file = new File("Users.txt");
        return file.exists();
    }

    public static boolean avionesExist() {
        File file = new File("Aviones.txt");
        return file.exists();
    }

    public static boolean viajesExist() {
        File file = new File("Viajes.txt");
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
            /*
             * FileOutputStream f = new FileOutputStream("Aviones.txt", false);
             * ObjectOutputStream o = new ObjectOutputStream(f);
             * 
             * //Gson gson = new Gson(); //String avionesJson = gson.toJson(aviones);
             * ObjectMapper oMapper = new ObjectMapper(); String avionesJson =
             * oMapper.writeValueAsString(aviones);
             * 
             * o.writeObject(avionesJson);
             * 
             * o.close(); f.close();
             */
            oMapper.writeValue(new File("Aviones.txt"), aviones);

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Avion> fetchAviones() {
        try {
            /*
             * FileInputStream f = new FileInputStream("Aviones.txt"); ObjectInputStream o =
             * new ObjectInputStream(f);
             * 
             * Gson gson = new Gson();
             * 
             * String json = (String) o.readObject(); System.out.println(json);
             * 
             * o.close(); f.close(); return gson.fromJson(json, new
             * TypeToken<ArrayList<Avion>>() { }.getType());
             */

            // return oMapper.readValue(new File("Aviones.txt"), Avion[].class);
            /*
             * System.out.println( "aviones: " + oMapper.readTree(new File("Aviones.txt")));
             */
            // return oMapper.readTree(new File("Aviones.txt")).;
            return oMapper.readValue(new File("Aviones.txt"), new TypeReference<ArrayList<Avion>>() {
            });

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Encontrado");
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void guardarViajes(Map<String, ArrayList<Viaje>> viajes) {

        oMapper.registerModule(new JavaTimeModule());
        oMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Map<String, String> tempMap = new HashMap<>();
        try {
            for (Entry<String, ArrayList<Viaje>> viaje : viajes.entrySet()) {
                String key = viaje.getKey();
                String value = oMapper.writeValueAsString(viaje.getValue());
                String encrypted = Encryption.encrypt(value, "d4dd1ch1ll");
                tempMap.put(key, encrypted);
            }

            oMapper.writeValue(new File("Viajes.txt"), tempMap);

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Encontrado");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static Map<String, ArrayList<Viaje>> fetchViajes() {

        oMapper.registerModule(new JavaTimeModule());
        oMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            Map<String, String> tempMap = oMapper.readValue(new File("Viajes.txt"),
                    new TypeReference<Map<String, String>>() {
                    });
            Map<String, ArrayList<Viaje>> original = new HashMap<>();
            for (Entry<String, String> entry : tempMap.entrySet()) {

                String key = entry.getKey();
                String encoded = entry.getValue();
                String decoded = Encryption.decrypt(encoded, "d4dd1ch1ll");
                ArrayList<Viaje> value = oMapper.readValue(decoded, new TypeReference<ArrayList<Viaje>>() {
                });
                original.put(key, value);
            }
            return original;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Encontrado");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
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