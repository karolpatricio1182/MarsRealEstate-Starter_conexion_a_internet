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
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    //este metodo es el que llamara al servicio retrofit y manejara la cadena JSON
    private fun getMarsRealEstateProperties() {
        MarsApi.retrofitService.getProperties().enqueue(
            object: Callback<List<MarsProperty>> {
                // La devolución de llamada onResponse() se llama cuando la solicitud es exitosa y el servicio web devuelve una respuesta.
                override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
                    /*
                    Debido a que response.body() ahora es una lista de objetos MarsProperty, el tamaño de esa lista es la cantidad de propiedades que
                    se analizaron.  Este mensaje de respuesta imprime esa cantidad de propiedades:
                     */
                    _response.value =
                        "Success: ${response.body()?.size} Mars properties retrieved"

                }
                //La devolución de llamada onFailure() se llama cuando falla la respuesta del servicio web.  Para esta respuesta, establezca el estado de _response en "Error: " concatenado con el mensaje del argumento Throwable.
                override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            })

    }
}
