package com.example.elevencash;

import static android.app.PendingIntent.getActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TelaVendas extends AppCompatActivity {

    private Carrinho carrinho;

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Produto> produtos;
    private ProdutoDatabase produtoDb;
    private ChipGroup chipGroup;
    private ImageButton btnVoltar, btnLixeira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_vendas);

        btnLixeira = findViewById(R.id.btnLixeira);
        btnVoltar = findViewById(R.id.btnVoltar);
        recyclerView = findViewById(R.id.recyclerView);
        chipGroup = findViewById(R.id.chipGroup);
        produtoDb = ProdutoDatabase.getDatabase(this);

        carrinho = new Carrinho();

        produtos = new ArrayList<>();
        adapter = new Adapter(produtos,TelaVendas.this, produtoDb, carrinho);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<Produto> fetchedProdutos = produtoDb.produtoDao().getMadeiras();

            runOnUiThread(() -> {
                if(fetchedProdutos.isEmpty()){
                    executor.execute(() -> {
                        List<Produto> retryProdutos = produtoDb.produtoDao().getMadeiras();

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

        btnVoltar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Ops! Parece que você tem itens no carrinho. Deseja fechar a venda mesmo assim?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent retornar = new Intent(TelaVendas.this, MainActivity.class);
                            startActivity(retornar);
                            finish();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }); //falta tratar para apenas se o carrinho estiver !vazio

        btnLixeira.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Tem certeza que deseja limpar o carrinho?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }); //falta tratar exclusão de carrinho

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
                for (Produto produto : listaFiltrada) {
                    Log.d("Filtro", "Produto adicionado: " + produto.getNomeProduto());
                    produtos.add(produto);
                }
            }

            adapter.notifyDataSetChanged();
    }

}
