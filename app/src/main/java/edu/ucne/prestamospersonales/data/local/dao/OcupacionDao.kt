package edu.ucne.prestamospersonales.data.local.dao

import androidx.room.*
import edu.ucne.prestamospersonales.domain.model.Ocupacion
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionDao {

    //Insertar un Usuario y en caso de que exista, editarlo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserOcupacion(Ocupacion: Ocupacion)

    //Eliminar un usuario
    @Delete
    suspend fun deleteOcupacion(Ocupacion: Ocupacion)

    //Sacar todas las Ocupaciones de la tabla Ocupaciones
    @Query("SELECT * FROM Ocupacion")
    fun getOcupaciones(): Flow<List<Ocupacion>>

    //Sacar Ocupacion por ID
    @Query("SELECT * FROM Ocupacion WHERE OcupacionId = :id")
    suspend fun getOcupacionesById(id:Int): Ocupacion?

}