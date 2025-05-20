package com.empresa.mmedidor.data
//
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mediciones")
data class Medicion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: String,
    val valor: Float,
    val fecha: String
)
