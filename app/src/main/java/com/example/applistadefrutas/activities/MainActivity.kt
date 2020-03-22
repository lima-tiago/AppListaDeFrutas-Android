package com.example.applistadefrutas.activities

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadefrutas.R
import com.example.applistadefrutas.adapters.AdapterFruta
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class Fruta(nome: String, preço: Double) {
    var nome: String = ""
    var preço: Double = 0.0

    init {
        this.nome = nome
        this.preço = preço
    }
}

class MainActivity : AppCompatActivity()  {

    var Banana: Fruta =
        Fruta("Banana", 0.0)
    var Laranja: Fruta =
        Fruta("Laranja", 0.0)
    var Limão: Fruta =
        Fruta("Limão", 0.0)
    var Uva: Fruta =
        Fruta("Uva", 0.0)
    var Lichia: Fruta =
        Fruta("Lichia", 0.0)
    var Manga: Fruta =
        Fruta("Manga", 0.0)
    var Goiaba: Fruta =
        Fruta("Goiaba", 0.0)
    var Maçã: Fruta =
        Fruta("Maçã", 0.0)
    var Mamão: Fruta =
        Fruta("Mamão", 0.0)
    var Melancia: Fruta =
        Fruta("Melancia", 0.0)

    var vetFruta: Vector<Fruta> = Vector()
    var vetCarrinho: Vector<Fruta> = Vector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var relCart = findViewById<RelativeLayout>(R.id.carrinho)
        relCart.visibility = View.GONE
        addElements()
        addElements()
        configureAdapter()
    }

    private fun dpsToPixels(activity: MainActivity, dps: Int): Int {
        val r = activity.resources

        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dps.toFloat(), r.displayMetrics).toInt()
    }

    fun addElements(){
        vetFruta.addElement(Banana)
        vetFruta.addElement(Laranja)
        vetFruta.addElement(Limão)
        vetFruta.addElement(Uva)
        vetFruta.addElement(Lichia)
        vetFruta.addElement(Manga)
        vetFruta.addElement(Goiaba)
        vetFruta.addElement(Maçã)
        vetFruta.addElement(Mamão)
        vetFruta.addElement(Melancia)
    }

    fun configureAdapter(){
        recyclerFrutas.adapter =
            AdapterFruta(vetFruta, vetCarrinho, this)
        recyclerFrutas.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        recyclerFrutas.setPadding(6,6,6,6)
    }

    fun addCart(fruta: Fruta){
        vetCarrinho.addElement(fruta)
        if (vetCarrinho.count() > 0){
            var int6dp = dpsToPixels(this,6)
            var int80dp = dpsToPixels(this,80)
            recyclerFrutas.setPaddingRelative(recyclerFrutas.paddingStart,recyclerFrutas.paddingTop,recyclerFrutas.paddingEnd,int80dp)
            carrinho.visibility = View.VISIBLE
            txtCount.text = vetCarrinho.count().toString()
        }


        Toast.makeText(this,fruta.nome,Toast.LENGTH_SHORT).show()
        println("funfou")
    }

}
