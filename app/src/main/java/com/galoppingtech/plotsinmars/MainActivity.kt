package com.galoppingtech.plotsinmars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.galoppingtech.plotsinmars.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "ManiAnctivity"


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setRecycler()

        lifecycleScope.launchWhenCreated {
            binding.progressbar.isVisible = true
            val  response = try {
                RetrofitInstance.api.getEstates()
            }catch (e: IOException){

                Log.e(TAG,"IOException error occurred, Check your internet")
                binding.progressbar.isVisible =false
                return@launchWhenCreated

            }catch (e: HttpException){

                Log.e(TAG,"HTTPException error occurred")
                binding.progressbar.isVisible =false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                recyclerAdapter.models = response.body()!!
            }else{
                Log.e(TAG,"Failured")
            }
            binding.progressbar.isVisible =false

        }

    }

    private fun setRecycler() = binding.recyclerView.apply {

        recyclerAdapter = RecyclerAdapter()
        adapter = recyclerAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)

    }
}