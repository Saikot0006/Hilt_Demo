package com.example.hiltdemo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.hiltdemo.databinding.FragmentHomeBinding
import com.example.hiltdemo.modal.MovieList
import com.example.hiltdemo.work_manager.MovieWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    var i : Int =1
    lateinit var request : OneTimeWorkRequest
    lateinit var data : Data
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        data = Data.Builder().putInt("input_data",i).build()

        request = OneTimeWorkRequestBuilder<MovieWorker>()
            .setInputData(data)
            .build()


        WorkManager.getInstance(requireContext()).enqueue(request)

        viewModel.getNowShowingMovie(i).observe(requireActivity()){
            it.results.forEach {
                Log.e("TAG", "onCreateView: "+it.original_title)
            }
        }

        binding.btnID.setOnClickListener {
            i = i+1
            Log.e("value1", "onCreateView: "+i)
            viewModel.getNowShowingMovie(i)
            data = Data.Builder().putInt("input_data",i).build()

            request = OneTimeWorkRequestBuilder<MovieWorker>()
                .setInputData(data)
                .build()

            WorkManager.getInstance(requireContext()).enqueue(request)


        }


        return binding.root
    }

}