package com.example.elevencash;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private int quantidadeDeProdutos;
    private  double valorTotal;

    public Carrinho() {
        this.quantidadeDeProdutos = 0;
        this.valorTotal = 0;
    }

    public int getQuantidadeDeProdutos() {
        return quantidadeDeProdutos;
    }

    public void setQuantidadeDeProdutos(int quantidadeDeProdutos) {
        this.quantidadeDeProdutos = quantidadeDeProdutos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void adicionarProduto(Produto produto) {
        setQuantidadeDeProdutos(getQuantidadeDeProdutos() + 1);
    }

    public void removerProduto(Produto produto) {
            setQuantidadeDeProdutos(getQuantidadeDeProdutos() - 1);
    }
}

