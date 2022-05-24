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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.MarsProperty

class PhotoGridAdapter : ListAdapter<MarsProperty,
        PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPropertyViewHolder, position: Int) {
        /*
        Aquí llama a getItem() para obtener el objeto MarsProperty asociado con la posición actual de RecyclerView y
         luego pasa esa propiedad al método bind() en MarsPropertyViewHolder.
         */
        val marsProperty = getItem(position)
        holder.bind(marsProperty)

    }
    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        // El objeto DiffCallback amplía DiffUtil.ItemCallback con el tipo de objeto que desea comparar: MarsProperty.
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem//devuelve verdadero si las referencias de objeto para oldItem y newItem son las mismas.
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }
    /*
     Necesita la variable GridViewItemBinding para vincular MarsProperty al diseño, así que pase la variable a
      MarsPropertyViewHolder. Debido a que la clase base ViewHolder requiere una vista en su constructor,
      le pasa la vista raíz vinculante.
     */
    class MarsPropertyViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty) {//Llame a executePendingBindings() después de configurar la propiedad, lo que hace que la actualización se ejecute de inmediato.
            binding.property = marsProperty
            binding.executePendingBindings()
        }

    }


}

