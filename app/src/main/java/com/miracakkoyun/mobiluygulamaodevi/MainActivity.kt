package com.miracakkoyun.mobiluygulamaodevi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val radioYemekler=findViewById<RadioGroup>(R.id.radioAnaYiyecek)
        val checkMercimek=findViewById<CheckBox>(R.id.checkMercimek)
        val checkKizartma=findViewById<CheckBox>(R.id.checkKizartma)
        val checkSalata=findViewById<CheckBox>(R.id.checkSalata)
        val radioIcecekler=findViewById<RadioGroup>(R.id.radioIcecekler)
        val siparisBtn=findViewById<Button>(R.id.btnSiparis)
        val textSipo=findViewById<TextView>(R.id.siparisBuyuk);
        val textSiparisList=findViewById<TextView>(R.id.siparisList)
        textSipo.visibility= View.INVISIBLE
        textSiparisList.visibility=View.INVISIBLE
        siparisBtn.setOnClickListener{
            val anaYemekId=radioYemekler.checkedRadioButtonId
            val anaYemek=when (anaYemekId){
                R.id.radioImam -> "İmam Bayıldı"
                R.id.radioTava ->"Yeşilyurt Tava"
                R.id.radioKabab ->"Adana Kebab"
                else -> null
            }
            val iceceklerId=radioIcecekler.checkedRadioButtonId
            val icecek=when (iceceklerId){
                R.id.radioAyran->"Ayran"
                R.id.radioKola->"Kola"
                R.id.radioSalgam->"Şalgam"
                else->null
            }
            if(icecek!=null && anaYemek!=null){
                val ekstralar= mutableListOf<String>()
                if(checkKizartma.isChecked)
                    ekstralar.add("Kızartma")
                if(checkSalata.isChecked){
                    ekstralar.add("Salata")
                }
                if(checkMercimek.isChecked)
                    ekstralar.add("Mercimek Çorbası")
                if(ekstralar.isEmpty()){
                    ekstralar.add("Ekstralar Seçilmedi")
                }

                val siparis=SiparisDetay(
                    anaYemek = anaYemek.toString(),
                    ekstralar = ekstralar,
                    icecek = icecek.toString(),
                )


                Toast.makeText(this, "Sipariş Alındı", Toast.LENGTH_LONG).show()
                textSipo.visibility=View.VISIBLE
                textSiparisList.text="Ana Yemek: ${siparis.anaYemek}\nEkstralar:${siparis.ekstralar.joinToString()}\nİçecek: ${siparis.icecek}"
                textSiparisList.visibility=View.VISIBLE
            }else{
                Toast.makeText(this, "Lütfen Zorunlu Seçimleri Yapınız", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }





    }



}