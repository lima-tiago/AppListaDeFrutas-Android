package com.example.applistadefrutas.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadefrutas.Fruta
import com.example.applistadefrutas.R
import com.example.applistadefrutas.activities.FrutaActivity
import com.example.applistadefrutas.activities.MainActivity
import kotlinx.android.synthetic.main.cell_fruta.view.*
import java.net.URL


class AdapterFruta(val vetFruta: ArrayList<Fruta>, val context: Context): RecyclerView.Adapter<AdapterFruta.FrutaViewHolder>() {

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


//            val mDrawableName = "logo_default"
//            val resID: Drawable =
//                getIdentifier(mDrawableName, "drawable", VerifyAccess.getPackageName())
//            val new_image: Int = FrutaActivity::class.java.getResource(fruta.nome). //  getResources().getDrawable(R.drawable.loginbtn)
            val image:Bitmap? = (itemView.context as MainActivity).getBitmapFromAssets(fruta.nome+".png")
            itemView.findViewById<ImageView>(R.id.imgFruta).setImageBitmap(image)
        }
    }
}
