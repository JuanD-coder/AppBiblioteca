package com.example.biblioteca.tipoUser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class EntitiesFk(
    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "ID_Correo") var id_correo: Int?,
    @ColumnInfo(name = "tipoUser") var tipoUser: String?,
 )