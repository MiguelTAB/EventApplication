package com.example.eventapplication

import android.graphics.Color
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapplication.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.Response
import java.io.IOException

class MainActivity() : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var holidaysMonthsL = arrayListOf<Int>()
    var holidaysDaysL = arrayListOf<Int>()

    var dayOne : Int = 9
    var dayTwo : Int = 10
    var dayThree : Int = 11


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchJson()






        binding.fragment1Btn.setOnClickListener(){

            replaceFragment(Fragment1())

        }

        binding.fragment2Btn.setOnClickListener(){

            replaceFragment(Fragment2())

        }

        binding.fragment3Btn.setOnClickListener(){

            replaceFragment(Fragment3())

        }
    }

    private fun fetchJson(){
        println("Attempting to fetch JSON")

        val url = "https://calendarific.com/api/v2/holidays?&api_key=cbee9c5303671195f92a148a6709b2d7a68aee90&country=PT&year=2022&month=6"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val holidays = gson.fromJson(body, Holidays::class.java)

                fun getHolidayCount(): Int{
                    return holidays.response.holidays.size
                }

                fun getHolidaysDay(): MutableList<Int>{
                    var holidaysD = mutableListOf<Int>()
                    holidays.response.holidays.forEach{Date->
                        holidaysD.add(Date.date.datetime.day)
                    }
                    return holidaysD
                }

                fun getHolidaysMonth(): MutableList<Int>{
                    var holidaysM = mutableListOf<Int>()
                    holidays.response.holidays.forEach{Date->
                        holidaysM.add(Date.date.datetime.month)
                    }
                    return holidaysM
                }

                fun getHolidays(): MutableList<Datetime> {
                    var holidaysA = mutableListOf<Datetime>()
                    holidays.response.holidays.forEach{Date->
                        holidaysA.add(Date.date.datetime)
                    }
                    return holidaysA
                }




                holidaysDaysL = getHolidaysDay() as ArrayList<Int>
                holidaysMonthsL = getHolidaysMonth() as ArrayList<Int>
                println(holidaysMonthsL)
                println(holidaysDaysL)

                val bundle = Bundle()

                bundle.putIntegerArrayList("days", holidaysDaysL)
                Log.i("Fragment1","bundle input day arraylist")
                bundle.putIntegerArrayList("months", holidaysMonthsL)
                Log.i("Fragment1","bundle input month arraylist")

                val fragment1 = Fragment1()
                val fragment2 = Fragment2()
                val fragment3 = Fragment3()

                fragment1.arguments = bundle
                fragment2.arguments = bundle
                fragment3.arguments = bundle

                for (day in holidaysDaysL)
                {
                    when(day){

                        dayOne-> {
                            binding.fragment1Btn.setBackgroundColor(resources.getColor(R.color.lightorange))
                            Log.i("Holiday","${day} of June is a holiday")
                            Handler(Looper.getMainLooper()).post {
                                Toast.makeText(applicationContext, "${day} of June is a holiday!", Toast.LENGTH_SHORT).show()
                            }
                        }
                        dayTwo-> {
                            binding.fragment2Btn.setBackgroundColor(resources.getColor(R.color.lightorange))
                            Log.i("Holiday","${day} of June is a holiday")
                            Handler(Looper.getMainLooper()).post {
                                Toast.makeText(applicationContext, "${day} of June is a holiday!", Toast.LENGTH_SHORT).show()
                            }
                            //Toast.makeText(applicationContext,"${day} of June is a holiday!",Toast.LENGTH_LONG).show()
                        }
                        dayThree-> {
                            binding.fragment3Btn.setBackgroundColor(resources.getColor(R.color.lightorange))
                            Log.i("Holiday","${day} of June is a holiday")
                            Handler(Looper.getMainLooper()).post {
                                Toast.makeText(applicationContext, "${day} of June is a holiday!", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else-> Log.i("WHEN","No holidays found for day ${day}")
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

        })

    }

/*
    private fun ShowHoliday(holyButton: Button){
            holyButton.setBackgroundColor(Color.RED)
    }

 */


    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()

    }
}

