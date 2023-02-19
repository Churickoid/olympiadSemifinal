package com.example.olympiadsemifinal.presentation.screen.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.olympiadsemifinal.R
import com.example.olympiadsemifinal.domain.model.Service

class ServicesAdapter : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    var serviceList = listOf<Service>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }
    var onItemClickListener: ((Service) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun getItemCount(): Int = serviceList.size


    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val serviceElement = serviceList[position]
        with(holder) {
            serviceName.text = serviceElement.name
            Glide.with(serviceIcon.context)
                .load(serviceElement.icon_url)
                .placeholder(R.drawable.loading_icon)
                .error(R.drawable.question_icon)
                .into(serviceIcon)
            itemView.setOnClickListener {
                onItemClickListener?.invoke(serviceElement)
            }
        }

    }

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceIcon = itemView.findViewById<ImageView>(R.id.iconImageView)!!
        val serviceName = itemView.findViewById<TextView>(R.id.nameTextView)!!
    }

}



