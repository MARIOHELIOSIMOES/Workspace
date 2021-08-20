package simoes.mario.todo.datasource

import simoes.mario.todo.model.Task

object TaskDataSoucer{
    private val list = arrayListOf<Task>(Task("titulo1","19:00", "18/08/2021",1), Task("titulo2","18:00", "17/08/2021",2))

    fun getList() = list

    fun insertTask(task: Task){
        if(task.id==0){
            list.add(task.copy(id= list.size+1))
        }else{
            list.remove((task))
            list.add(task)
        }
    }

    fun findById(taskId: Int) = list.find {
            it.id == taskId
    }

    fun deleteTask(task: Task) {
        list.remove(task)
    }
}