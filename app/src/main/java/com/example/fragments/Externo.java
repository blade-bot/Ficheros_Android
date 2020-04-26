package com.example.fragments;

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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Externo extends Fragment implements View.OnClickListener{
    Button le,ee;
    EditText edd;
    TextView tvdsd;
    File file;
    String carpeta="/archivos/";
    String archivo="fichero";
    String contenido;
    String file_path;
    String name="fichero.txt";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.externo_main, container,  false);

        le = vista.findViewById(R.id.boton_escribir_externo);
        ee = vista.findViewById(R.id.boton_leer_externo);
        edd = vista.findViewById(R.id.et_telefono_externo);
        tvdsd = vista.findViewById(R.id.tv_datos_externo);


        file_path = Environment.getExternalStorageDirectory()+carpeta;
        //archivo auxiliar para crear el directorio y archivo
        File aux = Environment.getExternalStorageDirectory();
        File externalfile = new File(aux.getAbsolutePath()+carpeta);
        if (!externalfile.exists()) {
            externalfile.mkdir();
            Log.d("Valor de externalfile", String.valueOf(externalfile));
        }
        try {
            file = new File(aux.getAbsolutePath()+carpeta, name);
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("Texto incial del archivo externo");
            bw.flush();
            bw.close();
            Log.d("Valor de file", String.valueOf(file));
            Log.d("Valor de file_path", String.valueOf(file_path));
        } catch (IOException e) {
            Log.e("FILE I/O", "Error en la escritura de fichero: " + e.getMessage());
            Log.d("Valor de file", String.valueOf(file));
            Log.d("Valor de file_path", String.valueOf(file_path));
        }
        if (file.exists()){
            leer(getContext());
        }

        le.setOnClickListener((View.OnClickListener) this);
        ee.setOnClickListener((View.OnClickListener) this);

        return vista;
    }
    public void escribir(Context contextescribire){
        FileWriter ficheroexterno = null;
        PrintWriter pw= null;
        try {
            ficheroexterno = new FileWriter(file);
            pw = new PrintWriter(ficheroexterno);
            pw.println(edd.getText().toString());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != ficheroexterno){
                    ficheroexterno.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        edd.setText("");
        Toast.makeText(getContext(), "Contenido Actualizado", Toast.LENGTH_SHORT).show();
        leer(getContext());
    }
    public  void leer(Context contextleere){
        contenido="";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            InputStreamReader archivo = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(archivo);

            int ascii;
            while ((ascii = br.read()) != -1)
            {
                char caracter = (char) ascii;
                contenido += caracter;
            }
            tvdsd.setText(contenido);
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boton_escribir_externo:
                escribir(getContext());
                break;
            case R.id.boton_leer_externo:
                leer(getContext());
                break;
        }
    }
}
