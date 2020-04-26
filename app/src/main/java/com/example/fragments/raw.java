package com.example.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.R;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class raw extends Fragment implements  View.OnClickListener{
    Button b1;
    TextView tvraw;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.raw_main, container,  false);

        b1 = vista.findViewById(R.id.boton_leer_raw);
        tvraw = vista.findViewById(R.id.tv_datos_raw);
        leer(getContext());
        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_leer_raw:
                leer(getContext());
                break;
        }
    }
    public void leer(Context contextraw){
        String contenido="";
        try {
            InputStream archivo = getResources().openRawResource(R.raw.raw);
            BufferedReader br = new BufferedReader(new InputStreamReader(archivo));

            String auxstring="";
            while ((auxstring = br.readLine()) != null)
            {
                contenido += auxstring +"\n";
                Log.i("datos de raw while",String.valueOf(contenido));
            }
            Log.i("datos de raw",String.valueOf(contenido));
            tvraw.setText(contenido);
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}
