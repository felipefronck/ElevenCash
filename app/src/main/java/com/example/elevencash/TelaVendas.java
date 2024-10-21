package com.example.elevencash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TelaVendas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Produto> produtos;
    private ProdutoDatabase produtoDb;
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_vendas);

        recyclerView = findViewById(R.id.recyclerView);
        chipGroup = findViewById(R.id.chipGroup);
        produtoDb = ProdutoDatabase.getDatabase(this);

        produtos = new ArrayList<>();
        adapter = new Adapter(produtos,TelaVendas.this, produtoDb);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<Produto> fetchedProdutos = produtoDb.produtoDao().getAll();

            runOnUiThread(() -> {
                if(fetchedProdutos.isEmpty()){
                    executor.execute(() -> {
                        List<Produto> retryProdutos = produtoDb.produtoDao().getAll();

                        runOnUiThread(() -> {
                            produtos.addAll(retryProdutos);
                            adapter.notifyDataSetChanged();
                        });
                    });
                } else {
                    produtos.addAll(fetchedProdutos);
                    adapter.notifyDataSetChanged();
                }
            });
        });

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, int checkedId) {
                aplicarFiltro(checkedId);
            }
        });


    }

    private void aplicarFiltro(int checkedId) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            final List<Produto> produtosExibidos;

            if(checkedId == R.id.btnLenhas){
                produtosExibidos = produtoDb.produtoDao().getMadeiras();
            } else if (checkedId == R.id.btnPeixes) {
                produtosExibidos = produtoDb.produtoDao().getPeixes();
            } else if (checkedId == R.id.btnMinerios) {
                produtosExibidos = produtoDb.produtoDao().getMinerios();
            } else {
                produtosExibidos = new ArrayList<>();
            }

                runOnUiThread(() -> {
                    filtraLista(produtosExibidos);
                });
        });
    }

    public void filtraLista(List<Produto> listaFiltrada){
            produtos.clear();
            if(listaFiltrada != null && !listaFiltrada.isEmpty()){
                produtos.addAll(listaFiltrada);
            }

            adapter.notifyDataSetChanged();
    }

}
