package com.simoes.mario.brsservice.Firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.simoes.mario.brsservice.Classes.Constantes;
import com.simoes.mario.brsservice.Classes.Filtro;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mário on 11/02/2017.
 */

public class FireFiltro {
    Filtro filtro;
    List<Filtro> listFiltros;
    DatabaseReference reference;
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                listFiltros.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d(" OnDataChange ", child.getValue().toString());
                    filtro = child.getValue(Filtro.class);
                    listFiltros.add(filtro);
                }
                Log.d("OnDataChange Ok", "Tamanho da lista de filtros: " + listFiltros.size());

            } catch (Exception e) {
                Log.d("OnDataChange Erro!", "Try - onDataChange erro - " + e.getMessage());

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public FireFiltro() {
        filtro = new Filtro();
        listFiltros = new ArrayList<>();
        reference = Constantes.referenceFiltro;
        reference.addValueEventListener(listener);
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public void SalvarNovo(List<Filtro> filtros) {
        try {

            for (int i = 0; i < filtros.size(); i++) {
                String key = reference.push().getKey();
                filtro = filtros.get(i);
                filtro.setId(key);
                reference.child(key).setValue(filtro);
                Log.d("Novo Filtro", "Novo filtro salvo com sucesso: key - " + key);
            }

        } catch (Exception e) {
            Log.d("Novo Filtro", "Metodo SalvarNovo Deu erro!");
        }
    }

    public void SalvarNovo(Filtro f) {
        try {

            String key = reference.push().getKey();
            filtro = new Filtro(key, f.getCodigo(), f.getTipo(), f.getMarca(), f.getModelo(), f.getValor());
            reference.child(key).setValue(filtro);
            Log.d("Novo Filtro", "Novo filtro salvo com sucesso: key - " + key);
        } catch (Exception e) {
            Log.d("Novo Filtro", "Metodo SalvarNovo Deu erro!");
        }
    }

    public void Atualizar(Filtro f) {
        try {

            String key = f.getId();
            filtro = new Filtro(key, f.getCodigo(), f.getTipo(), f.getMarca(), f.getModelo(), f.getValor());
            reference.child(key).setValue(filtro);

            Log.d("Novo Filtro", "Filtro Atualizado com sucesso: key - " + key);
        } catch (Exception e) {
            Log.d("Novo Filtro", "Metodo Atualizar Deu erro!");
        }
    }

    public List<Filtro> retornarTodosFiltros() {
        return listFiltros;
    }
}
