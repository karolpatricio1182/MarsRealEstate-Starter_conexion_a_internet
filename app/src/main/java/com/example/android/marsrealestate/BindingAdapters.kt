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

package com.example.android.marsrealestate

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//aqui se encontraran los adaptadores de enlace
/*
sirven para un adaptador de enlace para tomar la URL de un atributo XML asociado con ImageView y usa Glide para cargar la
imagen desde esa URL.  Los adaptadores de enlace son métodos de extensión que se ubican entre una vista y los datos
enlazados para proporcionar un comportamiento personalizado cuando los datos cambian.  En este caso, el comportamiento
personalizado es llamar a Glide para cargar una imagen desde una URL en ImageView.

 */
@BindingAdapter("imageUrl")//La anotación @BindingAdapter le dice al enlace de datos que desea que se ejecute este adaptador de enlace cuando un elemento XML tiene el atributo imageUrl.
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()//convertir la cadena de URL (del XML) en un objeto Uri
        Glide.with(imgView.context)//sirve para cargar la imagen del objeto Uri en ImageView.
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)//animacion de carga de la imagen
                .error(R.drawable.ic_broken_image))//sino se carga la imagen correctmebte manda una imagen rota
            .into(imgView)


    }

}
