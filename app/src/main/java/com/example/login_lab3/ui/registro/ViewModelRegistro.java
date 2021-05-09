package com.example.login_lab3.ui.registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.login_lab3.model.Usuario;
import com.example.login_lab3.request.ApiClient;

public class ViewModelRegistro extends AndroidViewModel {

    private MutableLiveData<Usuario> usuario;

    public ViewModelRegistro(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getUsuario() {
        if (usuario==null) {
            usuario= new MutableLiveData<>();

        }
        return usuario;
    }

    public void guardar(Context context, Usuario usuario) {
        ApiClient.guardar(context, usuario);
    }

    public void mostrar(Context context) {
        Usuario usuar= ApiClient.leer(context);
        usuario.setValue(usuar);

    }


}
