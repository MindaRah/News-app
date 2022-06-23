package com.example.mvvmretrofitapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmretrofitapp.adapter.NewsAdapter
import com.example.mvvmretrofitapp.databinding.FragmentNewsBinding
import com.example.mvvmretrofitapp.utils.ResponseStatus
import com.example.mvvmretrofitapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment: Fragment() {

    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModels<NewsViewModel>()
    private val binding : FragmentNewsBinding by lazy{
        FragmentNewsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsAdapter= NewsAdapter()
        binding.newsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.newsRecycler.adapter = newsAdapter

        viewModel.news.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is ResponseStatus.LOADING -> {
                    binding.swipe.isRefreshing = true
                }
                is ResponseStatus.SUCCESS -> {
                    binding.swipe.isRefreshing = false
                    dataState.news.data?.let { it->
                        newsAdapter.setUsersData(it)
                    }
                }
                is ResponseStatus.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        dataState.error.message,
                        Toast.LENGTH_LONG).show()
                    binding.swipe.isRefreshing = false
                }
            }
        }
        viewModel.getAllNews()
        return binding.root
    }
}