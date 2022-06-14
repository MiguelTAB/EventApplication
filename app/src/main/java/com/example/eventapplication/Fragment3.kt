package com.example.eventapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.sql.Time

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [Fragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment3 : Fragment(R.layout.fragment3) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var newRecyclerview : RecyclerView
    private lateinit var newArrayList: ArrayList<Evento>
    private lateinit var adapter : RecyclerAdapter
    lateinit var titleA : Array<String>
    lateinit var detailA : Array<String>
    lateinit var timeStartA : Array<String>
    lateinit var timeEndA : Array<String>
    private var month : Int = 6
    private var day : Int = 9

    /*
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
     */

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        titleA = arrayOf(
            "Demonstração de Armas",
            "Demonstração Força Aérea",
            "Atuação Escola de Mafra",
            "Concerto da Banda da Armada",
            "Demonstração de Explosivos",
            "Cerimonia de Encerramento",
        )

        detailA = arrayOf(
            "Avenida Central - Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vestibulum, lectus sit amet tristique placerat, sem leo facilisis velit, ultrices pellentesque erat justo quis nulla. Etiam libero odio, ultrices a pulvinar ut, condimentum sed turpis.",
            "Avenida da Liberdade - Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vestibulum, lectus sit amet tristique placerat, sem leo facilisis velit, ultrices pellentesque erat justo quis nulla. Etiam libero odio, ultrices a pulvinar ut, condimentum sed turpis.",
            "Avenida da Liberdade - Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vestibulum, lectus sit amet tristique placerat, sem leo facilisis velit, ultrices pellentesque erat justo quis nulla. Etiam libero odio, ultrices a pulvinar ut, condimentum sed turpis.",
            "Theatro Circo - Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vestibulum, lectus sit amet tristique placerat, sem leo facilisis velit, ultrices pellentesque erat justo quis nulla. Etiam libero odio, ultrices a pulvinar ut, condimentum sed turpis.",
            "Avenida da Liberdade - Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vestibulum, lectus sit amet tristique placerat, sem leo facilisis velit, ultrices pellentesque erat justo quis nulla. Etiam libero odio, ultrices a pulvinar ut, condimentum sed turpis.",
            "Avenida Central - Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vestibulum, lectus sit amet tristique placerat, sem leo facilisis velit, ultrices pellentesque erat justo quis nulla. Etiam libero odio, ultrices a pulvinar ut, condimentum sed turpis.",
        )

        timeStartA = arrayOf(
            "9:00",
            "10:00",
            "11:00",
            "14:00",
            "16:00",
            "17:00",
        )

        timeEndA = arrayOf(
            "10:00",
            "11:00",
            "12:00",
            "16:00",
            "17:00",
            "18:00",
        )


/*
        newRecyclerview = requireView().findViewById(R.id.recyclerView)
        newRecyclerview.layoutManager = LinearLayoutManager(activity)
        newRecyclerview.setHasFixedSize(true)

        newArrayList = arrayListOf<Evento>()

        getData()

 */

        /*
        layoutManager = LinearLayoutManager(this)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter()

        recyclerView.adapter = adapter
        */
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment3, container, false)



        return view

    }

    private fun initRecyclerView(view: View){
        newRecyclerview = view.findViewById(R.id.recyclerView3)
        newRecyclerview.layoutManager = LinearLayoutManager(activity)
        newArrayList = arrayListOf<Evento>()
        adapter = RecyclerAdapter(newArrayList)
        newRecyclerview.adapter = adapter
        adapter.setOnItemClickListener(object : RecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putInt("day", day)
                Log.i("Fragment3","bundle input day ${day}")
                bundle.putInt("month", month)
                Log.i("Fragment3","bundle input month ${month}")
                bundle.putString("timestart", timeStartA[position])
                Log.i("Fragment3","bundle input time start ${timeStartA[position]} position ${position}")
                bundle.putString("timeend", timeEndA[position])
                Log.i("Fragment3","bundle input time end ${timeEndA[position]} position ${position}")
                bundle.putString("title", titleA[position])
                Log.i("Fragment3","bundle input title ${titleA[position]} position ${position}")
                bundle.putString("detail", detailA[position])
                Log.i("Fragment3","bundle input detail ${detailA[position]} position ${position}")

                val fragment = DetailsFragment()
                fragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer,fragment)?.commit()
            }


        })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun getData(){
        for(i in titleA.indices){
            val evento = Evento(titleA[i],detailA[i],timeStartA[i],timeEndA[i])
            newArrayList.add(evento)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        initRecyclerView(view)
        getData()

    }

}