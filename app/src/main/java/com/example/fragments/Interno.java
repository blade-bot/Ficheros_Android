package com.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Interno extends Fragment implements  View.OnClickListener{
    EditText datosinternos;
    Button li,ei;
    TextView tvdi;
    String contenido="";
    File file;
    boolean existe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.interno_main, container,  false);
        Log.i(" Vista",String.valueOf(existe));
        datosinternos = vista.findViewById(R.id.et_datos);
        li = vista.findViewById(R.id.boton_leer_interno);
        ei = vista.findViewById(R.id.boton_escribir_interno);
        tvdi = vista.findViewById(R.id.tv_datos);

        li.setOnClickListener((View.OnClickListener) this);
        ei.setOnClickListener((View.OnClickListener) this);
        File f = new File("archivo_interno.txt");
        existe=false;
        if (existe)
        {
            leer(getContext());
        }else {
            CrearArchivo(getContext());
        }
        return vista;
    }
    public void CrearArchivo(Context ctx){
        try
        {
            OutputStreamWriter fout = new OutputStreamWriter(ctx.openFileOutput("fichero_interno.txt", Activity.MODE_PRIVATE));
            fout.write("Texto inical del archivo interno.");
            fout.flush();
            fout.close();
            leer(getContext());
            existe=true;
            Log.i(" crear archivo",String.valueOf(existe));
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
    }
    public void escribir( Context context){
        try
        {
            OutputStreamWriter fout = new OutputStreamWriter(context.openFileOutput("fichero_interno.txt", Activity.MODE_PRIVATE));
            fout.write(datosinternos.getText().toString());
            fout.flush();
            fout.close();
            leer(getContext());
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
        datosinternos.setText(null);
        Toast.makeText(getContext(), "Contenido Actualizado", Toast.LENGTH_SHORT).show();
        leer(getContext());
    }

    public  void leer(Context con){
        String contenido="";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.openFileInput("fichero_interno.txt")));
            String auxstring="";
            while ((auxstring = br.readLine()) != null)
            {
                contenido += auxstring+"\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvdi.setText(contenido);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boton_escribir_interno:
                escribir(v.getContext());
                break;
            case R.id.boton_leer_interno:
                leer(getContext());
                break;
        }
    }
}
