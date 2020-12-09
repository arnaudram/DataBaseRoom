package com.example.databaseroom.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.liveData
import androidx.paging.toLiveData
import com.example.databaseroom.repository.WineRepository
import kotlinx.coroutines.newSingleThreadContext
import java.util.concurrent.Executors

class ListActivityViewModel(private val repository: WineRepository) : ViewModel() {


    val allWines = liveData {
        val result = repository.getAllWine()
        emit(result)

    }
}

class ListActivityViewModelFoctory(application: Application,private val repository: WineRepository) :ViewModelProvider.AndroidViewModelFactory(application){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(ListActivityViewModel::class.java)){
           return ListActivityViewModel(repository) as T
       }
        else throw IllegalArgumentException("Wrong view model class")
    }
}