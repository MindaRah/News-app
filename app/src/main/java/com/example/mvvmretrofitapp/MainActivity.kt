package com.example.mvvmretrofitapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofitapp.utils.ResponseStatus
import com.example.mvvmretrofitapp.view.NewsFragment
import com.example.mvvmretrofitapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
      /*  viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.news.observe(this){status->
            when (status) {
                is ResponseStatus.SUCCESS -> {
                    Log.i("mind", status.news.category.toString())
                }
                is ResponseStatus.ERROR -> {
                    Log.i("mind", "Error")
                }
                is ResponseStatus.LOADING -> {
                    Log.i("mind", "isLoading")
                }
            }

        }
        viewModel.getAllNews()*/
        return super.onCreateView(name, context, attrs)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.
                beginTransaction()
            .replace(R.id.lay_container,NewsFragment())
            .commit()

    }
}