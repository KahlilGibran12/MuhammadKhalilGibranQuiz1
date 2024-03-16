package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;
import java.util.Locale;

public class HasilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        String name = getIntent().getStringExtra("Name");
        String membership = getIntent().getStringExtra("Membership");
        String itemCode = getIntent().getStringExtra("ItemCode");
        String namaBarang = getIntent().getStringExtra("NamaBarang");
        double hargaBarang = getIntent().getDoubleExtra("HargaBarang", 0);
        double totalHarga = getIntent().getDoubleExtra("TotalHarga", 0);
        double discountHarga = getIntent().getDoubleExtra("DiscountHarga", 0);
        double discountMember = getIntent().getDoubleExtra("DiscountMember", 0);
        double jumlahBayar = getIntent().getDoubleExtra("JumlahBayar", 0);

        // Mendapatkan format mata uang Rupiah
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        // Mengonversi nilai ke format mata uang Rupiah
        String hargaBarangStr = formatRupiah.format(hargaBarang);
        String totalHargaStr = formatRupiah.format(totalHarga);
        String discountHargaStr = formatRupiah.format(discountHarga);
        String discountMemberStr = formatRupiah.format(discountMember);
        String jumlahBayarStr = formatRupiah.format(jumlahBayar);

        // Menampilkan data ke TextView
        TextView tvName = findViewById(R.id.tvName);
        tvName.setText("Selamat Datang " + name);

        TextView tvMemberType = findViewById(R.id.tvMemberType);
        tvMemberType.setText("Tipe Member : " + membership);

        TextView tvItemCode = findViewById(R.id.tvItemCode);
        tvItemCode.setText("Kode Barang : " + itemCode);

        TextView tvItemName = findViewById(R.id.tvItemName);
        tvItemName.setText("Nama Barang : " + namaBarang);

        TextView tvItemPrice = findViewById(R.id.tvItemPrice);
        tvItemPrice.setText("Harga Barang : " + hargaBarangStr);

        TextView tvTotalHarga = findViewById(R.id.tvTotalHarga);
        tvTotalHarga.setText("Total Harga : " + totalHargaStr);

        TextView tvDiscount = findViewById(R.id.tvDiscount);
        tvDiscount.setText("Discount Harga : " + discountHargaStr);

        TextView tvMemberDiscount = findViewById(R.id.tvMemberDiscount);
        tvMemberDiscount.setText("Discount Member : " + discountMemberStr);

        TextView tvJumlahBayar = findViewById(R.id.tvJumlahBayar);
        tvJumlahBayar.setText("Jumlah Bayar : " + jumlahBayarStr);

        // Mengakses tombol Share
        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTransactionInfo(name, membership, itemCode, namaBarang, hargaBarangStr,
                        totalHargaStr, discountHargaStr, discountMemberStr, jumlahBayarStr);
            }
        });
    }

    // Method untuk berbagi informasi transaksi
    private void shareTransactionInfo(String name, String membership, String itemCode,
                                      String namaBarang, String hargaBarang, String totalHarga,
                                      String discountHarga, String discountMember, String jumlahBayar) {
        String message = "Detail Transaksi:\n" +
                "Nama: " + name + "\n" +
                "Tipe Member: " + membership + "\n" +
                "Kode Barang: " + itemCode + "\n" +
                "Nama Barang: " + namaBarang + "\n" +
                "Harga Barang: " + hargaBarang + "\n" +
                "Total Harga: " + totalHarga + "\n" +
                "Discount Harga: " + discountHarga + "\n" +
                "Discount Member: " + discountMember + "\n" +
                "Jumlah Bayar: " + jumlahBayar;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "Bagikan melalui"));
    }
}

