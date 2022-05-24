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

package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
//creando un objeto moshi
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



//interfaz que define como es que retrofit se comunica con el servidor, en este caso sera mediante solicitudes http y get cuando se solicite
interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>//este metodo obtiene la respuesta JSON, y el get le dice que haga este metodo,

}





//Ahora, una vez que se haya realizado toda la configuración, cada vez que su aplicación llame a MarsApi.retrofitService, obtendrá un objeto Singleton Retrofit que implementa MarsApiService.
// Nota: Recuerde que la "instanciación diferida" es cuando la creación de un objeto se retrasa deliberadamente hasta que realmente necesite ese objeto para evitar cálculos innecesarios o el uso de otros recursos informáticos.  Kotlin tiene soporte de primera clase para la creación de instancias diferidas.
object MarsApi {//inicializa el servicio retrofit, este codigo es estandar
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java) }//el create() crea el seervicio de retrofit con la interfaz marsapiservice, debido a que esta operacion necesita muchos recursos se inicia en modo lazy
}







