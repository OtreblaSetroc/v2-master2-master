package net.gshp.app1;

public class AsnwerSku {
    private int id;
    private int value;
    //private int id_sku;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /*public int getId_sku() {
        return id_sku;
    }

    public void setId_sku(int id_sku) {
        this.id_sku = id_sku;
    }
*/



    public AsnwerSku(int id, int value){
        this.id=id;
        this.value=value;
        //this.id_sku=id_sku;
    }
}


