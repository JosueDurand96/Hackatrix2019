package com.turismogo.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.turismogo.Adapter.HuariqueAdapter;
import com.turismogo.Adapter.PlatosAdapter;
import com.turismogo.Bean.HuariqueBean;
import com.turismogo.Bean.PlatosBean;
import com.turismogo.LugaresTuristicos.ListRegionActivity;
import com.turismogo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  private GridLayoutManager glm;
  private HuariqueAdapter huariqueAdapter;
  private PlatosAdapter plataosAdapter;
  private RecyclerView rmHuarique;
  LinearLayout btnGo;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    rmHuarique = findViewById(R.id.huariqueRecycler);
    btnGo = findViewById(R.id.btnGo);

    btnGo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MenuActivity.this, ListRegionActivity.class);
        startActivity(intent);
      }
    });


    final ArrayList<HuariqueBean> lista = new ArrayList<HuariqueBean>();

    String url = "http://34.74.187.30/ApiRestHackathon2019/Controller/HuariqueController.php?op=2";

    RequestQueue requestQueue = Volley.newRequestQueue(MenuActivity.this);

    JsonObjectRequest jsObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        try {

          JSONArray jsonArray = response.getJSONArray("rating");
          for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            HuariqueBean huariqueBean = new HuariqueBean();

            Log.e("mensajeadasd",jsonObject.getString("nombre"));

            huariqueBean.setNombre(jsonObject.getString("nombre"));
            huariqueBean.setDistrito(jsonObject.getString("distrito"));
            huariqueBean.setDistancia(jsonObject.getString("distancia"));
            huariqueBean.setPromociones(jsonObject.getString("promociones"));
            huariqueBean.setMensaje(jsonObject.getString("imagen"));
            huariqueBean.setId_huarique(jsonObject.getString("id"));

            lista.add(huariqueBean);

          }

          Log.e("mensajeadasd",lista.size()+"");

          glm = new GridLayoutManager(MenuActivity.this, 1);
          rmHuarique.setLayoutManager(glm);
          huariqueAdapter = new HuariqueAdapter(lista, MenuActivity.this);
          rmHuarique.setAdapter(huariqueAdapter);

        } catch (JSONException e) {
          e.printStackTrace();
        }

      }
    },
      new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Log.d("TAG", "Error Respuesta en JSON: " + error.getMessage());
        }
      });
    requestQueue.add(jsObjectRequest);

  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuActivity.this);
      View mView = getLayoutInflater().inflate(R.layout.activity_calculadora, null);
      mBuilder.setCancelable(false);
      Button btnTomarFoto = mView.findViewById(R.id.buttonCalc);
      final EditText txtprecio = mView.findViewById(R.id.txtprecio);
      mBuilder.setView(mView);
      final AlertDialog alertdialog = mBuilder.create();
      btnTomarFoto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          RequestQueue queue = Volley.newRequestQueue(MenuActivity.this);  // this = context

          JSONObject jsonObject = new JSONObject();
          try {
            jsonObject.put("precio",txtprecio.getText()+"");
          }catch (Exception e){

          }

          final ArrayList<PlatosBean> platosBeans = new ArrayList<>();

          String url = "http://34.74.187.30/ApiRestHackathon2019/Controller/HuariqueController.php?op=4";
          JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
            new Response.Listener<JSONObject>()
            {
              @Override
              public void onResponse(JSONObject response) {

                Log.e("mwnasJDASFADSJFADS",response+"");
                try {
                  JSONArray jsonArray = response.getJSONArray("huariques");
                  for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    PlatosBean bean = new PlatosBean();
                    bean.setMenu(jsonObject.getString("menu"));
                    bean.setPrecio(jsonObject.getString("precio"));
                    bean.setUrl(jsonObject.getString("url"));

                    platosBeans.add(bean);
                  }

                  glm = new GridLayoutManager(MenuActivity.this, 1);
                  rmHuarique.setLayoutManager(glm);
                  plataosAdapter = new PlatosAdapter(platosBeans, MenuActivity.this);
                  rmHuarique.setAdapter(plataosAdapter);


                }catch (Exception e){

                }
              }
            },
            new Response.ErrorListener()
            {
              @Override
              public void onErrorResponse(VolleyError error) {
                // error
                Log.d("Error.Response", error+"");
              }
            }
          ) {

          };
          queue.add(postRequest);

          alertdialog.dismiss();
        }
      });
      alertdialog.show();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
