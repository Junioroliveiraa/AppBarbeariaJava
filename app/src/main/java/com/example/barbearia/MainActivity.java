package com.example.barbearia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.database.sqlite.SQLiteDatabase;//Banco de dados


public class MainActivity extends AppCompatActivity {
    EditText et_nome, et_servico;
    Button btn_agendar,btn_consultar,btn_fechar;

    SQLiteDatabase db=null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nome=(EditText)  findViewById(R.id.et_nome);
        et_servico=(EditText)  findViewById(R.id.et_servico);
        btn_agendar=(Button) findViewById(R.id.btn_agendar);
        btn_consultar=(Button) findViewById(R.id.btn_consultar);
        btn_fechar=(Button) findViewById(R.id.btn_fechar);

        AbrirBanco();
        abrirOuCriarTabela();
        fecharDB();
    }

    public void AbrirBanco (){
        try{
            db=openOrCreateDatabase("bancoAgenda",MODE_PRIVATE,null);
        } catch (Exception ex){
            msg("Erro ao abrir ou criar banco de dados");
        }
    }

    public void abrirOuCriarTabela(){
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos (id INTEGER PRIMARY KEY,nome TEXT, servico TEXT);");
        }catch (Exception ex){
            msg("Erro ao criar tabela");
        }
    }

    public void fecharDB(){
      db.close();
    }

    public void inserirRegistro( View v){

        String st_nome,st_servico;
        st_nome=et_nome.getText().toString();
        st_servico=et_servico.getText().toString();
        if(st_nome=="" || st_servico==""){
            msg("Campos não podem estar vazios");
            return;
        }
        AbrirBanco();
        try {
            db.execSQL("INSERT INTO contatos (nome,servico) VALUES ('" +st_nome+ "','" +st_servico+ "')");
        }catch (Exception ex){
            msg("Registro inserido com sucesso");

        }

        fecharDB();
        et_nome.setText(null);
        et_servico.setText(null);
    }

    public void abrir_tela_consulta(View v) {
        Intent it_tela_consulta=new Intent(this,TelaConsulta.class);
        startActivity(it_tela_consulta);

}

    public void fechar(View v) {
        this.finish();

    }
    public void  msg(String txt){
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setMessage(txt);
        adb.setNeutralButton("OK",null);
        adb.show();
    }

}