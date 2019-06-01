package com.turismogo.Principal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.turismogo.LugaresTuristicos.ListRegionActivity;
import com.turismogo.R;

public class LoginActivity extends AppCompatActivity {

  TextView txtsaltar;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    txtsaltar=(TextView)findViewById(R.id.txtsaltar);
    txtsaltar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, ListRegionActivity.class);
        startActivity(intent);
      }
    });
  }
}
