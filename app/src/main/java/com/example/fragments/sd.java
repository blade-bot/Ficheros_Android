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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class sd extends Fragment {
    EditText datsd;
    Button b1,b2;
    TextView tvsd;
    File file;
    String carpeta="archivos/";
    String archivo="fichero";
    String contenido;
    String file_path;
    String name="fichero.txt";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.sd_main, container,  false);

        datsd = vista.findViewById(R.id.et_datos_sd);
        b1 = vista.findViewById(R.id.boton_escribir_externo_sd);
        b2 = vista.findViewById(R.id.boton_leer_externo_sd);
        tvsd = vista.findViewById(R.id.tv_datos_sd);
        //ME DABA NULL EN LA RUTA
        //String pathSDCard = System.getenv("SECONDARY_STORAGE");
        //no me funciona la ruta
        //String pathSDCard = "/mnt/sdcard-ext/";
        //saque la ruta de la app de archivos de google
        //String pathSDCard = "/storage/B4CE-1715/";
        //probando rutas sacadas por adb
        //String pathSDCard ="/mnt/sdcard/";
        //String pathSDCard ="/storage/B4CE-1715/";
        String pathSDCard = "/storage/sdcard0/";

        file_path = pathSDCard+carpeta;
        //archivo auxiliar para crear el directorio y archivo
        File aux = new File(pathSDCard);
        File sdfile = new File(aux.getAbsolutePath()+"/"+carpeta);
        if (!sdfile.exists()) {
            sdfile.mkdir();
            Log.d("Valor de externalfile", String.valueOf(sdfile));
        }
        try {
            file = new File(file_path, name);
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("Texto incial del archivo en la SD");
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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escribir(getContext());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leer(getContext());
            }
        });
        return vista;
    }
    public void escribir(Context contextescribirsd){
        try {
            file = new File(file_path, name);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(datsd.getText().toString());
            bw.flush();
            bw.close();
            Log.d("Valor de file", String.valueOf(file));
            Log.d("Valor de file_path", String.valueOf(file_path));
        } catch (IOException e) {
            Log.e("FILE I/O", "Error en la escritura de fichero: " + e.getMessage());
            Log.d("Valor de file", String.valueOf(file));
            Log.d("Valor de file_path", String.valueOf(file_path));
        }
        tvsd.setText("");
        Toast.makeText(getContext(), "Contenido Actualizado", Toast.LENGTH_SHORT).show();
        leer(getContext());
    }
    public  void leer(Context contextleersd){
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
            tvsd.setText(contenido);
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}
