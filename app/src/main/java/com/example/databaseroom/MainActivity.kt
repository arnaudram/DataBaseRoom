package com.example.databaseroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup=findViewById<RadioGroup>(R.id.radiobutton)


        radioGroup.setOnCheckedChangeListener { p0, p1 ->
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
        }
      val search=findViewById<Button>(R.id.search)
        search.setOnClickListener {
            navigateToList(this)
        }
    }

    private fun navigateToList(mainActivity: MainActivity) {
        startActivity(Intent(mainActivity,ListActivity::class.java))
    }
}