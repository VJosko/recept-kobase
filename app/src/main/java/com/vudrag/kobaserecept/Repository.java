package com.vudrag.kobaserecept;

import java.util.ArrayList;

public class Repository {

    private static Repository instance = null;

    private ArrayList<Recept> recepti = new ArrayList<>();

    public static synchronized Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public void addRecept(Recept recept){
        recepti.add(recept);
    }

    public ArrayList<Recept> getRecepte(){
        return recepti;
    }
}
