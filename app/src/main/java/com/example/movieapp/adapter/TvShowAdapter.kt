package com.example.movieapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.DetailActivity
import com.example.movieapp.databinding.TvShowLayoutAdapterBinding
import com.example.movieapp.model.TvShowItem

class TvShowAdapter(val context: Context) : RecyclerView.Adapter<TvShowAdapter.MyViewHolder>() {

   inner  class MyViewHolder(val binding: TvShowLayoutAdapterBinding):RecyclerView.ViewHolder(binding.root) {


   }

    private val diffCallback =object : DiffUtil.ItemCallback<TvShowItem>()
{
    override fun areItemsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
        return oldItem==newItem
    }

}

    private val differ =AsyncListDiffer(this,diffCallback)
    var tvShow :List<TvShowItem>
    get() = differ.currentList
    set(value)
        {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder(TvShowLayoutAdapterBinding.inflate(
          LayoutInflater.from(parent.context),parent,false
      ))
    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTvShow =tvShow[position]
        holder.binding.apply {
            textView.text=currentTvShow.name
            imageView.load(currentTvShow.image.original) {
                crossfade(true)
                crossfade(1000)

            }

            holder.itemView.setOnClickListener {
                Log.e("Tiklandi","${currentTvShow.name}")
                val intent =Intent(context,DetailActivity::class.java)
                val data=currentTvShow.summary
                var pattern ="<(???[^???]*???|'[^???]*???|[^'???>])*>".toRegex()

                val result=data.replace(pattern,"")
                intent.putExtra("name","$result")
                  context.startActivity(intent)

            }

        }



    }



    override fun getItemCount(): Int =tvShow.size



}
