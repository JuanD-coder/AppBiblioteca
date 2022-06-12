package com.example.biblioteca.data.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.biblioteca.tipoUser.EntitiesFk

@Entity (tableName = "Usuarios_table",
      foreignKeys = [ForeignKey(entity = EntitiesFk::class, parentColumns = ["ID_Correo"], childColumns = ["tipoUsuario"]) ]
        )
data class Entidades(
      @PrimaryKey @ColumnInfo(name = "id") val idenficacion: Int?,
      @ColumnInfo(name = "name") val name: String?,
      @ColumnInfo(name = "telefono") val telefono: String?,
      @ColumnInfo(name = "fechaNacimiento") val fecha: String?,
      @ColumnInfo(name = "tipoUsuario") val tipo: String?,
      )
