package com.example.biblioteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biblioteca.databinding.ActivityRegistrarUserBinding

class RegistrarUser : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = ""



    }
}