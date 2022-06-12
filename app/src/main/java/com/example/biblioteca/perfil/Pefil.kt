package com.example.biblioteca.perfil

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.biblioteca.databinding.ActivityPefilBinding

class Pefil : AppCompatActivity() {
    private lateinit var vinculo: ActivityPefilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vinculo = ActivityPefilBinding.inflate(layoutInflater)
        setContentView(vinculo.root)
        title = "Acerca de:"

        vinculo.wed.loadUrl("https://github.com/JuanDavid2923 ")
         val config= vinculo.wed.settings
        config.javaScriptEnabled = true

    }

}