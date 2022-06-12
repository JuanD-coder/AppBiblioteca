package com.example.biblioteca.tipoUser

import androidx.room.*

@Dao
interface TipoUserDao {

    @Insert
    suspend fun insert(user:EntitiesFk)

    @Query("delete from EntitiesFk where id_correo =:id")
    suspend fun delate(id:Int)

    @Update
    suspend fun update(user: EntitiesFk)

    @Query("select * from EntitiesFk")
    suspend fun mostar(): List<EntitiesFk>

}