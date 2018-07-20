package com.cursosdedesarrollo.datosandroidkotlin

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Entity
data class Task(@PrimaryKey(autoGenerate = true)
                var id:Int = 0,
                var name:String = "",
                var isDone:Boolean = false)



@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks() : Flowable<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task):Long

    @Query("SELECT * FROM Task where id like :id")
    fun getTaskById(id: Long): Task

    @Update
    fun updateTask(tasks: Task)

    @Delete
    fun deleteTask(tasks: Task)

}

@Database(entities = arrayOf(Task::class),version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}