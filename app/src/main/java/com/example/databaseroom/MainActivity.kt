package com.example.databaseroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.databaseroom.utils.WineFilter
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    lateinit  var radioGroup: RadioGroup
    lateinit var   filter: WineFilter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        radioGroup = findViewById<RadioGroup>(R.id.radiobutton)


       /* radioGroup.setOnCheckedChangeListener { p0, p1 ->
            p0?.let {
                when (p1) {
                    R.id.all -> {
                        android.widget.Toast.makeText(this@MainActivity, " Show All", Toast.LENGTH_SHORT).show()
                    }
                    R.id.no_alcohol -> {
                        Toast.makeText(this@MainActivity, "no alcohol", Toast.LENGTH_SHORT).show()
                    }
                    R.id.cheapest -> {
                        Toast.makeText(this@MainActivity, "chepest", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }*/
      val search=findViewById<Button>(R.id.search)
        search.setOnClickListener {
            navigateToList(this)
        }
    }

    private fun navigateToList(mainActivity: MainActivity) {
        if(::filter.isInitialized){
            var value=when(filter){
                WineFilter.ALL_WINE -> { "all" }
                WineFilter.CART -> {
                    "from cart"
                }
                WineFilter.CHEAPEST -> {
                    "cheapest"
                }
                WineFilter.NON_ALCOHOL -> {
                    "non alcohol"
                }
            }
            val intent=Intent(mainActivity,ListActivity::class.java)
                   intent.putExtra("filtervalue",value)
            startActivity(intent)
        }

    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton){
            val checked=view.isChecked
           when(view.id){
               R.id.all->{ filter = WineFilter.ALL_WINE
                   Timber.i("actual filter is :$filter ") }
               R.id.no_alcohol->{filter = WineFilter.NON_ALCOHOL
                   Timber.i("actual filter is :$filter ")
               }
               R.id.cheapest ->{filter = WineFilter.CHEAPEST
                   Timber.i("actual filter is :$filter ")
               }
               R.id.cart ->{filter = WineFilter.CART
                   Timber.i("actual filter is :$filter ")
               }
            }
        }
    }
}