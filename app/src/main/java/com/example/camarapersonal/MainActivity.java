package com.example.camarapersonal;

import static android.os.Environment.getExternalStorageDirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity{
    Button btnCamara;
    Button btnLista;
    Button btnGaleria;
    ImageView imageView;

    public static final int FOTO = 0;
    public static final int GALERIA=100;
    private String rutaFoto;
    //private Uri fotoURI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btnCamara = findViewById(R.id.btnCamara);
        btnLista = findViewById(R.id.btnLista);
        btnGaleria = findViewById(R.id.btnGaleria);

        btnGaleria.setOnClickListener(view->{
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, GALERIA);
        });
        btnLista.setOnClickListener(view ->{
            Intent intento = new Intent(this, Lista.class);
            startActivity(intento);
        });

        btnCamara.setOnClickListener(view->{
            tomarFoto();
        });
    }
  private void tomarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, FOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FOTO && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            try {
                String timeStamp = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
                String nombreArchivo = "JPEG_" + timeStamp + "_";
                File directorio = new File(getExternalStorageDirectory(), "DCIM/Personal");
                if (!directorio.exists()) {
                    File nuevaCarpeta = new File(getExternalStorageDirectory(), "DCIM/Personal");
                    nuevaCarpeta.mkdirs();
                }
                File imagen = File.createTempFile(nombreArchivo, ".jpg", directorio);
                rutaFoto = imagen.getAbsolutePath();
                FileOutputStream out = new FileOutputStream(imagen);//Guarda la imagen en la ruta de la variable dest
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                Toast.makeText(getApplicationContext(), "Imagen Guardada",
                        Toast.LENGTH_LONG).show();
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }else if(resultCode == RESULT_OK && requestCode == GALERIA) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }


}