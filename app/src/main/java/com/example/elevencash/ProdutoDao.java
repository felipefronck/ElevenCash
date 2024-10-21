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

    @Query("SELECT * FROM produto WHERE categoria_produto = 1")
    List<Produto> getMadeiras();

    @Query("SELECT * FROM produto WHERE categoria_produto = 2")
    List<Produto> getPeixes();

    @Query("SELECT * FROM produto WHERE categoria_produto = 3")
    List<Produto> getMinerios();
}
    //1 = madeira; 2 = peixe; 3 = minérios
