package simoes.mario.controlesalarial.Firebase;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import simoes.mario.controlesalarial.constantes.Constantes;
import simoes.mario.controlesalarial.modelos.Despesas;
import simoes.mario.controlesalarial.modelos.Salario;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mario on 13/07/2017.
 */

public class FireDespesas {

    private Despesas despesa;
    private List<Despesas> despesasList;
    private DatabaseReference reference;

    public FireDespesas(){
        despesa = new Despesas();
        despesasList = new ArrayList<>();
        reference = Constantes.referenceDespesa;
        reference.addValueEventListener(despesaslistener);
    }

    private ValueEventListener despesaslistener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                despesasList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    despesa = child.getValue(Despesas.class);
                    despesasList.add(despesa);
                }
                Log.d("FireDespesa", "Tamanho da lista de despesas: " + despesasList.size());
                //   progressDialog.dismiss();
            } catch (Exception e) {
                Log.d("FireDespsa!", "Erro no DespesasListener , message: " + e.getMessage());
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public void ClearListDespesas(){
        this.despesasList.clear();
    }

    public void AddDespesaLista(Despesas d){
        this.despesasList.add(d);
    }

    public void SalvarNovo(Despesas d){
        try{

            String key = getReference().push().getKey();
            despesa = new Despesas(key, d.getNome(), d.getValor(), d.getId_mes());
            getReference().child(key).setValue(getDespesa());
            Log.d("FireDespesas", "Novo despesa salva com sucesso: key - " + key);
        }catch (Exception e){
            Log.d("FireDespesas", "Metodo SalvarNovo Deu erro!");
        }
    }

    public void Atualizar(Despesas d){
        try{
            String key = d.getId();
            despesa = new Despesas(key, d.getNome(), d.getValor(), d.getId_mes());
            getReference().child(key).setValue(getDespesa());
            Log.d("FireDespesas", "Atualização de despesa salvo com sucesso: key - " + key);
        }catch (Exception e){
            Log.d("FireDespesas", "Metodo Atualizar Deu erro!");
        }
    }
    public void Excluir(Despesas d){
        try{
            String key = d.getId();
            reference.child(key).removeValue();
            Log.d("FireDespesas", "Despesa Removido da base, key: "+ key);
        }catch (Exception e){
            Log.d("FireDespesas", "Metodo Excluir Deu erro!");
        }
    }

    public Despesas getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesas d) {
        this.despesa = d;
    }

    public List<Despesas> getListDespesas() {
        return despesasList;
    }

    public List<Despesas> getListDespesasPorID(String id) {

        List<Despesas> lista = new ArrayList<>();

        for(int i=0; i< despesasList.size(); i++){
            String idLista = despesasList.get(i).getId_mes();
            if(idLista.equals(id)){
                lista.add(despesasList.get(i));
            }
        }
        return lista;
    }
    public double getTotalDespesasPorID(String id){
        double total = 0;
        List<Despesas> lista = new ArrayList<>();
        lista = getListDespesasPorID(id);

        for (int i=0; i<lista.size(); i++){
            total +=lista.get(i).getValor();
        }
        return  total;
    }

    public void setListDespesas(List<Despesas> listDespesas) {
        this.despesasList = listDespesas;
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }



}
