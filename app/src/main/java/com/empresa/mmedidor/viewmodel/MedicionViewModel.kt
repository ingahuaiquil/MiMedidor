package com.empresa.mmedidor.viewmodel
//
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.mmedidor.data.AppDatabase
import com.empresa.mmedidor.data.Medicion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.room.Room

class MedicionViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "mediciones-db"
    ).build()

    private val dao = db.medicionDao()
    val mediciones: StateFlow<List<Medicion>> = dao.obtenerTodas().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    fun agregarMedicion(tipo: String, valor: Float, fecha: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertar(Medicion(tipo = tipo, valor = valor, fecha = fecha))
        }
    }
}
