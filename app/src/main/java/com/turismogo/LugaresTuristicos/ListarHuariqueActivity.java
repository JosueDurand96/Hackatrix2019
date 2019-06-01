package com.turismogo.LugaresTuristicos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.turismogo.Adapter.HuariqueAdapter;
import com.turismogo.R;

public class ListarHuariqueActivity extends AppCompatActivity {

  String costa,sierra,selva;
  TextView textView2;
  private GridLayoutManager glm;
  private HuariqueAdapter huariqueAdapter;
  private RecyclerView rmHuarique;
  LinearLayout btnGo;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listar_huarique);
    final   Bundle recupera=getIntent().getExtras();

//LLAMAR ID DEL SCREEN REGION
    if(recupera!=null){
      costa=recupera.getString("costa");
    }
    if(recupera!=null){
      sierra=recupera.getString("sierra");
    }
    if(recupera!=null){
      selva=recupera.getString("selva");
    }
    //textView2=(TextView)findViewById(R.id.textView2);
    //textView2.setText(costa);


  }
}
