package com.example.databaseroom.repository

import android.content.Context
import androidx.paging.DataSource
import androidx.paging.PagingSource
import com.example.databaseroom.database.WineDao
import com.example.databaseroom.database.WineDatabase
import com.example.databaseroom.models.Wine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class WineRepository(private val wineDao: WineDao) {
    fun getAllWine(): Flow<List<Wine>> {
        return wineDao.getAllWine().distinctUntilChanged()
    }

    companion object{
        private val instance:WineRepository?=null
        fun getInstance(context:Context): WineRepository {
            
            return instance?: synchronized(WineRepository::class.java){
                val dao= WineDatabase.getWineDatabase(context).getWineDao()
              WineRepository(dao)
            }
        }
    }
}