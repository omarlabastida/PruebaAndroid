package example.tarea1.mysuperherolist.Marvel.MarvelRecycler

import example.tarea1.mysuperherolist.Marvel.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIMarvelService {

    @GET
    suspend fun getSuperHeroesMarvel(@Url url: String): Response<MarvelResponse>
}