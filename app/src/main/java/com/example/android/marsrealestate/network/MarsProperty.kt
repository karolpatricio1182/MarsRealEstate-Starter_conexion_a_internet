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

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/*
En esta clase de datos moshi va a almacenar los resultados despues de analizar el json de respuesta
La interfaz Parcelable permite que los objetos se serialicen, de modo que los datos de los objetos se puedan pasar
entre fragmentos o actividades.  En este caso, para que los datos dentro del objeto MarsProperty se pasen al fragmento
de detalle a través de Safe Args, MarsProperty debe implementar la interfaz Parcelable.
 */
@Parcelize
data class MarsProperty (
    val id: String, @Json(name = "img_src") val imgSrcUrl: String,//Para usar nombres de variables en su clase de datos que difieran de los nombres clave en la respuesta JSON, use la anotación @Json.
    val type: String,
    val price: Double) : Parcelable {
    val isRental
        get() = type == "rent"
}




