package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.TvShowItem
import com.example.movieapp.model.TvShowResponse
import com.example.movieapp.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel
@Inject constructor(private val repository: TvShowRepository):ViewModel()
{
   private val _response = MutableLiveData<List<TvShowItem>>()
    val responseTvShow:LiveData<List<TvShowItem>>
    get() = _response


    init {
        getAllTvShow()
    }

    private fun getAllTvShow() {
       viewModelScope.launch {
           repository.getTvShows().let {reponse->
               if (reponse.isSuccessful)
               {
                   _response.postValue(reponse.body())
               }
               else
               {
                   Log.d("Error","GetAlltvshow error ${reponse.code()}")
               }
           }
       }
    }
}