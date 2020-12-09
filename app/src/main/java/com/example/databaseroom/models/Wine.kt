package com.example.databaseroom.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wine")
 data class Wine(
        @PrimaryKey
         val name:String,
         val alc_level:Int,
         val price:Long,
         val isAddToCart:Boolean
 )
