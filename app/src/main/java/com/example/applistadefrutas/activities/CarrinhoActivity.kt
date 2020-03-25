package com.example.applistadefrutas.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadefrutas.Fruta
import com.example.applistadefrutas.ItemCarrinho
import com.example.applistadefrutas.R
import com.example.applistadefrutas.adapters.AdapterCarrinho
import kotlinx.android.synthetic.main.activity_carrinho.*
import java.io.IOException
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class CarrinhoActivity : AppCompatActivity() {

    var vetCarrinho: ArrayList<Fruta> = ArrayList()
    var frutasAgrupadas: ArrayList<ItemCarrinho> = ArrayList()
    var frutasRemovidas: ArrayList<Fruta> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)

        btnVoltarAsCompras.setOnClickListener {
            val intent = Intent()
            val bundle = Bundle()
            bundle.putSerializable("frutasRemovidas", frutasRemovidas)
            intent.putExtras(bundle)


            setResult(Activity.RESULT_FIRST_USER, intent)
            finish()
        }
        println(this.intent.hasExtra("carrinho"))
        val bundle = intent.extras

        if (bundle != null) {
            val vetCart:ArrayList<Fruta> = bundle.getSerializable("carrinho") as ArrayList<Fruta>

            setCarrinho(vetCart)
        }

        findDuplicate()
        configureAdapter()
        showTotal()

    }

    override fun onBackPressed() {
        Toast.makeText(this, "Para voltar clique em \"voltar as comprar\"", Toast.LENGTH_SHORT)
            .show()
    }

    fun findDuplicate() {
        frutasAgrupadas.clear()
        vetCarrinho.groupingBy { it }.eachCount().filter { it.value > 0 }.forEach {
            frutasAgrupadas.add(ItemCarrinho(Fruta(it.key.nome,it.key.preço), it.value))
        }
    }

    fun configureAdapter() {
        recyclerCarrinho.adapter = AdapterCarrinho(frutasAgrupadas, this)
        recyclerCarrinho.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerCarrinho.setPadding(6, 6, 6, 6)
    }

    internal fun setCarrinho(vetCarrinho: ArrayList<Fruta>) {
        this.vetCarrinho = vetCarrinho
    }

    fun removeCart(fruta: Fruta) {
        frutasRemovidas.add(fruta)
        vetCarrinho.remove(fruta)
        showTotal()

        Toast.makeText(this, fruta.nome + " removida do carrinho", Toast.LENGTH_SHORT).show()

        //fazer funcao update recycler
        findDuplicate()
        configureAdapter()
    }

    fun currencyFormatString(valor: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("pt","BR")).format(valor).toString()
    }

    fun showTotal() {
        cardTxtTotal.visibility = View.VISIBLE
        txtTotal.text = getTotalCarrinho()
    }

    fun getTotalCarrinho(): String{
        var total =  0.0
        vetCarrinho.forEach { total += it.preço }
        return currencyFormatString(total)
    }

    fun getBitmapFromAssets(fileName: String): Bitmap? {
        return try {
            BitmapFactory.decodeStream(assets.open(fileName))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
