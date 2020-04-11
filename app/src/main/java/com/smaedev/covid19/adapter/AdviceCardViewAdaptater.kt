package com.smaedev.covid19.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smaedev.covid19.R
import com.smaedev.covid19.db.Advice


class AdviceCardViewAdaptater(
    private val context: Context,
    private val listAdvices: List<Advice>
) :
    RecyclerView.Adapter<AdviceCardViewAdaptater.MyViewHolder?>() {

    private var advices = emptyList<Advice>()

    var mContext: Context = context
    private val adviceList: List<Advice> = listAdvices
    private var advice: Advice? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewTYpe: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardadvice, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Log.d(TAGpopPack,"onCreateViewHolder called.");


        advice = listAdvices[position]
        Glide.with(mContext)
            .asBitmap()
            .load(advice!!.image)
            .into(holder.image)
        holder.title.text = advice!!.title
        holder.desc.text = advice!!.description
        holder.btAdviceCard.setOnClickListener {
            advice = adviceList[position]

            //Toast.makeText(mContext, idpack ,Toast.LENGTH_LONG).show();
            //openPackDetailFragment(country.getImage(), country.getNom(), country.getPrix())
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //CircleImageView image;
        var image: ImageView = view.findViewById(R.id.imageViewAdv)
        var title: TextView = view.findViewById(R.id.tvAdviceCardTitle)
        var desc: TextView = view.findViewById(R.id.tvCardAdviceDesc)
        var btAdviceCard : Button = view.findViewById(R.id.btAdviceCard)

    }

    /*private fun openPackDetailFragment(
        image: String,
        nom: String,
        pu: Int
    ) {
        val fragPackA = FragPackA()
        val data = Bundle()
        idpack = pack.getId()
        data.putString("idProduit", idpack.toString())
        data.putString("TITRE_KEY", nom)
        data.putString("IMAGE_KEY", image)
        data.putInt("PRIX_KEY", pu)
        fragPackA.setArguments(data)
        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragContainer, fragPackA)
            .addToBackStack(null).commit()
    }*/

    companion object {
        const val TAGpopPack = "CardViewAdaptater"
        var idpack = 0
    }

    override fun getItemCount(): Int {
        return listAdvices.size
    }

    internal fun setAdvices(advices: List<Advice>) {
        this.advices = advices
        notifyDataSetChanged()
    }
}

