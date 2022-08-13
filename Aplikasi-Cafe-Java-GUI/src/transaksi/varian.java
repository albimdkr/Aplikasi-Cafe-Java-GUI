package transaksi;

public class varian {
    int no_varian, harga;
    String nama_varian;

    public varian(int no_varian, int harga, String nama_varian) {
        this.no_varian = no_varian;
        this.harga = harga;
        this.nama_varian = nama_varian;
    }

    public int getNo() {
        return no_varian;
    }

    public int getHarga() {
        return harga;
    }

    public String toString() {
        return nama_varian;
    }
    
}
