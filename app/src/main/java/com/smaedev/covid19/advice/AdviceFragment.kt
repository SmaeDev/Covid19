package com.smaedev.covid19.advice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covid19.R
import com.smaedev.covid19.adapter.AdviceCardViewAdaptater
import com.smaedev.covid19.databinding.FragmentAdviceBinding
import com.smaedev.covid19.db.Advice
import java.util.*

class AdviceFragment : Fragment() {


    private lateinit var adviceViewModel: AdviceViewModel
    private lateinit var binding: FragmentAdviceBinding
    lateinit var adapterAdvice : AdviceCardViewAdaptater
    var adviceList: List<Advice>? = null


    private lateinit var recyclerViewAdv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_advice, container, false)
        binding = FragmentAdviceBinding.bind(root)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        adviceViewModel =ViewModelProvider(this).get(AdviceViewModel::class.java)
        adviceViewModel.getAdvices()

        adviceList = ArrayList<Advice>()
        recyclerViewAdv = binding.recyclerViewAdvice
        recyclerViewAdv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewAdv.setHasFixedSize(true)

        //binding.ibtBackAdvice.setOnClickListener{findNavController().navigate(R.id.nav_home, null)}
        binding.ibtBackAdvice.setOnClickListener{findNavController().navigateUp()}

        adviceViewModel.allAdvices.observe(viewLifecycleOwner, Observer { advices ->
            advices?.let {
                adapterAdvice = AdviceCardViewAdaptater(requireContext(), it)
                recyclerViewAdv.adapter = adapterAdvice
                adapterAdvice.setAdvices(it)

                Log.d("Advices ///////","Adv ${it}")
            }
        })

        return root
    }

}