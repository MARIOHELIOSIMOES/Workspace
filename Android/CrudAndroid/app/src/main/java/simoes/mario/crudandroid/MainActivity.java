package simoes.mario.crudandroid;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

import simoes.mario.crudandroid.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    EditText edtnome, edtemail;
    ListView listdados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ArrayList<Pessoa> arrayListPessoa = new ArrayList<Pessoa>();
    private ArrayAdapter<Pessoa> arrayAdapter;

    private Pessoa pessoaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtemail = (EditText)findViewById(R.id.edtemail);
        edtnome = (EditText) findViewById(R.id.edtnome);
        listdados = (ListView)findViewById(R.id.listdados);


        inicializarFirebase();
        eventoDataBase();

        listdados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pessoaSelecionada = (Pessoa) parent.getItemAtPosition(position);
                edtnome.setText(pessoaSelecionada.getNome());
                edtemail.setText(pessoaSelecionada.getEmail());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void eventoDataBase() {
        databaseReference.child("Pessoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayListPessoa.clear();
                for(DataSnapshot data: snapshot.getChildren()){
                    Pessoa p = data.getValue(Pessoa.class);
                    arrayListPessoa.add(p);
                }
                arrayAdapter = new ArrayAdapter<Pessoa>(MainActivity.this, android.R.layout.simple_list_item_1, arrayListPessoa);
                listdados.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Pessoa p =new Pessoa();
        try{
            switch (id){
                case R.id.menu_novo:
                    p.setUid(UUID.randomUUID().toString());
                    p.setNome(edtnome.getText().toString());
                    p.setEmail(edtemail.getText().toString());
                    databaseReference.child("Pessoa").child(p.getUid()).setValue(p);
                    limparCampos();
                    break;

                case R.id.menu_atualizar:
                    p.setUid(pessoaSelecionada.getUid());
                    p.setNome(edtnome.getText().toString());
                    p.setEmail(edtemail.getText().toString());
                    databaseReference.child("Pessoa").child(pessoaSelecionada.getUid()).setValue(p);
                    limparCampos();
                    break;

                case R.id.menu_deletar:
                    p.setUid(pessoaSelecionada.getUid());
                    databaseReference.child("Pessoa").child(pessoaSelecionada.getUid()).removeValue();
                    break;
            }
        }catch (Exception e){
           Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void limparCampos() {
        edtnome.setText("");
        edtemail.setText("");
    }
}