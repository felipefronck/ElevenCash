package com.example.elevencash;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProdutoDao {

    @Query("SELECT * FROM produto")
    List<Produto> getAll();

    @Insert
    void insertAll(List<Produto> produtos);

    @Delete
    void delete(Produto produto);

}
