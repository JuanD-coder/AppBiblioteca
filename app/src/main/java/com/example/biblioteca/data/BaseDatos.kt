package com.example.biblioteca.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.biblioteca.data.dao.Dao
import com.example.biblioteca.data.dao.Entidades


@Database(entities = [Entidades::class], version = 1)
 abstract class BaseDatos : RoomDatabase() {

    abstract fun usuarioDao(): Dao
    companion object{
        var INSTANCE: BaseDatos?=null
        fun getInstance(context: Context): BaseDatos
        {
            if(INSTANCE ==null)
            {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, BaseDatos::class.java, "usuario.db").build()
            }
            return INSTANCE!!
        }
    }
 }