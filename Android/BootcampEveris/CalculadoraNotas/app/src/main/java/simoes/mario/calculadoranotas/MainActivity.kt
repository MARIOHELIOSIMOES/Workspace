package simoes.mario.calculadoranotas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNota1 =  findViewById<EditText>(R.id.edtNota1)
        val edtNota2 = edtNota2
        val edtFalta = edtFaltas
        val btnCalcular = btnCalcular
        val txvResultado = txvResultado

        btnCalcular.setOnClickListener{
            val nota1 = Integer.parseInt(edtNota1.text.toString())
            val nota2 = Integer.parseInt(edtNota2.text.toString())
            val falta = Integer.parseInt(edtFalta.text.toString())
            val media = (nota1 + nota2) / 2

            if(media>5 && falta<=10){
                txvResultado.setText("Aprovado!!\nNota média: "+media+"\nFaltas: "+falta)
                txvResultado.setTextColor(Color.GREEN)
            }else{
                txvResultado.setText("Reprovado!!\nNota média: "+media+"\nFaltas: "+falta)
                txvResultado.setTextColor(Color.RED)
            }
        }

    }
}