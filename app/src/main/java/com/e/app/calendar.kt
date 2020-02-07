package com.e.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.activity_sport_services.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class calendar : AppCompatActivity() {

    var service : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val timeRes = arrayOf("12:30-14:00","14:15-15:30","15:45-17:00" )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeRes)

        spinner.adapter=arrayAdapter

        spinner.onItemSelectedListener=object :

            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }
    }

    override fun onStart() {
        super.onStart()

        val intent : Intent = getIntent()
        service = intent.getStringExtra("service")!!

        when(service){
            "MMA" -> serviceTitle.text = "MMA ринг"
            "Brusya" -> serviceTitle.text = "Брусья"
            "gym" -> serviceTitle.text = "Качалка"
            "cardio" -> serviceTitle.text = "Беговая дорожка"
            "yoga" -> serviceTitle.text = "Йога"
        }
        getReservation()
    }



    fun getReservation() {
        App.service.getReservation(service,calendarView.date).enqueue(object : Callback<List<ReservationEnity>> {
            override fun onFailure(call: Call<List<ReservationEnity>>, t: Throwable) {
                Toast.makeText(this@calendar, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<ReservationEnity>>, response: Response<List<ReservationEnity>>) {
                //Log.e("myLog","$response")
                //Toast.makeText(this@calendar, "${response.body()}", Toast.LENGTH_LONG).show()
                //response.body()?.let { adapter.bind(it) }


            }
        })
    }

    fun nextAct(view: View) {
        val myIntent = Intent(this, confirmOrder::class.java)
        //myIntent.putExtra()
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left )
    }

    fun backAct(view: View){
        val myIntent = Intent(this,sportServices::class.java)
        //myIntent.putExtra()


        startActivity(myIntent)

    }
}
