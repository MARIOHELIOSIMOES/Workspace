package simoes.mario.todo.datasource

import simoes.mario.todo.model.Task

object TaskDataSoucer{
    private val list = arrayListOf<Task>()

    fun getList() = list

    fun insertTask(task: Task){
        list.add(task.copy(id= list.size+1))
    }
}