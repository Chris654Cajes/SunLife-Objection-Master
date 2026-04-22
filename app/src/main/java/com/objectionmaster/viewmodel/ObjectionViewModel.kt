package com.objectionmaster.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.objectionmaster.data.ObjectionRepository
import com.objectionmaster.model.Objection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ObjectionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ObjectionRepository(application)
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _objections = MutableLiveData<List<Objection>>()
    val objections: LiveData<List<Objection>> = _objections

    private val _selectedObjection = MutableLiveData<Objection?>()
    val selectedObjection: LiveData<Objection?> = _selectedObjection

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadAllObjections()
    }

    fun loadAllObjections() {
        uiScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getAllObjections()
                _objections.value = result
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchObjections(query: String) {
        uiScope.launch {
            _isLoading.value = true
            try {
                val result = repository.searchObjections(query)
                _objections.value = result
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getObjectionById(id: Int) {
        uiScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getObjectionById(id)
                _selectedObjection.value = result
            } finally {
                _isLoading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
