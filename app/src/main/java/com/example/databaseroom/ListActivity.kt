package com.example.databaseroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseroom.adapter.WineAdapter
import com.example.databaseroom.database.WineDatabase
import com.example.databaseroom.repository.WineRepository
import com.example.databaseroom.ui.ListActivityViewModel
import com.example.databaseroom.ui.ListActivityViewModelFoctory
import com.example.databaseroom.utils.WineFilter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val application= requireNotNull(this).application
        val dao=WineDatabase.getWineDatabase(application.applicationContext).getWineDao()
        val repository=WineRepository(dao)
        val listActivityViewModelFoctory=ListActivityViewModelFoctory(application,repository)
        val listActivityViewModel=ViewModelProvider(this,listActivityViewModelFoctory)[ListActivityViewModel::class.java]

        val recyclerView=findViewById<RecyclerView>(R.id.recycleview)
        val toolbar= findViewById<Toolbar>(R.id.toolbar)
        val receivedFilter=intent.getStringExtra("filtervalue")
        Timber.e(" received intent:$receivedFilter")


        if (receivedFilter != null) {
            toolbar.title=receivedFilter

            supportActionBar?.title=receivedFilter
            listActivityViewModel.setFilter(receivedFilter)
        }


        val adapter=WineAdapter()

        listActivityViewModel.wines.observe(this, Observer {
           lifecycleScope.launch {
               it.collectLatest {
                  adapter.submitList(it)
               }
           }
            recyclerView.adapter=adapter
        })


    }
}