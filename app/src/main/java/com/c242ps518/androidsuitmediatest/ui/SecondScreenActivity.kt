package com.c242ps518.androidsuitmediatest.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.c242ps518.androidsuitmediatest.R
import com.c242ps518.androidsuitmediatest.data.preference.NameSharedPreference
import com.c242ps518.androidsuitmediatest.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var nameSharedPreference: NameSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameSharedPreference = NameSharedPreference(this)
        binding.tvName.text = nameSharedPreference.getName()

        Log.d("SecondScreenActivity", nameSharedPreference.getName())

        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressedDispatcher.onBackPressed()
        }

        binding.btnChoose.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
        }

        val username = intent.getStringExtra("username")
        if (username != null) {
            binding.tvUsername.text = username
        }
    }
}