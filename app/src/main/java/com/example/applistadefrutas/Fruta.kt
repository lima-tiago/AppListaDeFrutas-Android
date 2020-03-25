package com.example.applistadefrutas

import java.io.Serializable

data class Fruta(val nome:String,
                 val preço:Double):Serializable {
}

data class ItemCarrinho(val fruta: Fruta,
                        val qtd: Int):Serializable{
}