package com.example.biblioteca

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.example.biblioteca.data.RegistrarUser
import com.example.biblioteca.databinding.ActivityMenuBinding
import com.example.biblioteca.perfil.Pefil
import com.example.biblioteca.tipoUser.tipoActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var vinculo: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vinculo = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(vinculo.root)
        title = ""

        vinculo.user.setOnClickListener{
            showDelate("Antes de registar un usuario debe de registar un tipo de Usuario","Aceptar")
            startActivity(Intent(this, RegistrarUser::class.java))
        }


        vinculo.tipo.setOnClickListener{
            startActivity(Intent(this,tipoActivity::class.java))
        }
    }
    private fun showDelate(titulo: String, mensaje: String){
        val alert = android.app.AlertDialog.Builder(this@MenuActivity)
        alert.setTitle(titulo)
        alert.setMessage(mensaje)
        alert.setCancelable(true)
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true //super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.perfil -> startActivity(Intent(this,Pefil::class.java))
            R.id.cerrar -> cerrarSesion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cerrarSesion() {
        val preferences = getSharedPreferences("access", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("sesion","0")
        editor.apply()
        finish()
        startActivity(Intent(this,MainActivity::class.java))
    }

}