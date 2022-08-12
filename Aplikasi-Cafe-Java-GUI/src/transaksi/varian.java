package transaksi;

public class varian {
    int no_varian, harga;
    String varian;

    public varian(int no_varian, int harga, String varian) {
        this.no_varian = no_varian;
        this.harga = harga;
        this.varian = varian;
    }

    public int getNo() {
        return no_varian;
    }

    public int getHarga() {
        return harga;
    }

    public String getVarian() {
        return varian;
    }
    
}
