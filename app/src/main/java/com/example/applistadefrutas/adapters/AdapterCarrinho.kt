package com.example.applistadefrutas.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadefrutas.ItemCarrinho
import com.example.applistadefrutas.R
import com.example.applistadefrutas.activities.CarrinhoActivity
import com.example.applistadefrutas.activities.MainActivity
import kotlinx.android.synthetic.main.cell_fruta.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterCarrinho(val vetCarrinho: ArrayList<ItemCarrinho>, val context: Context): RecyclerView.Adapter<AdapterCarrinho.CarrinhoViewHolder>(){

    class CarrinhoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindFruta(item: ItemCarrinho){
            val precoFormatado =  (itemView.context as CarrinhoActivity).currencyFormatString(item.qtd * item.fruta.preço)
            itemView.findViewById<TextView>(R.id.txtNomeFruta).text = item.fruta.nome
            itemView.findViewById<TextView>(R.id.txtQtdFruta).text = item.qtd.toString()
            itemView.findViewById<TextView>(R.id.txtPrecoFruta).text = precoFormatado
            itemView.findViewById<Button>(R.id.btnAddCart).visibility = View.GONE
            itemView.findViewById<Button>(R.id.btnRemoveCart).visibility = View.VISIBLE
            itemView.findViewById<androidx.cardview.widget.CardView>(R.id.cardQtd).visibility = View.VISIBLE
            itemView.findViewById<androidx.cardview.widget.CardView>(R.id.cardPreco).visibility = View.VISIBLE

            val image: Bitmap? = (itemView.context as CarrinhoActivity).getBitmapFromAssets(item.fruta.nome+".png")
            itemView.findViewById<ImageView>(R.id.imgFruta).setImageBitmap(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_fruta,parent,false)
        return CarrinhoViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return vetCarrinho.count()
    }

    override fun onBindViewHolder(holder: CarrinhoViewHolder, position: Int) {
        holder.bindFruta(vetCarrinho[position])

        holder.itemView.btnRemoveCart.setOnClickListener {
            (context as CarrinhoActivity).removeCart(vetCarrinho[position].fruta)
        }
    }
}