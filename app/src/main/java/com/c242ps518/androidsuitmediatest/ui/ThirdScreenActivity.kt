package com.c242ps518.androidsuitmediatest.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.c242ps518.androidsuitmediatest.R
import com.c242ps518.androidsuitmediatest.adapter.LoadingStateAdapter
import com.c242ps518.androidsuitmediatest.adapter.PagingUserAdapter
import com.c242ps518.androidsuitmediatest.databinding.ActivityThirdScreenBinding
import com.c242ps518.androidsuitmediatest.ui.viewmodel.ThirdScreenViewModel
import com.c242ps518.androidsuitmediatest.ui.viewmodel.ViewModelFactory

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var viewModel: ThirdScreenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.third_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factory: ViewModelFactory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[ThirdScreenViewModel::class.java]

        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressedDispatcher.onBackPressed()
        }

        getUsers()

        binding.swipeRefreshLayout.setOnRefreshListener {
            getUsers()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getUsers() {
        val adapter = PagingUserAdapter()
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        binding.rvUser.adapter = adapter
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        viewModel.quote.observe(this) {
            if (it != null) {
                binding.noData.visibility = View.GONE
            } else {
                binding.noData.visibility = View.VISIBLE
            }

            adapter.submitData(lifecycle, it)
        }
    }
}