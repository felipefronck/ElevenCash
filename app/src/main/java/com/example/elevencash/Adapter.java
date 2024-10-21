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

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> nomes;
    List<Integer> imagens;
    List<Double> precos;
    LayoutInflater layoutInflater;

    public Adapter(List<String> nomes, List<Integer> imagens, List<Double> precos, Context context) {
        this.nomes = nomes;
        this.imagens = imagens;
        this.precos = precos;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.listagem_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return precos.size();
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
