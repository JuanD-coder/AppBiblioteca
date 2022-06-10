package com.example.biblioteca.data

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.biblioteca.R
import com.example.biblioteca.data.dao.Entidades
import com.example.biblioteca.databinding.ActivityRegistrarUserBinding
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

        binding.names.text.isEmpty()


        binding.agregar.setOnClickListener{
            var nombre:String?=null
            var id:Int?=null
            var correo:String?=null
            var clave:String?=null
            if (!binding.names.text.isEmpty() && !binding.identificacion.text.isEmpty() && !binding.email.text.isEmpty() && !binding.password.text.isEmpty()){
                val userInfo = Entidades(binding.identificacion.text.toString().toInt(),binding.names.text.toString(),binding.email.text.toString(),binding.password.text.toString())
                GlobalScope.launch(Dispatchers.IO) {
                    BaseDatos.getInstance(this@RegistrarUser).usuarioDao().insert(userInfo)
                    }
                Toast.makeText(applicationContext, "Registro Insertado", Toast.LENGTH_SHORT).show()
            } else{
                binding.identificacion.error = "Ingrese la identificacion"
                binding.names.error = "Ingrese su nombre"
                binding.email.error = "Ingrese un correo"
                binding.password.error = "Ingrese una contraseña"
                binding.identificacion.requestFocus()
                binding.names.requestFocus()
                binding.email.requestFocus()
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
                        userData.append("id=" + it.idenficacion + " Nombre= " + it.name + " E-mail= " + it.correo + " Contraseña= " + it.clave + "\n")
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
                    if (!binding.names.text.isEmpty() && !binding.identificacion.text.isEmpty() && !binding.email.text.isEmpty() && !binding.password.text.isEmpty()){
                        val userInfo = Entidades(binding.identificacion.text.toString().toInt(),binding.names.text.toString(),binding.email.text.toString(),binding.password.text.toString())
                        GlobalScope.launch(Dispatchers.IO) {
                            BaseDatos.getInstance(this@RegistrarUser).usuarioDao().update(userInfo)
                        }
                        Toast.makeText(applicationContext, "Registro Insertado", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(applicationContext, "Por vafor inserte el primer valor", Toast.LENGTH_SHORT).show()
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
}