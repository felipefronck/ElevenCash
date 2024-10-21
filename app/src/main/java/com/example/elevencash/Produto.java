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
    private double precoProduto;

    @ColumnInfo(name = "nome_produto")
    private String nomeProduto;

    @ColumnInfo(name = "imagem_produto")
    private int imagemProduto;

    @ColumnInfo(name = "categoria_produto")
    private int categoriaProduto; //1 = madeira; 2 = peixe; 3 = minérios

    public Produto(double precoProduto, String nomeProduto, int imagemProduto, int categoriaProduto) {
        this.idProduto = 0;
        this.quantidadeProduto = 0;
        this.precoProduto = precoProduto;
        this.nomeProduto = nomeProduto;
        this.imagemProduto = imagemProduto;
        this.categoriaProduto = categoriaProduto;
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

    public double getPrecoProduto() {
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

    public int getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(int categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }
}
