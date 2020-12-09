package com.example.databaseroom.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.databaseroom.models.Wine
import kotlinx.coroutines.*

@Database(entities = [Wine::class],version = 1, exportSchema =true )
abstract class WineDatabase:RoomDatabase(){
   abstract fun getWineDao():WineDao

   companion object{
       @Volatile
       private var instance:WineDatabase?=null
       fun getWineDatabase(context: Context):WineDatabase{
           return  instance?: synchronized(WineDatabase::class.java){
               val callback = object:RoomDatabase.Callback(){
                   override fun onCreate(db: SupportSQLiteDatabase) {
                       super.onCreate(db)
                       val wine1=Wine("Don Perri",12,200,false)
                       val wine2=Wine("Try me",2,100,false)
                       val wine3=Wine("Raki",14,300,true)
                       GlobalScope.launch {
                          withContext(Dispatchers.IO){
                              getWineDatabase(context).getWineDao().insertWine(wine1,wine2,wine3)
                          }
                      }

                   }

               }
               Room.databaseBuilder(context,WineDatabase::class.java,"winedatabase.db").addCallback( callback )
                   .build()
           }
       }

   }


}