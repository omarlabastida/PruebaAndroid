package example.tarea1.mysuperherolist.Marvel.MarvelRecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import example.tarea1.mysuperherolist.databinding.ItemDogBinding
import example.tarea1.mysuperherolist.Marvel.Result

class MarvelViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun bind(superHero: Result, itemClick: MarvelAdapter.OnMarvelClickListener){
        var imageSuper :String = superHero.thumbnail.path+"."+superHero.thumbnail.extension
        itemView.setOnClickListener{
            itemClick.onImageClick(superHero)
        }


                var nombre : String = superHero.name
        binding.name.text= nombre
        Picasso.get().load(imageSuper.replace("http", "https")).into(binding.SuperHeroMarvel)

    }
}