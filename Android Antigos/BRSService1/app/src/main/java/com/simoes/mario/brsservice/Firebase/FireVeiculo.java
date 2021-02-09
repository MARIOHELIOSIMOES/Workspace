package com.simoes.mario.brsservice.Firebase;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import com.simoes.mario.brsservice.Classes.Constantes;
import com.simoes.mario.brsservice.Classes.Veiculo;


/**
 * Created by Mário on 11/02/2017.
 */

public class FireVeiculo {

    private Veiculo veiculo;
    private List<Veiculo > listVeiculo;
    private DatabaseReference reference;

    public FireVeiculo(){
        veiculo = new Veiculo();
        listVeiculo =new ArrayList<>();
        reference = Constantes.referenceVeiculo;

    }
    public void ClearListVeiculo(){
        this.listVeiculo.clear();
    }
    public void AddVeiculoLista(Veiculo veiculo){
        this.listVeiculo.add(veiculo);
    }


    public void SalvarNovo(Veiculo v){
        try{

            String key = getReference().push().getKey();
            veiculo = new Veiculo(key, v.getTipo(), v.getMarca(), v.getModelo(), v.getCombustivel(), v.getAno(), v.getLitragem(), v.getOleoMotor(), v.getOleoCambio(), v.getCodfOleo(), v.getCodfAr(), v.getCodfArCab(), v.getCodfComb());
            getReference().child(key).setValue(getVeiculo());
            Constantes.referenceMarcas.child(key).setValue(getVeiculo().getMarca());
            Log.d("Novo Veiculo", "Novo veiculo salvo com sucesso: key - " + key);
        }catch (Exception e){
            Log.d("Novo Veiculo", "Metodo SalvarNovo Deu erro!");
        }
    }
    public void Atualizar(Veiculo v){
        try{
            String key = v.getId();
            veiculo = new Veiculo(key, v.getTipo(), v.getMarca(), v.getModelo(), v.getCombustivel(), v.getAno(), v.getLitragem(), v.getOleoMotor(), v.getOleoCambio(), v.getCodfOleo(), v.getCodfAr(), v.getCodfArCab(), v.getCodfComb());
            getReference().child(key).setValue(getVeiculo());
            Constantes.referenceMarcas.child(key).setValue(getVeiculo().getMarca());
            Log.d("Novo Veiculo", "Novo veiculo salvo com sucesso: key - " + key);
        }catch (Exception e){
            Log.d("Novo Veiculo", "Metodo SalvarNovo Deu erro!");
        }
    }
    public void Excluir(Veiculo veiculo){
        try{
            String key = veiculo.getId();
            reference.child(key).removeValue();
            Log.d("FireVeiculo", "Veiculo Removido da base");
        }catch (Exception e){
            Log.d("FireVeiculo", "Metodo Excluir Deu erro!");
        }
    }
    private List<Veiculo> retornarTodosVeiculos(){
       return getListVeiculo();
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<Veiculo> getListVeiculo() {
        return listVeiculo;
    }

    public void setListVeiculo(List<Veiculo> listVeiculo) {
        this.listVeiculo = listVeiculo;
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }
}


