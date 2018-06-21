package net.gshp.app1;

/**
 * Created by leo on 20/06/18.
 */

public class SKU {
    private int idSku;
    private int valor;
    private int idforeign;

    public SKU(int idSku, int valor, int idforeign) {
        this.idSku = idSku;
        this.valor = valor;
        this.idforeign = idforeign;
    }

    public int getIdSku() {
        return idSku;
    }

    public SKU setIdSku(int idSku) {
        this.idSku = idSku;
        return this;
    }

    public int getValor() {
        return valor;
    }

    public SKU setValor(int valor) {
        this.valor = valor;
        return this;
    }

    public int getIdforeign() {
        return idforeign;
    }

    public SKU setIdforeign(int idforeign) {
        this.idforeign = idforeign;
        return this;
    }
}
