package es.fpsumma.dam2.utilidades.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import es.fpsumma.dam2.utilidades.data.local.AppDatabase
import es.fpsumma.dam2.utilidades.model.Asignatura
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AsignaturasViewModel(app: Application) : AndroidViewModel(app) {
    //Se crea la base de datos que en este caso es asignaturas.db
    private val db = Room.databaseBuilder(
        app, AppDatabase::class.java, "asignaturas.db"
    ).fallbackToDestructiveMigration(false).build()

    //Se crea una variable que la igualamos al dao que nos queramos referir en este caso al de asignaturaDao
    private val dao = db.asignaturaDao()

    //Lo que hace es que cuando cambia la Base de Datos, se actualiza
    val asignaturas: StateFlow<List<Asignatura>> =
        dao.getAllAsignaturas().stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    // Función que crea la asignatura
    fun addAsignatura(asignatura: String, trimestre: String, nota: Double) = viewModelScope.launch {
        dao.insert(Asignatura(asignatura = asignatura, trimestre = trimestre, nota = nota))
    }

    //Función que borra la asignatura
    fun deleteAsignatura(asignatura: Asignatura) = viewModelScope.launch {
        dao.delete(asignatura)
    }
}