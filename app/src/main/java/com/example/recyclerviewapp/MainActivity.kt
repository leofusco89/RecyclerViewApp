package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewapp.models.Album
import com.example.recyclerviewapp.service.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        service.getAlbums().enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                pb.visibility = View.GONE
            }

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                pb.visibility = View.GONE
                if (response.isSuccessful){
                    initUi(response.body()!!)
                }
            }
        })
    }

    private fun initUi(body: List<Album>) {
        //El RecyclerView se verá como un LinearLayout (si queremos grilla, usamos el Grid y
        //modificamos el adapter acorde)
        val linearLayoutManager = LinearLayoutManager(this)
        rv_albums.layoutManager = linearLayoutManager

        //Indicamos el contenido a mostrar y el adapter para saber como mostrarlo
        val adapter = AlbumsAdapter(body)
        rv_albums.adapter = adapter

        //Agregamos línea divisoria entre ítems
        val dividerItemDecoration = DividerItemDecoration(rv_albums.context, linearLayoutManager.orientation)
        rv_albums.addItemDecoration(dividerItemDecoration)
    }
}
