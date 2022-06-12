package com.example.biblioteca.data

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.biblioteca.R
import com.example.biblioteca.data.dao.Entidades
import com.example.biblioteca.databinding.ActivityRegistrarUserBinding
import com.example.biblioteca.perfil.Pefil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegistrarUser : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.open_bottom)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.close_bottom)}
    private val fromButtom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom)}
    private val toButtom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom)}

    private var clicked = false

    private lateinit var binding: ActivityRegistrarUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = ""

        binding.tipoUser.text.isEmpty()


        binding.agregar.setOnClickListener{
            var nombre:String?=null
            var id:Int?=null
            var correo:String?=null
            var clave:String?=null
            var tipo:String?=null
            if (!binding.tipoUser.text.isEmpty() && !binding.correos.text.isEmpty() && !binding.claveTipos.text.isEmpty() && !binding.password.text.isEmpty() && !binding.tipoUsuario.text.isEmpty()){
                val userInfo = Entidades(binding.correos.text.toString().toInt(),binding.tipoUser.text.toString(),binding.claveTipos.text.toString(),
                    binding.password.text.toString(),binding.tipoUsuario.text.toString())
                GlobalScope.launch(Dispatchers.IO) {
                    BaseDatos.getInstance(this@RegistrarUser).usuarioDao().insert(userInfo)
                    }
                Toast.makeText(applicationContext, "Registro Insertado", Toast.LENGTH_SHORT).show()
            } else {
                binding.correos.error = "Ingrese la identificacion"
                binding.tipoUser.error = "Ingrese su nombre"
                binding.claveTipos.error = "Ingrese un correo"
                binding.password.error = "Ingrese una contraseña"
                binding.tipoUser.error = "Ingrese el ID del tipo de usuario"
                binding.tipoUser.requestFocus()
                binding.correos.requestFocus()
                binding.tipoUser.requestFocus()
                binding.claveTipos.requestFocus()
                binding.password.requestFocus()
            }
        }

        binding.add.setOnClickListener{
            mostrarButtom()
        }
        binding.delate.setOnClickListener{
            val userId:EditText = EditText(this@RegistrarUser)
            val alerts = AlertDialog.Builder(this@RegistrarUser)
                         alerts.setTitle("Ingrese la identificacion del usuario")
                         alerts.setView(userId)
            alerts.setPositiveButton("Borrar Usuario",object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    val uid: Int = userId.text.toString().toInt()
                    GlobalScope.launch {
                        BaseDatos.getInstance(this@RegistrarUser).usuarioDao().delate(uid)
                    }
                    showDelate("Informacion del usuario","Registro eliminado")
                }
            })
            alerts.create()
            alerts.show()
        }
        binding.show.setOnClickListener{
            lateinit var allUser:List<Entidades>
            val userData:StringBuffer = StringBuffer()
            GlobalScope.launch(Dispatchers.IO) {
                allUser = BaseDatos.getInstance(this@RegistrarUser).usuarioDao().display()
                launch (Dispatchers.Main){
                    allUser.forEach{
                        userData.append("identifiacion:" + it.idenficacion + " Nombre: " + "\n" + it.name + " E-mail:: " + it.telefono + " Contraseña " + it.fecha + " Tipo de usuario" +it.tipo + "\n")
                    }
                    mostrar(userData.toString())
                }
            }
        }
        binding.update.setOnClickListener{
            val userId:EditText = EditText(this@RegistrarUser)
            val alerts = AlertDialog.Builder(this@RegistrarUser)
            alerts.setTitle("Ingrese la identificacion del usuario")
            alerts.setView(userId)
            alerts.setPositiveButton("Actualizar Usuario",object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    val uid:Int = userId.text.toString().toInt()
                    if (!binding.tipoUser.text.isEmpty() && !binding.correos.text.isEmpty() && !binding.claveTipos.text.isEmpty() && !binding.password.text.isEmpty() && !binding.tipoUsuario.text.isEmpty()){
                        val userInfo = Entidades(uid,binding.correos.text.toString(),binding.claveTipos.text.toString(),binding.password.text.toString(),binding.tipoUsuario.text.toString())
                        GlobalScope.launch(Dispatchers.IO) {
                            BaseDatos.getInstance(this@RegistrarUser).usuarioDao().update(userInfo)
                        }
                        Toast.makeText(applicationContext, "Registro Insertado", Toast.LENGTH_SHORT).show()
                    } else{
                        binding.correos.error = "Ingrese la identificacion"
                        binding.claveTipos.error = "Ingrese un correo"
                        binding.password.error = "Ingrese una contraseña"
                        binding.tipoUser.error = "Ingrese el ID del tipo de usuario"
                        binding.tipoUser.requestFocus()
                        binding.correos.requestFocus()
                        binding.claveTipos.requestFocus()
                        binding.password.requestFocus()
                    }
                    showDelate("Informacion del usuario","Registro actualizado")
                }
            })
            alerts.create()
            alerts.show()
        }
 

    }

    private fun mostrarButtom() {
        setAnimation(clicked)
        setVisibles(clicked)
        setClicked(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.delate.visibility = View.VISIBLE
            binding.show.visibility = View.VISIBLE
            binding.update.visibility = View.VISIBLE
        } else {
            binding.delate.visibility = View.INVISIBLE
            binding.show.visibility = View.INVISIBLE
            binding.update.visibility = View.INVISIBLE
        }
    }

    private fun setVisibles(clicked: Boolean) {
        if (!clicked) {
            binding.delate.startAnimation(fromButtom)
            binding.show.startAnimation(fromButtom)
            binding.update.startAnimation(fromButtom)
            binding.add.startAnimation(rotateOpen)
        } else {
            binding.delate.startAnimation(toButtom)
            binding.show.startAnimation(toButtom)
            binding.update.startAnimation(toButtom)
            binding.add.startAnimation(rotateClose)
        }
    }

    private fun setClicked(clicked: Boolean){
        if (!clicked) {
            binding.delate.isClickable = true
            binding.show.isClickable = true
            binding.update.isClickable = true
        } else {
            binding.delate.isClickable = false
            binding.show.isClickable = false
            binding.update.isClickable = false
        }
    }

    private fun showDelate(titulo: String, mensaje: String){
        val alert = android.app.AlertDialog.Builder(this@RegistrarUser)
        alert.setTitle(titulo)
        alert.setMessage(mensaje)
        alert.setCancelable(true)
        alert.show()
    }

    private fun mostrar(userData: String) {
        Toast.makeText(this@RegistrarUser, "${userData}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true //super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.perfil -> startActivity(Intent(this, Pefil::class.java))
            R.id.cerrar -> cerrarSesion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cerrarSesion() {
        val preferences = getSharedPreferences("access", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("sesion", "0")
        editor.apply()
        finish()
    }

    }