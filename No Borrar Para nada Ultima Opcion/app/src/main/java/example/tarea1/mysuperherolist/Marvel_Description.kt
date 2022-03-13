package example.tarea1.mysuperherolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import example.tarea1.mysuperherolist.Marvel.MarvelRecycler.MarvelAdapter
import example.tarea1.mysuperherolist.Marvel.Result
import example.tarea1.mysuperherolist.databinding.ActivityMainBinding
import example.tarea1.mysuperherolist.databinding.ActivityMarvelDescriptionBinding

class Marvel_Description : AppCompatActivity() {
    lateinit var binding: ActivityMarvelDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMarvelDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDescription()




    }

    override fun onBackPressed() {
        super.onBackPressed()
        Intent(this,ActivityMainBinding::class.java)

    }

    private fun showDescription(){
        if (!intent.getStringExtra("SuperHeroImage").isNullOrEmpty()){
            Picasso.get().load(intent.getStringExtra("SuperHeroImage")).into(binding.imageMD)
        }else{ }
        if (!intent.getStringExtra("SuperHeroId").isNullOrEmpty()){
            binding.idMD.text= "ID:" + " " + intent.getStringExtra("SuperHeroId")
        }else{ binding.idMD.text= "Id Not Found" }
        if (!intent.getStringExtra("SuperHeroName").isNullOrEmpty()){
            binding.nameMD.text=  intent.getStringExtra(("SuperHeroName"))+"\n\n"
        }else{ binding.nameMD.text= "Name Not Found" }
        if (!intent.getStringExtra("SuperHeroDescription").isNullOrEmpty()){
            binding.descriptionMD.text= "Description:" + "\n" + intent.getStringExtra(("SuperHeroDescription"))+"\n\n\n\n\n"
        }else{ binding.descriptionMD.text= "Description Not Found" }
        if (!intent.getStringExtra("SuperHeroComics").isNullOrEmpty()){
            binding.ComicsMD.text= "Comics:" + "\n" + intent.getStringExtra("SuperHeroComics")
        }else{ binding.ComicsMD.text= "Comics Not Found" }
        if (!intent.getStringExtra("SuperHeroSeries").isNullOrEmpty()){
            binding.SeriesMD.text= "Series:" + "\n" + intent.getStringExtra("SuperHeroSeries")
        }else{ binding.SeriesMD.text= "Series Not Found" }

    }

}