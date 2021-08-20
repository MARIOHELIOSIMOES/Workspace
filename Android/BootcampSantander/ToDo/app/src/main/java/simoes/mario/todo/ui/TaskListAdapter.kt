package simoes.mario.todo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import simoes.mario.todo.R
import simoes.mario.todo.databinding.LayoutItemBinding
import simoes.mario.todo.model.Task
import simoes.mario.todo.ui.TaskListAdapter.TaskViewHolder

class TaskListAdapter: ListAdapter<Task, TaskViewHolder>(DiffCallBack()){

    var listenerEdit: (Task) -> Unit ={}
    var listenerDelete: (Task) -> Unit= {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindind = LayoutItemBinding.inflate(inflater, parent, false)
        return TaskViewHolder(bindind)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class TaskViewHolder(
            private val bindind: LayoutItemBinding
            ): RecyclerView.ViewHolder(bindind.root){

                fun bind(item: Task){
                    bindind.tvTitle.text = item.title
                    bindind.date.text = item.date + " " + item.hour
                    bindind.ivMore.setOnClickListener {
                        showPopup(item)
                    }
                }
                private fun showPopup(item: Task){
                    val ivMore = bindind.ivMore
                    val popupMenu = PopupMenu(ivMore.context, ivMore)
                    popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.action_edit -> listenerEdit(item)
                            R.id.action_delete -> listenerDelete(item)
                        }
                        return@setOnMenuItemClickListener true
                    }
                    popupMenu.show()

                }
    }
}
class DiffCallBack: DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem==newItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
}