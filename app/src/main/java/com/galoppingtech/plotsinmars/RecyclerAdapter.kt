package com.galoppingtech.plotsinmars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.galoppingtech.plotsinmars.databinding.ItemViewBinding

class RecyclerAdapter:RecyclerView.Adapter<RecyclerAdapter.myViewHolder>() {
    inner class myViewHolder(val binding: ItemViewBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object :DiffUtil.ItemCallback<Model>(){
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return  oldItem == newItem
        }

    }

    private  val differ = AsyncListDiffer(this,diffCallback)
    var models :List<Model>
    get() = differ.currentList
    set(value) {differ.submitList(value)}

    override fun getItemCount() = models.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.binding.apply {
            val  modelo = models[position]
            ivPrice.text = modelo.price.toString()
            ivType.text = modelo.type

           // ivImage.setImageResource(modelo.img_src)


            Glide.with(this.root.context)
                .load(modelo.img_src)
                .into(holder.binding.ivImage)

        }

    }



}