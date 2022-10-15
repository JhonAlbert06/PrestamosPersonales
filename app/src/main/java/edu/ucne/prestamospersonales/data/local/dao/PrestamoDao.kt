package edu.ucne.prestamospersonales.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.prestamospersonales.data.local.models.Prestamo
import kotlinx.coroutines.flow.Flow

@Dao
interface PrestamoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamo: Prestamo)

    @Delete
    suspend fun delete(prestamo: Prestamo)

    @Query("SELECT * FROM Prestamos")
    fun getAll(): Flow<List<Prestamo>>

    @Query("SELECT * FROM prestamos WHERE prestamoId = :id")
    suspend fun getById(id:Int): Prestamo?

   /* @Query("SELECT Personas.nombres FROM Personas JOIN Prestamos ON personaId = Prestamos.personaId WHERE Prestamos.prestamoId= :id")
    suspend fun findPersona(id:Int): String?*/
}