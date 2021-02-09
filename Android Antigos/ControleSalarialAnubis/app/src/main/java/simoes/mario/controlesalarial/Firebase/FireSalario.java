package simoes.mario.controlesalarial.Firebase;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import simoes.mario.controlesalarial.constantes.Constantes;
import simoes.mario.controlesalarial.modelos.Salario;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by mario on 13/07/2017.
 */

public class FireSalario {

    private Salario salario;
    private List<Salario> salarioList;
    private DatabaseReference reference;

    public FireSalario(){
        salario = new Salario();
        salarioList = new ArrayList<>();
        reference = Constantes.referenceSalario;
        reference.addValueEventListener(salariolistener);
    }

    private ValueEventListener salariolistener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                salarioList.clear();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" FireSalario ", "onDataChange :"+ child.getValue().toString());
                    salario = child.getValue(Salario.class);
                    salarioList.add(salario);
                }
                Log.d("FireSalario", "Tamanho da lista de salários: "+salarioList.size());
                //   progressDialog.dismiss();
            }catch(Exception e){
                Log.d("FireSalario!","Erro no Salariolistener , message: " + e.getMessage());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public Salario Procurar(int mes, int ano){
        salario = new Salario();

        for(int i=0; i<salarioList.size(); i++){
            if(salarioList.get(i).getMes()==mes && salarioList.get(i).getAno()==ano){
                return salarioList.get(i);
            }
        }
        return salario;
    }

    public void ClearListSalario(){
        this.salarioList.clear();
    }

    public void AddSalarioLista(Salario s){
        this.salarioList.add(s);
    }

    public void SalvarNovo(Salario s){
        try{

            String key = getReference().push().getKey();
            salario = new Salario(key,s.getAno(), s.getMes(),s.getValor());
            getReference().child(key).setValue(getSalario());
            Log.d("FireSalario", "Novo salario salvo com sucesso: key - " + key);
        }catch (Exception e){
            Log.d("FireSalario", "Metodo SalvarNovo Deu erro!");
        }
    }

    public void Atualizar(Salario s){
        try{
            String key = s.getId();
            salario = new Salario(key, s.getAno(), s.getMes(), s.getValor());
            getReference().child(key).setValue(getSalario());
            Log.d("FireSalario", "Atualização de salario salvo com sucesso: key - " + key);
        }catch (Exception e){
            Log.d("FireSalario", "Metodo Atualizar Deu erro!");
        }
    }

    public void Excluir(Salario s){
        try{
            String key = s.getId();
            reference.child(key).removeValue();
            Log.d("FireSalario", "Salario Removido da base, key: "+ key);
        }catch (Exception e){
            Log.d("FireSalario", "Metodo Excluir Deu erro!");
        }
    }

    public Salario getSalario() {
        return salario;
    }

    public Salario getSalarioPorID(String id){
        salario = new Salario();
        for (int i = 0; i<salarioList.size(); i++){
            if(salarioList.get(i).getId().equals(id)){
                salario = salarioList.get(i);
            }
        }
        return salario;
    }

    public void setSalario(Salario s) {
        this.salario = s;
    }

    public List<Salario> getListSalario() {
        return salarioList;
    }

    public void setListVeiculo(List<Salario> listSalario) {
        this.salarioList = listSalario;
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }

}
