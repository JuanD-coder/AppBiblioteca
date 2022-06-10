package com.example.biblioteca.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {

    @Insert
    suspend fun insert(user:Entidades)

    @Query("delete from Usuarios_table where idenficacion =:id")
    suspend fun delate(id:Int)

    @Update
    suspend fun update(user: Entidades)

    @Query("select * from Usuarios_table")
    suspend fun display(): List<Entidades>

   /* @Query("SELECT * FROM Usuarios_tabl")
    abstract suspend fun mostar(): List<Entidades>

    @Query("SELECT * FROM usuarios_table WHERE idenficacion IN (:userIds)")
    abstract suspend fun cargar(userIds: IntArray): List<Entidades>

    @Query("SELECT * FROM usuarios_table WHERE name  LIKE :first ")
    suspend fun buscar(first: String, last: String): Entidades

    @Insert
    suspend fun insertAll(usuario: Entidades)

    @Query("update usuarios_table set name =:name where idenficacion=:id")
    abstract suspend fun actualizarUsuarios(name:String,id:Int)

    @Query("delete from usuarios_table where idenficacion=:id")
    suspend fun eliminar(id: Int)*/

 }