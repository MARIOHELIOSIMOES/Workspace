package simoes.mario.bankdio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvList: RecyclerView
    private var adapter = MenuItemAdaptar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciaViews()
        setItensLista()
    }

    private fun iniciaViews(){
        rvList = findViewById<RecyclerView>(R.id.rvList)
        rvList.adapter = adapter
        rvList.layoutManager = GridLayoutManager(this, 2)
    }

    private fun setItensLista(){
        adapter.setItensList(arrayListOf(
                MenuItemModel("Cartões"),
                MenuItemModel("Meus Comprovantes"),
                MenuItemModel("Investimentos"),
                MenuItemModel("Portabilidade"),
                MenuItemModel("Poupança")
        ))
    }
}