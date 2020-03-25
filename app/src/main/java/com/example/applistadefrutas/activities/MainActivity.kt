package com.example.applistadefrutas.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadefrutas.Fruta
import com.example.applistadefrutas.R
import com.example.applistadefrutas.adapters.AdapterFruta
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var preco = 1.0
    var Banana: Fruta =
        Fruta("Banana", preco)
    var Laranja: Fruta =
        Fruta("Laranja", preco)
    var Limão: Fruta =
        Fruta("Limão", preco)
    var Uva: Fruta =
        Fruta("Uva", preco)
    var Lichia: Fruta =
        Fruta("Lichia", preco)
    var Manga: Fruta =
        Fruta("Manga", preco)
    var Goiaba: Fruta =
        Fruta("Goiaba", preco)
    var Maçã: Fruta =
        Fruta("Maçã", preco)
    var Mamão: Fruta =
        Fruta("Mamão", preco)
    var Melancia: Fruta =
        Fruta("Melancia", preco)

    var vetFruta: ArrayList<Fruta> = ArrayList()
    var vetCarrinho: ArrayList<Fruta> = ArrayList()

    val REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnVerCarrinho.setOnClickListener {
            val i = Intent(this, CarrinhoActivity::class.java)

            val bundle = Bundle()
            bundle.putSerializable("carrinho", vetCarrinho)
            i.putExtras(bundle)

            startActivityForResult(i,REQUEST_CODE)
        }
        addElements()
        addElements()

        configureAdapter()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_FIRST_USER) {
                if (data != null) {
                    val frutasRemovidas :ArrayList<Fruta> = data.getSerializableExtra("frutasRemovidas") as ArrayList<Fruta>

                    updateCarrinho(frutasRemovidas)
                }
            }
        }
    }

    private fun updateCarrinho(frutasRemovidas: ArrayList<Fruta>) {
        frutasRemovidas.forEach {
            vetCarrinho.remove(it)
        }
        txtCount.text = vetCarrinho.count().toString()
    }

    private fun dpsToPixels(activity: MainActivity, dps: Int): Int {
        val r = activity.resources

        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dps.toFloat(), r.displayMetrics
        ).toInt()
    }

    fun addElements() {
        vetFruta.add(Banana)
        vetFruta.add(Laranja)
        vetFruta.add(Limão)
        vetFruta.add(Uva)
        vetFruta.add(Lichia)
        vetFruta.add(Manga)
        vetFruta.add(Goiaba)
        vetFruta.add(Maçã)
        vetFruta.add(Mamão)
        vetFruta.add(Melancia)
    }

    fun configureAdapter() {
        recyclerFrutas.adapter = AdapterFruta(vetFruta, this)
        recyclerFrutas.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerFrutas.setPadding(6, 6, 6, 6)
    }

    fun setPaddingPopUp() {
        recyclerFrutas.setPaddingRelative(
            recyclerFrutas.paddingStart,
            recyclerFrutas.paddingTop,
            recyclerFrutas.paddingEnd,
            dpsToPixels(this, 80)
        )
    }

    fun addCart(fruta: Fruta) {
        vetCarrinho.add(fruta)
        showPopUpCarrinho()
        Toast.makeText(this, fruta.nome + " adicionada ao carrinho", Toast.LENGTH_SHORT).show()
    }

    fun showPopUpCarrinho() {
        if (vetCarrinho.count() > 0) {
            setPaddingPopUp()
            carrinho.visibility = View.VISIBLE
            txtCount.text = vetCarrinho.count().toString()
        }
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

