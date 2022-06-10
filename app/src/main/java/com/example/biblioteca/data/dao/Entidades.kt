package com.example.biblioteca.data.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

 @Entity (tableName = "Usuarios_table")
data class Entidades(
      @PrimaryKey val idenficacion: Int?,
      @ColumnInfo(name = "name") val name: String?,
      @ColumnInfo(name = "correo ") val correo: String?,
      @ColumnInfo(name = "clave") val clave: String?,

      )
