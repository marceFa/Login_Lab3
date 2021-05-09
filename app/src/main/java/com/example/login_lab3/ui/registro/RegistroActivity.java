package com.example.login_lab3.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.login_lab3.R;
import com.example.login_lab3.model.Usuario;
import com.example.login_lab3.ui.login.MainActivity;

public class RegistroActivity extends AppCompatActivity {
    EditText dni;
    EditText nombre;
    EditText apellido;
    EditText email;
    EditText password;
    TextView msm;
    ViewModelRegistro viewModelRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inicializar();

        viewModelRegistro.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                dni.setText(usuario.getDni() + "");
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getMail());
                password.setText(usuario.getPassword());

            }
        });
        if (getIntent().getExtras().getInt("") == 1) {
            viewModelRegistro.mostrar(getApplicationContext());

        }
    }

    public void inicializar() {
        dni = findViewById(R.id.dni);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        msm = findViewById(R.id.mensajeregis);
        viewModelRegistro = ViewModelProviders.of(this).get(ViewModelRegistro.class);
    }

    public void guardar(View view) {
        Usuario usuario= new Usuario();
        if (!dni.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            usuario.setDni(Long.parseLong(dni.getText().toString()));
            usuario.setNombre(nombre.getText().toString());
            usuario.setApellido(apellido.getText().toString());
            usuario.setMail(email.getText().toString());
            usuario.setPassword(password.getText().toString());

            viewModelRegistro.guardar(getApplicationContext(), usuario);
            msm.setText("INFO GUARDADA!!");
            Intent ingresar= new Intent(this, MainActivity.class);
            startActivity(ingresar);

        }else{
            msm.setText("POR FAVOR COMPLETE TODOS LOS DATOS!!");
        }


    }
}