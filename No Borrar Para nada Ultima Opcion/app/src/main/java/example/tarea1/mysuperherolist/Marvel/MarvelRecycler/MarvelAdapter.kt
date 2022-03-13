package example.tarea1.mysuperherolist.Marvel.MarvelRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import example.tarea1.mysuperherolist.Marvel.Result
import example.tarea1.mysuperherolist.R

class MarvelAdapter(var superHeros: List<Result>, private val itemClickListener:OnMarvelClickListener): RecyclerView.Adapter<MarvelViewHolder>() {
    var posRecycler = 0
    interface OnMarvelClickListener{
        fun onImageClick(superHero:Result)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(layoutInflater.inflate(R.layout.item_dog,parent,false))
    }

    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        println(position)
        posRecycler= position
        val item: Result = superHeros[position]
        val itemClick:OnMarvelClickListener = itemClickListener
        holder.bind(item, itemClick)
    }

    override fun getItemCount(): Int {
        return superHeros.size;
    }




}