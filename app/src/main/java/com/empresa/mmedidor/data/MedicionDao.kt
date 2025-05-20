package com.empresa.mmedidor.data
//
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.empresa.mmedidor.data.Medicion


@Dao
interface MedicionDao {
    @Query("SELECT * FROM mediciones ORDER BY fecha DESC")
    fun obtenerTodas(): kotlinx.coroutines.flow.Flow<List<Medicion>>

    @Insert
    suspend fun insertar(medicion: Medicion)
}

