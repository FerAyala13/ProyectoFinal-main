package com.android.ayalas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.ayalas.util.ConexionBD;
import com.android.emerson.R;

import java.sql.ResultSet;
import java.sql.Statement;

public class consultarEstudiantes extends AppCompatActivity {
    EditText nomconsultar;
    TextView dui,nom, telefono, sexo, direccion;
    ImageButton buscar;

    ConexionBD conexionBD = new ConexionBD();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultarclientes);

        nomconsultar = (EditText) findViewById(R.id.txtconsultar);
        nom = (TextView) findViewById(R.id.txtnombre);
        dui = (TextView) findViewById(R.id.txtdni);
        telefono = (TextView) findViewById(R.id.txttelefono);
        sexo = (TextView) findViewById(R.id.txtsexo);
        direccion = (TextView) findViewById(R.id.txtdir);
        buscar = findViewById(R.id.btnbuscar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarclientes();
            }
        });
    }


    public void consultarclientes() {
        try {
            Statement stm = conexionBD.conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM  tbl_cliente WHERE dni ='" + nomconsultar.getText().toString() + "'");

            if (rs.next()) {

                dui.setText("DNI del Cliente : "+rs.getString(1));
                nom.setText("Nombre del Cliente: "+rs.getString(2));
                telefono.setText("Telefono del Cliente: "+rs.getString(3));
                sexo.setText("Sexo del Cliente: "+rs.getString(4));
                direccion.setText("Direccion del Cliente: "+rs.getString(6));

            }else{
                Toast.makeText(getApplicationContext(),"El cliente no existe" ,Toast.LENGTH_SHORT).show();
            }
            nomconsultar.setText(" ");


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
