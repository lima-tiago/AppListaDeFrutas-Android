package com.example.applistadefrutas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadefrutas.R
import com.example.applistadefrutas.activities.Fruta
import java.util.*

class AdapterCarrinho(val vetCarrinho: Vector<Fruta>): RecyclerView.Adapter<AdapterCarrinho.CarrinhoViewHolder>(){

    class CarrinhoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindFruta(fruta: Fruta){
            itemView.findViewById<TextView>(R.id.txtNomeFruta).text = fruta.nome
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
    }
}