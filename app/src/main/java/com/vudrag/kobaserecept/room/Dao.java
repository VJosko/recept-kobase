package com.vudrag.kobaserecept.room;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vudrag.kobaserecept.classes.Recept;
import com.vudrag.kobaserecept.classes.ReceptInfo;
import com.vudrag.kobaserecept.classes.Sastojak;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    long insertInfo(ReceptInfo receptInfo);

    @Insert
    void insertSastojak(Sastojak sastojak);

    @Transaction
    @Query("SELECT * FROM info_table")
    LiveData<List<Recept>> getRecepteList();

    @Query("SELECT * FROM sastojak_table")
    List<Sastojak> getSastojak();

}
