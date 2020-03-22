package com.example.applistadefrutas.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadefrutas.R
import com.example.applistadefrutas.activities.Fruta
import com.example.applistadefrutas.activities.FrutaActivity
import com.example.applistadefrutas.activities.MainActivity
import kotlinx.android.synthetic.main.cell_fruta.view.*
import java.util.*


class AdapterFruta(
    val vetFruta: Vector<Fruta>,
    val vetCarrinho: Vector<Fruta>,
    val context: Context
): RecyclerView.Adapter<AdapterFruta.FrutaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrutaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_fruta,parent,false)
        return FrutaViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return vetFruta.count()//To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: FrutaViewHolder, position: Int) {
        holder.bindFruta(vetFruta[position])

        holder.itemView.btnAddCart.setOnClickListener {
           // vetCarrinho.addElement(vetFruta[position])
//            Toast.makeText(holder.itemView.context,vetFruta[position].nome + " adicionada ao carrinho",Toast.LENGTH_SHORT).show()
            println(vetCarrinho.count())
            // se carrinho.count > 1 mostrar layout de vizualizar carrinho
            (context as MainActivity).addCart(vetFruta[position])
        }

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context,FrutaActivity::class.java)

            i.putExtra("nome_fruta",vetFruta[position].nome)
            holder.itemView.context.startActivity(i)
        }
    }

    class FrutaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindFruta(fruta: Fruta){
            itemView.findViewById<TextView>(R.id.txtNomeFruta).text = fruta.nome
        }
    }
}
