package com.example.databaseroom.ui

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.toLiveData
import com.example.databaseroom.repository.WineRepository
import kotlinx.coroutines.newSingleThreadContext
import timber.log.Timber
import java.util.concurrent.Executors

class ListActivityViewModel(private val repository: WineRepository) : ViewModel() {

    private val _currentFilter=MutableLiveData<String>()
    fun setFilter(receivedFilter: String) {
       _currentFilter.value=receivedFilter
    }

    val wines =_currentFilter.switchMap {
        liveData {
        when(it){
            "from cart"->{
                val cartWine=repository.getWineFromCart()
                emit(cartWine)
                Timber.d("current filter is: from cart")
            }
            "cheapest"->{
                val cheapestWine=repository.getCheapestWine()
                emit(cheapestWine)
                Timber.d("current filter is: cheapest")
            }
            "non alcohol"->{
                val nonAlcoholWine=repository.getNonAlcohol()
                emit(nonAlcoholWine)
                Timber.d("current filter is: non alcohol")
            }
            else->{

                    val result = repository.getAllWine()
                    emit(result)
                Timber.d("current filter is: all wine")
            }

        }
        }
    }

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