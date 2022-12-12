package com.android.ayalas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.ayalas.util.ConexionBD;

import java.sql.PreparedStatement;

public class Agregarusuario extends AppCompatActivity {

    private EditText etnombre, etapellidos, etusuario, etcontra;
    private Button btn_guardar;

    ConexionBD conexionBD = new ConexionBD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarusuario);

        etnombre = findViewById(R.id.etnombres);
        etapellidos = findViewById(R.id.etApellidos);
        etusuario = findViewById(R.id.etusuario);
        etcontra = findViewById(R.id.etcontraseña);
        btn_guardar = findViewById(R.id.btnagregarusuario);


   btn_guardar.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {guardar_usuario();
       }
   });

    }



    public void guardar_usuario(){
        try{
            PreparedStatement stm= conexionBD.conexionBD().prepareStatement( "INSERT INTO Person Values(?,?,?,?)");
            stm.setString(1,etnombre.getText().toString());
            stm.setString(2,etapellidos.getText().toString());
            stm.setString(3,etusuario.getText().toString());
            stm.setString(4,etcontra.getText().toString());
            stm.executeUpdate();
            Toast.makeText(getApplicationContext(), " Usuario registrado exitosamente...",Toast.LENGTH_SHORT).show();

            new Handler(Looper.getMainLooper()).postDelayed(()->{
                Intent i = new Intent(Agregarusuario.this,LoginActivity.class);
                startActivity(i);
                finish();
            },2500);

            etnombre.setText("");
            etapellidos.setText("");
            etusuario.setText("");
            etcontra.setText("");
        }

        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }










}