package com.example.recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.models.Album
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumsAdapter(private val albums: List<Album>) :RecyclerView.Adapter<AlbumsAdapter.AlbumsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsHolder {
        //Cada ViewHolder indica como se va a ir mostrando cada ítem, podemos tener varios para
        //mostrar cada uno de manera diferente
        //Recibe por parámetro la vista que lo va a representar de forma visual, por este motivo, si
        //queremos mostrar cosas de manera distinta, podemos tener varios ViewHolders que esperen
        //distintas vistas

        //Acá cargamos una vista y la devolvemos en un AlbumsHolder, pero podríamos cargar otro
        //tipo de item_album, un item_album_special por ejemplo, en un nuevo AlbumsSpecialHolder
        //que quizas poner un color distinto para distinguirlo
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumsHolder(view)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumsHolder, position: Int) {
        //El holder es uno de los AlbumsHolder que retornamos en el método onCreateViewHolder, así
        //que tiene una view asociada
        holder.itemView.tv_album_title.text = albums[position].title
    }

    class AlbumsHolder(view: View) : RecyclerView.ViewHolder(view)

}