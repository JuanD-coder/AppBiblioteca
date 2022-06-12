package com.example.biblioteca.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {

    @Insert
    suspend fun insert(user:Entidades)

    @Query("delete from Usuarios_table where id =:id")
    suspend fun delate(id:Int)

    @Update
    suspend fun update(user: Entidades)

    @Query("select * from Usuarios_table")
    suspend fun display(): List<Entidades>


 }