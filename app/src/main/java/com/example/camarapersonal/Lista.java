package com.example.camarapersonal;

import static android.os.Environment.getExternalStorageDirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class Lista extends AppCompatActivity {
    private ListView lista;
    private ImageView imagen;
    private String[] archivos;
    private ArrayAdapter<String> adaptador ;
    private ArrayList<Foto> fotos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        File dir = new File(getExternalStorageDirectory(), "DCIM/Personal");
        archivos = dir.list();
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, archivos);
        lista = (ListView) findViewById(R.id.lstImages);
        lista.setAdapter(adaptador);
        imagen = (ImageView) findViewById(R.id.imgView);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Bitmap bitmap = BitmapFactory.decodeFile(getExternalStorageDirectory()+ "/DCIM/Personal/" + archivos[arg2]);
                imagen.setImageBitmap(bitmap);
            }
        });
    }
}