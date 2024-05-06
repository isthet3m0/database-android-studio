package com.example.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etUserid, edUser, edPass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserid=(EditText) findViewById(R.id.editTextTextId);
        edUser = (EditText)findViewById(R.id.editTextTextPersonas);
        edPass=(EditText) findViewById(R.id.editTextTextPassword);



    }
    public void next(View v){
        Intent next=new Intent(this,segundaPantalla.class);
        startActivity(next);
    }
    public void alta (View view)
    {
        AdminOpenHelper ad = new AdminOpenHelper(this, "bdusuario",null,1);
        SQLiteDatabase bd = ad.getWritableDatabase();

        String id = etUserid.getText().toString();
        String user = edUser.getText().toString();
        String pass = edPass.getText().toString();

        ContentValues reg = new ContentValues();
        reg.put("id",id);
        reg.put("user",user);
        reg.put("pass",pass);

        bd.insert("usuario", null,reg);
        bd.close();
        etUserid.setText("");
        edUser.setText("");
        edPass.setText("");

        Toast.makeText(this, "Se insertaron los datos", Toast.LENGTH_LONG).show();
    }
    public void Eliminar(View view)
    {
        AdminOpenHelper ad = new AdminOpenHelper(this,"bdusuario",null,1);
        SQLiteDatabase bd = ad.getWritableDatabase();

        String id = etUserid.getText().toString();

        if (!id.isEmpty())
        {
            int resultado = bd.delete("usuario","id="+id, null);
            bd.close();

            etUserid.setText("");
            edUser.setText("");
            edPass.setText("");

            if (resultado == 1)
            {
                Toast.makeText(this, "Se elimino el usuario", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(this, "usuario no encontrado", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "ingresa el id", Toast.LENGTH_LONG).show();
    }
    public void Consultas(View view)
    {
        AdminOpenHelper ad = new AdminOpenHelper(this,"bdusuario",null,1);
        SQLiteDatabase bd = ad.getWritableDatabase();

        String id = etUserid.getText().toString();

        Cursor fila = bd.rawQuery("SELECT user, pass FROM usuario WHERE id="+id,null);
        if (fila.moveToFirst())
        {
            edUser.setText(fila.getString(0));
            edPass.setText(fila.getString(1));
        }
        else
            Toast.makeText(this,"No existe este usuario papu", Toast.LENGTH_SHORT).show();
        bd.close();
    }
    public void Consulta(View view)
    {
        AdminOpenHelper ad = new AdminOpenHelper(this,"bdusuario",null,1);
        SQLiteDatabase bd = ad.getWritableDatabase();

        String id = etUserid.getText().toString();

        Cursor fila = bd.rawQuery("SELECT user, pass FROM usuario WHERE id="+id,null);
        if (edUser.getText().toString().isEmpty() || edPass.getText().toString().isEmpty() || etUserid.getText().toString().isEmpty()) {
            Toast.makeText(this, "No se pudo crack", Toast.LENGTH_LONG).show();
            return;
        }
        edUser.setText(fila.getString(0));
        edPass.setText(fila.getString(1));
    }
    public void Modificar (View view)
    {
        AdminOpenHelper ad = new AdminOpenHelper(this, "bdusuario",null,1);
        SQLiteDatabase bd = ad.getWritableDatabase();

        String id = etUserid.getText().toString();
        String user = edUser.getText().toString();
        String pass = edPass.getText().toString();

        if (!id.isEmpty() && !user.isEmpty() && !pass.isEmpty())
        {
            ContentValues reg = new ContentValues();

            reg.put("id",id);
            reg.put("user",user);
            reg.put("pass",pass);

            int res = bd.update("usuario", reg, "id="+id, null);
            bd.close();
            if (res == 1)
            {
                Toast.makeText(this,"Se modifico correctament", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this,"No existe este usuario", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this,"Se deben llenar los campos", Toast.LENGTH_SHORT).show();
    }

    public void Limpiar (View view){
        etUserid.setText("");
        edUser.setText("");
        edPass.setText("");
        etUserid.requestFocus();
    }
}
