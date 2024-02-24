package com.example.sozlukuygulamasideneme

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class KelimelerAdapter(var mContex: Context,var KelimelerListesi:List<Kelimeler>) :
    RecyclerView.Adapter<KelimelerAdapter.cardTasarimAdapter>() {

    inner class cardTasarimAdapter(gorsel:View) : RecyclerView.ViewHolder(gorsel){

        var CardView:CardView
        var textViewIngilizce : TextView
        var textViewTurkce : TextView

        init {
            CardView = gorsel.findViewById(R.id.CardView)
            textViewIngilizce = gorsel.findViewById(R.id.textViewIngilizce)
            textViewTurkce = gorsel.findViewById(R.id.textViewTurkce)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardTasarimAdapter {
        val tasarim = LayoutInflater.from(mContex).inflate(R.layout.card_tasarim,parent,false)
        return cardTasarimAdapter(tasarim)
    }

    override fun getItemCount(): Int {
        return KelimelerListesi.size
    }

    override fun onBindViewHolder(holder: cardTasarimAdapter, position: Int) {
        val kelime = KelimelerListesi.get(position)

        holder.textViewIngilizce.text=kelime.ingilizce
        holder.textViewTurkce.text=kelime.turkce

        holder.CardView.setOnClickListener {

            val intent = Intent(mContex,DetayActivity::class.java)

            intent.putExtra("nesne",kelime)

            mContex.startActivity(intent)

        }

    }


}