package com.example.elevencash;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Produto.class}, version = 2, exportSchema = false)
public abstract class ProdutoDatabase extends RoomDatabase {

    public abstract ProdutoDao produtoDao();

    private static volatile ProdutoDatabase instance;
    private static Context appContext;

    public static ProdutoDatabase getDatabase(final Context context){
        if (instance == null) {
            synchronized (ProdutoDatabase.class){
                if (instance == null){
                    appContext = context.getApplicationContext();
                    instance = Room.databaseBuilder(appContext,
                                    ProdutoDatabase.class, "produto_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        public void onCreate(SupportSQLiteDatabase db){
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                ProdutoDao dao = instance.produtoDao();
                List<Produto> mockProdutos = new ArrayList<>();
                mockProdutos.add(new Produto(5.00, "Madeira de Carvalho", R.drawable.oaklog, 1));
                mockProdutos.add(new Produto(5.00, "Madeira de Acácia", R.drawable.acacialog, 1));
                mockProdutos.add(new Produto(5.00, "Madeira de Carvalho Escuro", R.drawable.darkoaklog, 1));
                mockProdutos.add(new Produto(5.00, "Madeira de Eucalipto", R.drawable.birchlog, 1));
                mockProdutos.add(new Produto(5.00, "Madeira da Selva", R.drawable.junglelog, 1));

                mockProdutos.add(new Produto(5.00, "Baiacu", R.drawable.baiacu, 2));
                mockProdutos.add(new Produto(5.00, "Bacalhau", R.drawable.cod, 2));
                mockProdutos.add(new Produto(5.00, "Peixe Tropical", R.drawable.tropical, 2));
                mockProdutos.add(new Produto(5.00, "Salmão", R.drawable.salmao, 2));

                mockProdutos.add(new Produto(5.00, "Ferro", R.drawable.ironore, 3));
                mockProdutos.add(new Produto(5.00, "Ouro", R.drawable.goldore, 3));
                mockProdutos.add(new Produto(5.00, "Diamante", R.drawable.diamondore, 3));
                mockProdutos.add(new Produto(5.00, "Esmeralda", R.drawable.emeraldore, 3));
                mockProdutos.add(new Produto(5.00, "Carvão", R.drawable.coalore, 3));
                mockProdutos.add(new Produto(5.00, "Redstone", R.drawable.redstoneore, 3));
                mockProdutos.add(new Produto(5.00, "Cobre", R.drawable.copperore, 3));

                dao.insertAll(mockProdutos);
            });
        }
    };
}
