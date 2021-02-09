package com.simoes.mario.brsservice.Firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.simoes.mario.brsservice.Classes.Constantes;
import com.simoes.mario.brsservice.Classes.Oleo;

/**
 * Created by Mário on 11/02/2017.
 */

public class FireOleo {
    private Oleo oleo;
    private List<Oleo> listOleo;
    private DatabaseReference reference;

    public FireOleo(){
        oleo=new Oleo();
        listOleo= new ArrayList<>();
        reference =Constantes.referenceOleo;
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                getListOleo().clear();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" OnDataChange ", child.getValue().toString());
                    setOleo(child.getValue(Oleo.class));
                    getListOleo().add(getOleo());
                }
                Log.d("OnDataChange Ok", "Tamanho da lista de filtros: "+ getListOleo().size());

            }catch(Exception e){
                Log.d("OnDataChange Erro!","Try - onDataChange erro - " + e.getMessage());

            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public void SalvarNovo(List<Oleo> oleos){
        try{

            for(int i = 0; i< oleos.size(); i++){
                String key = getReference().push().getKey();
                oleo =oleos.get(i);
                oleo.setId(key);
                reference.child(key).setValue(oleo);
                Log.d("Novo Filtro", "Novo oleo salvo com sucesso: key - " + key);
            }

        }catch (Exception e){
            Log.d("Novo Filtro", "Metodo SalvarNovo Deu erro!");
        }
    }
    public void ClearListOleo(){
        listOleo.clear();
    }
    public void SalvarNovo(Oleo o){
        try{

            String key = getReference().push().getKey();
            oleo = new Oleo(key, o.getTipo(), o.getMarca(), o.getModelo(), o.getNome(), o.getCombustivel(), o.getValor());
            reference.child(key).setValue(getOleo());
            Log.d("Novo Oleo", "Novo oleo salvo com sucesso: key - " + key);
        }catch (Exception e){
            Log.d("Novo Oleo", "Metodo SalvarNovo Deu erro!");
        }

    }
    public void Atualizar(Oleo o){
        try{

            String key = o.getId();
            oleo = new Oleo(key, o.getTipo(), o.getMarca(), o.getModelo(), o.getNome(), o.getCombustivel(), o.getValor());
            reference.child(key).setValue(oleo);
            Log.d("FireOloeo", "Oleo Atualizado com sucesso: key - " + key);
        }catch (Exception e){
            Log.d("FireOloeo", "Metodo Atualizar Deu erro!");
        }
    }
    public List<Oleo> retornarTodosOleos(){
        return getListOleo();
    }

    public Oleo getOleo() {
        return oleo;
    }

    public void setOleo(Oleo oleo) {
        this.oleo = oleo;
    }

    public List<Oleo> getListOleo() {
        return listOleo;
    }

    public void setListOleo(List<Oleo> listOleo) {
        this.listOleo = listOleo;
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }
}
