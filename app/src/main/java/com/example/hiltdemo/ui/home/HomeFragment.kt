package com.example.hiltdemo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.hiltdemo.databinding.FragmentHomeBinding
import com.example.hiltdemo.work_manager.MovieWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        val request = OneTimeWorkRequestBuilder<MovieWorker>().build()

        WorkManager.getInstance(requireContext()).enqueue(request)

       /* WorkManager.getInstance(requireActivity().applicationContext)
            .getWorkInfoByIdLiveData(request.id).observe(requireActivity()) {
                Log.e("hello", "onCreateView: hello09"+it.id )
                *//*viewModel.getNowShowingMovie(1)
                    .observe(requireActivity()){
                    it.results.forEach {
                        Log.e("original_title", "onCreateView: "+it.original_title )
                    }
                }*//*

            }*/

        WorkManager.getInstance(requireContext())
            .getWorkInfoByIdLiveData(request.id).observe(requireActivity()) {
                if (it.state.isFinished) {
                    viewModel.getNowShowingMovie(1)
                        .observe(requireActivity()) {
                            it.results.forEach {

                                //val data = Data.Builder().putString("movie",movie)
                                Log.e("original_title", "onCreateView: " + it.original_title)
                            }
                        }
                }
            }


        return binding.root
    }

}