package com.example.elevencash;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Produto implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_produto")
    private int idProduto;
    @ColumnInfo(name = "quantidade_produto")
    private int quantidadeProduto;
    @ColumnInfo(name = "preco_produto")
    private int precoProduto;
    @ColumnInfo(name = "nome_produto")
    private String nomeProduto;
    @ColumnInfo(name = "imagem_produto")
    private int imagemProduto;

    public Produto(int idProduto, int quantidadeProduto, int precoProduto, String nomeProduto, int imagemProduto) {
        this.idProduto = idProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.precoProduto = precoProduto;
        this.nomeProduto = nomeProduto;
        this.imagemProduto = imagemProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public int getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(int precoProduto) {
        this.precoProduto = precoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(int imagemProduto) {
        this.imagemProduto = imagemProduto;
    }
}
