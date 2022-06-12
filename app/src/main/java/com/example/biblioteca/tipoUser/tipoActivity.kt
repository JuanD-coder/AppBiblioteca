package com.example.biblioteca.tipoUser

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
import com.example.biblioteca.data.BaseDatos
import com.example.biblioteca.databinding.ActivityTipoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class tipoActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.open_bottom)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.close_bottom)}
    private val fromButtom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom)}
    private val toButtom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom)}

    private var clicked = false

    private lateinit var inicio:ActivityTipoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicio = ActivityTipoBinding.inflate(layoutInflater)
        setContentView(inicio.root)
        title = ""

        inicio.tipoUser.text.isEmpty()


        inicio.agregar.setOnClickListener{
            var id:Int?=null
            var tipo:String?=null
            if (!inicio.tipoUser.text.isEmpty()){
                val userInfo = EntitiesFk(null,inicio.tipoUser.text.toString())
                GlobalScope.launch(Dispatchers.IO) {
                    BaseDatos.getInstance(this@tipoActivity).tiposDao().insert(userInfo)
                }
                Toast.makeText(applicationContext, "Registro Insertado", Toast.LENGTH_SHORT).show()
            } else{
                inicio.tipoUser.error = "Ingrese el Tipo de usario"
                inicio.tipoUser.requestFocus()
            }
        }

        inicio.add.setOnClickListener{
            mostrarButtom()
        }
        inicio.delate.setOnClickListener{
            val userId: EditText = EditText(this@tipoActivity)
            val alerts = AlertDialog.Builder(this@tipoActivity)
            alerts.setTitle("Ingrese el ID ")
            alerts.setView(userId)
            alerts.setPositiveButton("Borrar",object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    val uid: Int = userId.text.toString().toInt()
                    GlobalScope.launch {
                        BaseDatos.getInstance(this@tipoActivity).tiposDao().delate(uid)
                    }
                    showDelate("Informacion del Tipo de usuario","Registro eliminado")
                }
            })
            alerts.create()
            alerts.show()
        }
        inicio.show.setOnClickListener{
            lateinit var allUser:List<EntitiesFk>
            val userData:StringBuffer = StringBuffer()
            GlobalScope.launch(Dispatchers.IO) {
                allUser = BaseDatos.getInstance(this@tipoActivity).tiposDao().mostar()
                launch (Dispatchers.Main){
                    allUser.forEach{
                        userData.append("ID:  " + it.id_correo + "  Tipo de usuario: " + it.tipoUser + "\n")
                    }
                    mostrar(userData.toString())
                }
            }
        }
        inicio.update.setOnClickListener{
            val userId: EditText = EditText(this@tipoActivity)
            val alerts = AlertDialog.Builder(this@tipoActivity)
            alerts.setTitle("Ingrese el ID")
            alerts.setView(userId)
            alerts.setPositiveButton("Actualizar Usuario",object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    val uid:Int = userId.text.toString().toInt()
                    if (!inicio.tipoUser.text.isEmpty()){
                        val userInfo = EntitiesFk(uid,inicio.tipoUser.text.toString())
                        GlobalScope.launch(Dispatchers.IO) {
                            BaseDatos.getInstance(this@tipoActivity).tiposDao().update(userInfo)
                        }
                        Toast.makeText(applicationContext, "Registro Actualizado", Toast.LENGTH_SHORT).show()
                    } else{
                        inicio.tipoUser.error = "Ingrese el Tipo de usario"
                        inicio.tipoUser.requestFocus()
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
            inicio.delate.visibility = View.VISIBLE
            inicio.show.visibility = View.VISIBLE
            inicio.update.visibility = View.VISIBLE
        } else {
            inicio.delate.visibility = View.INVISIBLE
            inicio.show.visibility = View.INVISIBLE
            inicio.update.visibility = View.INVISIBLE
        }
    }

    private fun setVisibles(clicked: Boolean) {
        if (!clicked) {
            inicio.delate.startAnimation(fromButtom)
            inicio.show.startAnimation(fromButtom)
            inicio.update.startAnimation(fromButtom)
            inicio.add.startAnimation(rotateOpen)
        } else {
            inicio.delate.startAnimation(toButtom)
            inicio.show.startAnimation(toButtom)
            inicio.update.startAnimation(toButtom)
            inicio.add.startAnimation(rotateClose)
        }
    }

    private fun setClicked(clicked: Boolean){
        if (!clicked) {
            inicio.delate.isClickable = true
            inicio.show.isClickable = true
            inicio.update.isClickable = true
        } else {
            inicio.delate.isClickable = false
            inicio.show.isClickable = false
            inicio.update.isClickable = false
        }
    }

    private fun showDelate(titulo: String, mensaje: String){
        val alert = android.app.AlertDialog.Builder(this@tipoActivity)
        alert.setTitle(titulo)
        alert.setMessage(mensaje)
        alert.setCancelable(true)
        alert.show()
    }

    private fun mostrar(userData: String) {
        Toast.makeText(this@tipoActivity, "${userData}", Toast.LENGTH_SHORT).show()
    }
}

