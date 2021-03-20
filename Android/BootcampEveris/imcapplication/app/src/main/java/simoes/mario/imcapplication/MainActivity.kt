package simoes.mario.imcapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners(){
        edtAltura.doAfterTextChanged { text ->
            Toast.makeText(this,text.toString(),Toast.LENGTH_LONG).show()
        }
        edtPeso.doOnTextChanged { text, _, _, _ ->

        }
        btnCalcular.setOnClickListener{
            calcularIMC(edtAltura.text.toString(), edtPeso.text.toString())
        }
    }
    private fun calcularIMC(altura: String, peso: String){
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()
        if(peso!=null && altura!=null){
            val imc = peso / (altura *  altura)
            txtTitulo.text = "Seu IMC: \n" + imc
        }
    }

}