package com.example.barbearia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TelaConsulta extends AppCompatActivity {

    EditText et_consulta,et_servico;
    Button btn_anterior,btn_proximo,btn_voltar;

    SQLiteDatabase db=null;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);

        et_consulta=(EditText)findViewById(R.id.et_consulta);
        et_servico=(EditText)findViewById(R.id.et_servico);
        btn_anterior=(Button)findViewById(R.id.btn_anterior);
        btn_proximo=(Button)findViewById(R.id.btn_proximo);
        btn_voltar=(Button) findViewById(R.id.btn_voltar_consulta);

        buscarDados();




    }
    public void fechar(View v) {
        this.finish();
    }
    public void abrirBanco(){
        try{
            db=openOrCreateDatabase("bancoAgenda",MODE_PRIVATE,null);
        } catch (Exception ex){
            msg("Erro ao abrir ou criar banco de dados");
        }

    }
    public void fecharDB(){
        db.close();
    }

    public void buscarDados() {
        abrirBanco();
        cursor = db.query("contatos",
                new String[]{"consulta", "servico"},
                null,
                null,
                null,
                null,
                null,
                null

                );
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            mostrarDados();

        } else {
            msg("Nenhum registro encontrado");

        }
    }
 /*
        public void proximoRegistro(View v) {
            cursor.moveToNext();
        }
        public void RegistroAnterior(View v) {
        cursor.moveToPrevious();
        } */


        public void mostrarDados(){
        et_consulta.setText(cursor.getString(cursor.getColumnIndex("consulta")));
            et_consulta.setText(cursor.getString(cursor.getColumnIndex("servico")));
        }

    public void  msg(String txt){
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setMessage(txt);
        adb.setNeutralButton("OK",null);
        adb.show();
    }


}