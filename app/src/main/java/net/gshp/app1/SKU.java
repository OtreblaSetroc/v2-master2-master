package net.gshp.app1;

/**
 * Created by leo on 20/06/18.
 */

public class SKU {
    private int idSku;
    private String valor;
    private int answer;

    public int getIdSku() {
        return idSku;
    }

    public SKU setIdSku(int idSku) {
        this.idSku = idSku;
        return this;
    }

    public String getValor() {
        return valor;
    }

    public SKU setValor(String valor) {
        this.valor = valor;
        return this;
    }

    public int getAnswer() {
        return answer;
    }

    public SKU setAnswer(int answer) {
        this.answer = answer;
        return this;
    }
}
