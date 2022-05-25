/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsApiFilter
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
enum class MarsApiStatus { LOADING, ERROR, DONE }//esto va a representar todos los estados de la conexion ( carga, éxito y fracaso)

class OverviewViewModel : ViewModel() {

    private val _properties = MutableLiveData<List<MarsProperty>>()
    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
    val navigateToSelectedProperty: LiveData<MarsProperty>
        get() = _navigateToSelectedProperty

    val properties: LiveData<List<MarsProperty>>
        get() = _properties
    // The internal MutableLiveData String that stores the most recent response
    private val _status  = MutableLiveData<MarsApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<MarsApiStatus>
        get() = _status

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)//muestra todas las propiedades cuando la aplicación se carga por primera vez.

    }

    //este metodo es el que llamara al servicio retrofit y manejara la cadena JSON
    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _properties.value = MarsApi.retrofitService.getProperties(filter.value)
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()//esto limpia reciclerview

            }


        }


    }
    fun updateFilter(filter: MarsApiFilter) {//toma un argumento MarsApiFilter y llame a getMarsRealEstateProperties() con ese argumento.
        getMarsRealEstateProperties(filter)
    }
    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }
        /*
        Anula el valor de _navigateToSelectedProperty.
        Necesita esto para marcar el estado de navegación como completo y para evitar que la navegación se active
        nuevamente cuando el usuario regrese de la vista de detalles.
         */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}
