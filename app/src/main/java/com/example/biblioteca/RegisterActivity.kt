package com.example.biblioteca

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.biblioteca.databinding.ActivityMainBinding
import com.example.biblioteca.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerVinculo: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerVinculo = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerVinculo.root)
        title = ""

        val preferences = getSharedPreferences("access", Context.MODE_PRIVATE)

        registerVinculo.regis.setOnClickListener {
            val editor = preferences.edit()
            var nombres = registerVinculo.name.text.toString()
            val email = registerVinculo.correo.text.toString()
            var password = registerVinculo.claveR.text.toString()
            editor.putString("nombre",nombres)
            editor.putString("usuario",email)
            editor.putString("clave",password)
            editor.putString("sesion","1")
            editor.apply()
            Toast.makeText(this, "Registrado con exito", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}