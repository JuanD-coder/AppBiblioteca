package com.example.biblioteca.tipoUser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biblioteca.databinding.ActivityTipoBinding

class tipoActivity : AppCompatActivity() {
    private lateinit var inicio:ActivityTipoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicio = ActivityTipoBinding.inflate(layoutInflater)
        setContentView(inicio.root)

    }
}