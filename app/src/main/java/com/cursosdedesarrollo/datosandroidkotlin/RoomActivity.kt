package com.cursosdedesarrollo.datosandroidkotlin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RoomActivity : AppCompatActivity() {

    private var rvTask: RecyclerView? = null
    private var etTask: EditText? = null
    private var btnAddTask: Button? = null
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
        btnAddTask = findViewById<Button>(R.id.btnAddTask)
        etTask = findViewById<EditText>(R.id.etTask)
        rvTask = findViewById<RecyclerView>(R.id.rvTask)
        btnAddTask?.setOnClickListener {
            addTask(Task(name = etTask?.text.toString()))}
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
                etTask?.text?.clear()
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

        rvTask?.setHasFixedSize(true)
        rvTask?.layoutManager = LinearLayoutManager(this)
        rvTask?.adapter = adapter
    }
    inline fun doAsync(crossinline f: () -> Unit) {
        Thread({ f() }).start()
    }
}
