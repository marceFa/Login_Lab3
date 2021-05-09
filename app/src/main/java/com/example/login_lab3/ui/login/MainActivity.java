package com.example.login_lab3.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.login_lab3.R;
import com.example.login_lab3.model.Usuario;
import com.example.login_lab3.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private Button btRegistrar ;
    private Button btLogin;
    private EditText mail;
    private EditText password;
    private TextView msjerror;
    private ViewModelMain viewModelMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();

        viewModelMain.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario!=null) {
                    Intent intent= new Intent(getApplicationContext(), RegistroActivity.class);
                    intent.putExtra("", 1);
                    startActivity(intent);

                }else{
                    msjerror.setText("USUARIO INEXISTENTE Y/O DATOS INCORRECTOS!!!!");
                }

            }
        });

    }
    public void inicializar(){
        btLogin= findViewById(R.id.login);
        btRegistrar = findViewById(R.id.registrar);
        mail=findViewById(R.id.email);
        password=findViewById(R.id.password);
        msjerror= findViewById(R.id.msm);
        viewModelMain = ViewModelProviders.of(this).get(ViewModelMain.class);

    }

    public void Ingresar(View view) {
        String email= mail.getText().toString();
        String pass= password.getText().toString();
        if(!email.isEmpty() && !pass.isEmpty()){
            viewModelMain.Ingresar(this, email, pass);
        }else{
            msjerror.setText("POR FAVOR COMPLETE LOS CAMPOS PARA INGRESAR!");
        }

    }

    public void Registrarse(View view) {
        Intent registrar= new Intent(this, RegistroActivity.class);
        registrar.putExtra("registrarse", 0);
        startActivity(registrar);



    }


}