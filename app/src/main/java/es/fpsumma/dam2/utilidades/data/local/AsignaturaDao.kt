package es.fpsumma.dam2.utilidades.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import es.fpsumma.dam2.utilidades.model.Asignatura
import kotlinx.coroutines.flow.Flow

@Dao
interface AsignaturaDao {

    //Inserta una asignatura en la tabla y si hay un conflicto no se inserta y da error
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (asignatura: Asignatura)

    //Actualiza una asignatura que ya existe y tiene el mismo id. Cambia todos los campos de la Base de Datos
    @Update
    suspend fun update(asignatura: Asignatura)

    //Borra la asignatura entera de la Base de Datos
    @Delete
    suspend fun delete(asignatura: Asignatura)

    //Se mostrará la asignatura que tenga el id que pongamos en el parámetro
    @Query ("SELECT * from asignaturas WHERE id = :id")
    fun getAsignatura(id: Int): Flow<Asignatura>

    //Se mostrará todas las asignaturas
    @Query ("SELECT * from asignaturas")
    fun getAllAsignaturas(): Flow<List<Asignatura>>
}