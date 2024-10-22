package com.example.elevencash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Produto> produtos;
    private LayoutInflater layoutInflater;
    private ProdutoDatabase produtoDb;
    private Carrinho carrinho;

    public Adapter(ArrayList<Produto> produtos, Context context, ProdutoDatabase produtoDb, Carrinho carrinho) {
        this.produtos = (produtos != null) ? produtos : new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
        this.produtoDb = produtoDb;
        this.carrinho = carrinho;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.listagem_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.nomeProduto.setText(produto.getNomeProduto());
        holder.imgProduto.setImageResource(produto.getImagemProduto());
        holder.precoProduto.setText("$" + String.valueOf(produto.getPrecoProduto()));

        holder.btnAddProduto.setOnClickListener(view -> {
            carrinho.adicionarProduto(produto);
            holder.qntdProduto.setText(String.valueOf(carrinho.getQuantidadeDeProdutos()));
        });

        holder.btnRemoveProduto.setOnClickListener(view -> {
            carrinho.removerProduto(produto);
            holder.qntdProduto.setText(String.valueOf(carrinho.getQuantidadeDeProdutos()));
        });
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomeProduto, precoProduto, qntdProduto;
        ImageView imgProduto;
        ImageButton btnRemoveProduto, btnAddProduto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            precoProduto = itemView.findViewById(R.id.precoProduto);
            qntdProduto = itemView.findViewById(R.id.qntdProduto);
            imgProduto = itemView.findViewById(R.id.imgProduto);
            btnRemoveProduto = itemView.findViewById(R.id.btnRemoveProduto);
            btnAddProduto = itemView.findViewById(R.id.btnAddProduto);
        }
    }
}
