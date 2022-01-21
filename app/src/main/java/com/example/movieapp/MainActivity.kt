package com.example.movieapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.TvShowAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint


/*
* Android bileşenlerine bağımlılıkları
*  enjekte edebilmek için
*  @AndroidEntryPoint annotasyonunu kullanırız.*/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel:TvShowViewModel by viewModels()
    private lateinit var tvShowAdapter : TvShowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupRv()
    }

    private fun setupRv() {
       tvShowAdapter= TvShowAdapter()
        binding.recylerView.apply {
            adapter=tvShowAdapter
            layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)

            setHasFixedSize(true)
        }
        binding.rvEpisodes.apply {
            adapter=tvShowAdapter
            layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)

            setHasFixedSize(true)
        }




        viewModel.responseTvShow.observe(this,{listTvShow->
            tvShowAdapter.tvShow=listTvShow

        })


    }
}