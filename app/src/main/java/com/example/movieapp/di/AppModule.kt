package com.example.movieapp.di

import com.example.movieapp.api.ApiService
import com.example.movieapp.helper.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
// neden Module kullandık ?
    //Hilt her zaman contructor  tarafında enject edilmez

/*//3. party sınıfları direk enjecte edemedigimiz durumlarda module kullanırız
* Her modulun hangi android sınıfında kullanılacaganı belirtmek içinse
*
* @InstallIn anatasyonunu kullanırız
*
* Moduller içirisinde gereken bagımlılıları saglanması için @Provides
* anatasyonu kullanılır
*
* Genelde network işlemleri için module yazabiliriz
*
* Projemizde ihtiyac halinde gerekli fildlarda bu instaceleri hilt ile inject yapabilirsin
*
*
* */


    /*
    * @Provides annotasyonu modullerde nesneleri bağımlılık
    * olarak tanımlamak için oluşturduğumuz methodların üzerinde kullanılır.*/
    @Provides
    fun provideBaseUrl()=Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetroiftInstance(BASE_URL:String) :ApiService=
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


