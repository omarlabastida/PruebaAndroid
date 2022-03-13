package example.tarea1.mysuperherolist
//José Omar Labastida Cuadra
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import example.tarea1.mysuperherolist.Marvel.Item
import example.tarea1.mysuperherolist.Marvel.ItemXX
import example.tarea1.mysuperherolist.Marvel.MarvelRecycler.APIMarvelService
import example.tarea1.mysuperherolist.Marvel.MarvelRecycler.MarvelAdapter
import example.tarea1.mysuperherolist.Marvel.MarvelResponse
import example.tarea1.mysuperherolist.Marvel.Result
import example.tarea1.mysuperherolist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), MarvelAdapter.OnMarvelClickListener{

    lateinit var binding: ActivityMainBinding
    val URL_TOP: String = "https://i0.wp.com/3.bp.blogspot.com/-V4MJ_8Wt5pY/Ti0g5MvjTzI/AAAAAAAALBg/pU4pN_CbM1g/s1600/avengersassemblebig.jpg"
    val URL_MARVEL: String = "https://gateway.marvel.com/v1/public/"
    private lateinit var adapter: MarvelAdapter
        private val superHeroImages= mutableListOf<Result>()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            Picasso.get().load(URL_TOP).into(binding.top)
            val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                println("SI HAy internet")
                superHeroeSearch()
                initRecyclerView()
            } else {
                Toast.makeText(this@MainActivity,"SIN CONEXION A INTERNET", Toast.LENGTH_LONG).show()
                println("no hay internet")
            }
        }
    fun initRecyclerView(){
        println("RECYCLER")
        adapter = MarvelAdapter(superHeroImages,this)
        binding.rvMarvel.layoutManager =LinearLayoutManager(this)
        binding.rvMarvel.adapter = adapter
    }
        private fun getRetrofit():Retrofit{
                return Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(URL_MARVEL)
                        .build()
        }

        private fun superHeroeSearch(){
            println("1")
            CoroutineScope(Dispatchers.IO).launch {
                println("2")
                val call: Response<MarvelResponse> = getRetrofit().create(APIMarvelService::class.java).getSuperHeroesMarvel("characters?limit=100&ts=1234&apikey=e01d1473cfef5e962eda4d701d65d465&hash=1cb9e544afdb09de146f23a4baaa5901")
                println("3")
                val superHeros:MarvelResponse? = call.body()
                println("4")
                runOnUiThread {
                    println("UITHREAD")
                    if(call.isSuccessful){
                        println("CALL GOOD")
                        val imagenes:List<Result> = superHeros?.data?.results ?: emptyList()
                        superHeroImages.clear()
                        superHeroImages.addAll(imagenes)
                        adapter.notifyDataSetChanged()
                        println("llamada")
                    }
                    else{
                        println("CALL NOT GOOD")
                    }
                }

            }
        }

    override fun onImageClick(superHero:Result) {
            val intent= Intent(this, Marvel_Description::class.java)
            var imageSuper:String = superHero.thumbnail.path+"."+superHero.thumbnail.extension
            imageSuper= imageSuper.replace("http", "https")
            intent.putExtra("SuperHeroImage",imageSuper)
            intent.putExtra("SuperHeroName", superHero.name)
            intent.putExtra("SuperHeroDescription", superHero.description)
            intent.putExtra("SuperHeroId", superHero.id.toString())
            var itemList: List<Item> = superHero.comics.items
            var ComicsList: String = ""
            for (comics:Item in itemList){
                ComicsList += comics.name +"\n\n"
            }
            intent.putExtra("SuperHeroComics", ComicsList)
        var itemsList: List<ItemXX> = superHero.series.items
        var SeriesList: String = ""
        for (series:Item in itemList){
            SeriesList += series.name +"\n\n"
        }
        intent.putExtra("SuperHeroSeries", SeriesList)


            startActivity(intent)
    }


    private fun posision(posision:MarvelAdapter){
        posision.posRecycler
    }




}




