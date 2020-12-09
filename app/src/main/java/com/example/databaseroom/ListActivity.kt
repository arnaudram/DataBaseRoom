package com.example.databaseroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseroom.adapter.WineAdapter
import com.example.databaseroom.database.WineDatabase
import com.example.databaseroom.repository.WineRepository
import com.example.databaseroom.ui.ListActivityViewModel
import com.example.databaseroom.ui.ListActivityViewModelFoctory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val application= requireNotNull(this).application
        val dao=WineDatabase.getWineDatabase(application.applicationContext).getWineDao()
        val repository=WineRepository(dao)
        val listActivityViewModelFoctory=ListActivityViewModelFoctory(application,repository)
        val listActivityViewModel=ViewModelProvider(this,listActivityViewModelFoctory)[ListActivityViewModel::class.java]

        val recyclerView=findViewById<RecyclerView>(R.id.recycleview)

        val adapter=WineAdapter()

        listActivityViewModel.allWines.observe(this, Observer {
           lifecycleScope.launch {
               it.collectLatest {
                  adapter.submitList(it)
               }
           }
            recyclerView.adapter=adapter
        })


    }
}