package com.cursosdedesarrollo.datosandroidkotlin


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.util.Log


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity() {

    lateinit var tasks: MutableList<Task>
    lateinit var taskDao: TaskDao
    lateinit var adapter : TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        tasks = ArrayList()
        taskDao = (application as Aplicacion).database?.taskDao()!!
        //getTasks()
        //insertTask(Task(id = 0,name = "Tarea"))
        getTasks()
        btnAddTask.setOnClickListener {
            addTask(Task(name = etTask.text.toString()))}
        /*
        (application as Aplicacion).addPerson("Nombre","Apellido")
        val flowable=(application as Aplicacion).registerAllPersonListener()
        flowable ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfPeople ->
                    //view.personTableUpdated(listOfPeople)
                    Log.d("app:","$listOfPeople")
                }
        */
    }



    fun getTasks() {
        doAsync {
            val tasks = taskDao.getAllTasks()
            tasks.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { listOfTasks ->
                        //view.personTableUpdated(listOfPeople)
                        Log.d("app:","$listOfTasks")
                        setUpRecyclerView(listOfTasks)
                    }
        }
    }
    fun addTask(task:Task){
        doAsync {
            val id = taskDao.insertTask(task)
            val recoveryTask = taskDao.getTaskById(id)

            tasks.add(recoveryTask)
            adapter.notifyItemInserted(tasks.size)

            runOnUiThread {
                etTask.text.clear()
            }
        }
    }

    fun setUpRecyclerView(tasks: List<Task>) {
        adapter = TasksAdapter(tasks,
                {
                    doAsync {
                        it.isDone=!(it.isDone)
                        taskDao.updateTask(it)
                    }

                },
                {
                    doAsync {
                        taskDao.deleteTask(it)
                    }

                })

        rvTask.setHasFixedSize(true)
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = adapter
    }
    inline fun doAsync(crossinline f: () -> Unit) {
        Thread({ f() }).start()
    }
}
