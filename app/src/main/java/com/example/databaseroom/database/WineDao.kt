package com.example.databaseroom.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.databaseroom.models.Wine
import kotlinx.coroutines.flow.Flow


@Dao
interface WineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWine( vararg wine: Wine)
    @Query("SELECT * FROM wine WHERE price=:price")
    fun findByPrice(price:Long): DataSource.Factory<Int,Wine>

    @Query("SELECT * FROM wine WHERE price <200")
    fun findCheapestWine(): Flow<List<Wine>>

    @Query("SELECT * FROM wine ORDER BY name ASC")
    fun getAllWine():Flow<List<Wine>>

    @Query("SELECT name FROM wine ORDER BY name ASC")
     fun getAllWineName(): Flow<List<String>>
    @Query("SELECT * FROM wine WHERE isAddToCart=:isAddToCart ORDER BY name ASC")
     fun getWineFromCart(isAddToCart:Boolean):Flow<List<Wine>>

    @Query("SELECT * FROM wine  WHERE alc_level<5 ORDER BY name ASC")
     fun getNonAlcohol():Flow<List<Wine>>
}