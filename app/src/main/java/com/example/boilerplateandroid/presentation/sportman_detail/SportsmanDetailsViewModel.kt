package com.example.boilerplateandroid.presentation.sportman_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boilerplateandroid.model.Sportsman
import com.example.boilerplateandroid.repository.sportman_detail.SportsmanDetailsRepository
import com.example.boilerplateandroid.utils.NetworkResult
import kotlinx.coroutines.launch

class SportsmanDetailsViewModel(
    private val repository: SportsmanDetailsRepository
) : ViewModel() {

    private val _sportsmanDetail: MutableLiveData<SportsmanDetailUIState> = MutableLiveData()
    val sportsmanDetail: LiveData<SportsmanDetailUIState> = _sportsmanDetail

    suspend fun safeFetchSportsmanDetails(id: String) {

        runCatching {
            repository.fetchSportsmanDetails(id)
        }.onSuccess {

            if (it is NetworkResult.Success) {
                _sportsmanDetail.postValue(SportsmanDetailUIState.Success(it.data))
            }

            Log.i("TAG", "success")
        }.onFailure {
            Log.i("TAG", "failure")
        }
    }

    sealed interface SportsmanDetailUIState {
        object Loading : SportsmanDetailUIState
        data class Error(val error: Throwable) : SportsmanDetailUIState
        data class Success(val data: Sportsman) : SportsmanDetailUIState
    }
}