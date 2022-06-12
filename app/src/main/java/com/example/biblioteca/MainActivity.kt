package com.example.biblioteca

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.biblioteca.data.RegistrarUser
import com.example.biblioteca.databinding.ActivityMainBinding
import com.example.biblioteca.perfil.Pefil

class MainActivity : AppCompatActivity() {
    private lateinit var mainVinculo: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainVinculo = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainVinculo.root)
        title = "Inicio"

        mainVinculo.registar.setOnClickListener {
            val enviar = Intent(this, RegisterActivity::class.java)
            startActivity(enviar)
        }
        val preferences = getSharedPreferences("access", Context.MODE_PRIVATE)

        mainVinculo.button.setOnClickListener {
            val usuario = preferences.getString("usuario", "")
            val claveMain = preferences.getString("clave", "")

            val user = mainVinculo.user.text.toString()
            val password = mainVinculo.claveR.text.toString()

            // Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show()
            // Toast.makeText(this, claveMain, Toast.LENGTH_SHORT).show()

            if (usuario.equals(user) and claveMain.equals(password)) {
                val editor = preferences.edit()
                editor.putString("sesion", "1")
                editor.apply()
                startActivity(Intent(this, MenuActivity::class.java))
            } else {
                Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
                mainVinculo.user.error = "Ingrese su E-mail"
                mainVinculo.claveR.error = "Ingrese su Contraseña"
                mainVinculo.user.requestFocus()
                mainVinculo.claveR.requestFocus()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)
        return true //super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.perfil -> startActivity(Intent(this, Pefil::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}