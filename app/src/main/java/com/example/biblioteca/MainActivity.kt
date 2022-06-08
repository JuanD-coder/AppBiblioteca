package com.example.biblioteca

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.biblioteca.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainVinculo: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainVinculo = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainVinculo.root)
        title = ""

        mainVinculo.textView.setOnClickListener {
            val enviar = Intent(this, RegisterActivity::class.java)
            startActivity(enviar)
        }
        val preferences = getSharedPreferences("access", Context.MODE_PRIVATE)

        mainVinculo.button.setOnClickListener {
            val usuario = preferences.getString("usuario", "")
            val claveMain = preferences.getString("claves", "")

            val user = mainVinculo.user.text.toString()
            val password = mainVinculo.claveR.text.toString()

            //Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show()
            //Toast.makeText(this, clave, Toast.LENGTH_SHORT).show()

            if (usuario.equals(user) and claveMain.equals(password)) {
                val editor = preferences.edit()
                editor.putString("sesion", "1")
                editor.apply()
                startActivity(Intent(this, RegistrarUser::class.java))
            } else {
                Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}