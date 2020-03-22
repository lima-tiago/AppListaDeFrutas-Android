package com.example.applistadefrutas.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import com.example.applistadefrutas.R
import com.example.applistadefrutas.R.id.txtNomeFrutaGrande
import kotlinx.android.synthetic.main.activity_fruta.*

class FrutaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruta)

        getIncommingIntent()
    }

    private fun getIncommingIntent() {
        if ( this.intent.hasExtra("nome_fruta")){
            val nomeFruta:String = this.intent.getStringExtra("nome_fruta").toString()

            setNomeFruta(nomeFruta)
        }
    }

    internal fun setNomeFruta(nome: String) {
        val txtnome = findViewById<TextView>(R.id.txtNomeFrutaGrande)
        txtnome.text = nome
    }

}
