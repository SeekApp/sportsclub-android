package com.example.boilerplateandroid.presentation.sportman_detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.boilerplateandroid.databinding.ActivitySportmanDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SportsmanDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySportmanDetailBinding
    private val viewModel : SportsmanDetailsViewModel by viewModel()

    private val sportmanDetailId by lazy {
        intent.extras?.get("ID_SPORTSMAN").toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportmanDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("ID_SPORTSMAN")
        Log.i("ID", "$id")

        lifecycleScope.launchWhenResumed {
            viewModel.safeFetchSportsmanDetails(sportmanDetailId)
        }

        observerSportsmanDetails()
    }

    private fun observerSportsmanDetails(){
        viewModel.sportsmanDetail.observe(this){
            when(it){
                is SportsmanDetailsViewModel.SportsmanDetailUIState.Success -> {
                    Log.i("TAG", it.data.name)
                    binding.txtSportmanName.text = it.data.name
                }
            }
        }
    }

    private fun fillProfile(){
        //TODO complete profile of Sportsman with your details
    }
}