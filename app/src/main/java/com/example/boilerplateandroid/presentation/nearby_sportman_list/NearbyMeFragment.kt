package com.example.boilerplateandroid.presentation.nearby_sportman_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boilerplateandroid.R
import com.example.boilerplateandroid.R.*
import com.example.boilerplateandroid.databinding.FragmentNearbyMeBinding
import com.example.boilerplateandroid.presentation.sportman_detail.SportsmanDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NearbyMeFragment : Fragment() {

    private var binding: FragmentNearbyMeBinding? = null
    private val viewModel : NearbyViewModel by viewModel()
    private lateinit var adapter: NearbyAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNearbyMeBinding.inflate(inflater, container, false)

        observerNearbyMeList()
        setupRecyclerView()

        return binding!!.root
    }

    private fun observerNearbyMeList(){
        viewModel.nearbyMe.observe(viewLifecycleOwner){
            when(it){
                is NearbyViewModel.NearbyMeListUIState.Success -> {
                    Log.i("TAG", "success nearby list")
                    Log.i("TAG", it.data[0].name)

                    adapter.submitList(it.data)
                }
            }
        }
    }

    private fun setupRecyclerView(){
        adapter = NearbyAdapter {
            Log.i(TAG, "on click $it")
            val intent = Intent(context, SportsmanDetailsActivity::class.java)
            intent.putExtra("ID_SPORTSMAN", it.id)
            startActivity(intent)
        }
        binding!!.rvSportmans.adapter = adapter
        binding!!.rvSportmans.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        const val TAG = "NearbyActivity"
    }
}