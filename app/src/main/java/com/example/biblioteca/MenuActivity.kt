package com.example.biblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biblioteca.data.RegistrarUser
import com.example.biblioteca.databinding.ActivityMenuBinding
import com.example.biblioteca.tipoUser.tipoActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var vinculo: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vinculo = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(vinculo.root)
        title = ""

        vinculo.user.setOnClickListener{
            startActivity(Intent(this,RegistrarUser::class.java))
        }

        vinculo.tipo.setOnClickListener{
            startActivity(Intent(this,tipoActivity::class.java))
        }
    }
}