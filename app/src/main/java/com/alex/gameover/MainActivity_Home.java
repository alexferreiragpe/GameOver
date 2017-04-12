package com.alex.gameover;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olvia.alexgameover.R;

public class MainActivity_Home extends AppCompatActivity {

    int numerousuario;
    private EditText numero;
    private Button jogar, reiniciar;
    private TextView mensagem, tentativa;
    private int aleatorio, tentativas = 3, cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        numero = (EditText) findViewById(R.id.edtNumeroUsuario);
        jogar = (Button) findViewById(R.id.btnVerificar);
        reiniciar = (Button) findViewById(R.id.btnReiniciar);
        mensagem = (TextView) findViewById(R.id.txtMensagem);
        reiniciar.setEnabled(false);
        aleatorio = gerarAleatorio();
        tentativa = (TextView) findViewById(R.id.txtTentativas);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity_Home.this);


        jogar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                EditText textoVazio = (EditText) findViewById(R.id.edtNumeroUsuario);

                if (textoVazio.getText().toString().equals("")) {
                    mensagem.setText("Digite um Valor para Testar!");
                    limpar();
                } else {
                    numerousuario = Integer.parseInt(numero.getText().toString());
                    if (numerousuario < 1 || numerousuario > 20) {

                        mensagem.setText("Digite um Número Válido!");
                        limpar();
                    } else if (aleatorio == numerousuario) {
                        cont = cont + 1;

                        alertDialog.setTitle("Parabéns...");
                        alertDialog.setMessage("Você Acertou c/ " + cont + " Tentativa(s).\nNúmero sorteado: " + aleatorio);
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        });
                        alertDialog.setIcon(R.drawable.icon);
                        alertDialog.show();
                        numero.setEnabled(false);
                        jogar.setEnabled(false);
                        reiniciar.setEnabled(true);
                        tentativa.setText("");
                        mensagem.setText("");
                        limpar();


                    } else {
                        tentativas = tentativas - 1;
                        cont = cont + 1;
                        String stri = String.valueOf(tentativas);
                        tentativa.setText("Tentativas Restantes: " + stri);
                        if (numerousuario > aleatorio) {
                            mensagem.setText("Tente um Número Menor!");
                        } else if (numerousuario < aleatorio) {
                            mensagem.setText("Tente um Número Maior!");
                        }
                        limpar();
                    }
                }

                if (tentativas == 0) {
                    numero.setEnabled(false);
                    reiniciar.setEnabled(true);
                    jogar.setEnabled(false);
                    alertDialog.setTitle("Perdeu...");
                    alertDialog.setMessage("Acabaram as Tentativas\nNúmero sorteado era " + aleatorio);
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    });
                    alertDialog.setIcon(R.drawable.icon);
                    alertDialog.show();
                    tentativa.setText("");
                    mensagem.setText("");
                    limpar();
                }

            }
        });
        reiniciar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tentativas = 3;
                numero.setEnabled(true);
                reiniciar.setEnabled(false);
                mensagem.setText("");
                aleatorio = gerarAleatorio();
                jogar.setEnabled(true);
                cont = 0;
                limpar();
            }
        });

    }

    public void limpar() {
        numero.setText("");
    }

    private int gerarAleatorio() {
        return (int) (Math.random() * 20 + 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity__home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        //chmar tela sobre
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Sobre", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity_Home.this, MainActivitySobre.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
