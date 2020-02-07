package com.e.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttontojim : Button =  findViewById(R.id.buttontojim)


        buttontojim.setOnClickListener{
            clickEvent(it)
        }
        buttontobar.setOnClickListener{
            clickEvent(it)
        }
        buttontopool.setOnClickListener{
            clickEvent(it)
        }
        buttontospa.setOnClickListener {
            clickEvent(it)
        }


    }



    private fun clickEvent(view: View){
        val myIntent : Intent = Intent(this, sportServices::class.java)
        when(view.id){
            R.id.buttontobar ->  myIntent.putExtra("category", "bar")

            R.id.buttontopool -> myIntent.putExtra("category", "pool")
            R.id.buttontospa -> myIntent.putExtra("category", "spa")
            R.id.buttontojim -> myIntent.putExtra("category", "gym")
        }
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left )


    }

}
