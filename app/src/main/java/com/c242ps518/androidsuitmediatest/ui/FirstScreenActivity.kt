package com.c242ps518.androidsuitmediatest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.c242ps518.androidsuitmediatest.R
import com.c242ps518.androidsuitmediatest.data.preference.NameSharedPreference
import com.c242ps518.androidsuitmediatest.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding
    private lateinit var nameSharedPreference: NameSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.first_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameSharedPreference = NameSharedPreference(this)

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isEmpty() || name.isBlank()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondScreenActivity::class.java)
            nameSharedPreference.saveName(name)
            startActivity(intent)
        }

        binding.btnCheck.setOnClickListener {
            val palindrome = binding.etPalindrome.text.toString()
            if (palindrome.isEmpty() || palindrome.isBlank()) {
                Toast.makeText(this, "Please enter your palindrome", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isPalindrome = isPalindrome(palindrome)
            val message = if (isPalindrome) "isPalindrome" else "not palindrome"
            showDialog(message)
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val cleanText = text.replace("\\s".toRegex(), "").lowercase()
        return cleanText == cleanText.reversed()
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

}