package com.example.elevencash;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Produto.class}, version = 1, exportSchema = false)
public abstract class ProdutoDatabase extends RoomDatabase {

    public abstract ProdutoDao produtoDao();
}
