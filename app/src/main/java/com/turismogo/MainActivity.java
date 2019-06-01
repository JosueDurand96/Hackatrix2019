package com.turismogo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.turismogo.Principal.LoginActivity;

public class MainActivity extends AppCompatActivity {
  private final int DURACION_SPLASH = 3000;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Handler H=  new Handler();
    new Handler().postDelayed(new Runnable(){
      public void run(){
        // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
      }
    }, DURACION_SPLASH);
  }
}
