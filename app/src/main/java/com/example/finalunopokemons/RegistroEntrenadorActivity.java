package com.example.finalunopokemons;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalunopokemons.Clases.Entrenador;
import com.example.finalunopokemons.Services.EntrenadorService;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroEntrenadorActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static  final int REQUEST_GET_SINGLE_FILE=2;

    EditText etNombreEntrenador;
    EditText etPuebloEntrenador ;
    String imagenEntrenador;

    Entrenador entrenador = new Entrenador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_entrenador);

        etNombreEntrenador = findViewById(R.id.etNombreEntrenador);
        etPuebloEntrenador = findViewById(R.id.etPuebloEntrenador);

        Button btnCamara = findViewById(R.id.btnTomarFoto);
        Button btnGaleriaEntrenador = findViewById(R.id.btnGaleriaEntrenador);

        Button btnCrearEntrenador = findViewById(R.id.btnCrearEntrenador);



        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RegistroEntrenadorActivity.this.checkSelfPermission(Manifest.permission.CAMERA) !=  PackageManager.PERMISSION_DENIED )
                {
                    RegistroEntrenadorActivity.this.requestPermissions(new String[]{Manifest.permission.CAMERA}, 1001);
                }
                else
                {
                    dispatchTakePictureIntent();
                }
            }
        });

        btnGaleriaEntrenador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RegistroEntrenadorActivity.this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=  PackageManager.PERMISSION_DENIED )
                {
                    RegistroEntrenadorActivity.this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1002);
                }
                else {
                    dispatchPickFromGaleryIntent();
                }

            }
        });



        btnCrearEntrenador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit= new Retrofit.Builder()
                        .baseUrl("https://upn.lumenes.tk/entrenador/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EntrenadorService service =retrofit.create(EntrenadorService.class);
                etNombreEntrenador.setText(entrenador.nombres);
                etPuebloEntrenador.setText(entrenador.pueblo);
                dispatchTakePictureIntent();


                service.createEntrenador(entrenador).enqueue(new Callback<Entrenador>() {

                    @Override
                    public void onResponse(Call<Entrenador> call, Response<Entrenador> response) {
                        Log.i("MAIN_APP",  new Gson().toJson( response.body()));
                    }

                    @Override
                    public void onFailure(Call<Entrenador> call, Throwable t) {
                        Log.i("MAIN_APP",  "No se pudo estabecer conexion");
                    }
                });

            }
        });



//                service.createEntrenador(entrenador).enqueue(new Callback<Entrenador>() {
//
//                    @Override
//                    public void onResponse(Call<Entrenador> call, Response<Entrenador> response) {
//                        Log.i("MAIN_APP",  new Gson().toJson( response.body()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<Entrenador> call, Throwable t) {
//                        Log.i("MAIN_APP",  "No se pudo estabecer conexion");
//                    }
//                });





    }




    ///RESULTTTTTTT

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //MUESTRA IMAGEN DE CAMARA
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            imagenEntrenador = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            entrenador.imagen = imagenEntrenador;
            //imagenMostrar.setImageBitmap(imageBitmap);
        }

        ///MUESTRA IMAGEN DE GALERIA
        if (requestCode == REQUEST_GET_SINGLE_FILE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            // Get the path from the Uri
            final String path = getPathFromURI(selectedImageUri);
            if (path != null) {
                File f = new File(path);
                selectedImageUri = Uri.fromFile(f);
            }
            // Set the image in ImageView
           // imagenMostrar.setImageURI(selectedImageUri);

        }

    }

    ////////////////

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


    ////////////////////////


    // Tomar FOTO CON CAMARA
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // ELEGIR FOTO DE GALERIA
    private void dispatchPickFromGaleryIntent()
    {


        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"select picture" ) , REQUEST_GET_SINGLE_FILE);



    }
}