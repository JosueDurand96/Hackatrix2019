package com.turismogo.LugaresTuristicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.turismogo.R;

public class ListRegionActivity extends AppCompatActivity {

  ImageView imageViewImagen,imageViewImagen2,imageViewImagen3;
  String id_regionCosta="1";
  String id_regionSierra="2";
  String id_regionSelva="3";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_region);
    imageViewImagen=(ImageView)findViewById(R.id.imageViewImagen);
    imageViewImagen2=(ImageView)findViewById(R.id.imageViewImagen2);
    imageViewImagen3=(ImageView)findViewById(R.id.imageViewImagen3);
    imageViewImagen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),ListarHuariqueActivity.class);
       // intent.putExtra("costa",id_regionCosta);
        startActivity(intent);
      }
    });
    imageViewImagen2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),ListarHuariqueActivity.class);
        //intent.putExtra("sierra",id_regionSierra);
        startActivity(intent);
      }
    });
    imageViewImagen3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),ListarHuariqueActivity.class);
        //intent.putExtra("selva",id_regionSelva);
        startActivity(intent);
      }
    });
  }
}
