package com.turismogo.LugaresTuristicos;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.turismogo.Adapter.HuariqueAdapter;
import com.turismogo.Adapter.HuariqueAdapterList;
import com.turismogo.Bean.HuariqueBean;
import com.turismogo.Menu.MenuActivity;
import com.turismogo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListarHuariqueActivity extends AppCompatActivity {
  private ListView lstProductos;
  String costa,sierra,selva;
  EditText editText2;

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
    rmHuarique = findViewById(R.id.huariqueRecycler);
    btnGo = findViewById(R.id.btnGo);
    final ArrayList<HuariqueBean> lista = new ArrayList<HuariqueBean>();

    String url = "http://34.74.187.30/ApiRestHackathon2019/Controller/HuariqueController.php?op=2";

    RequestQueue requestQueue = Volley.newRequestQueue(ListarHuariqueActivity.this);

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

            lista.add(huariqueBean);

          }

          Log.e("mensajeadasd",lista.size()+"");

          glm = new GridLayoutManager(ListarHuariqueActivity.this, 1);
          rmHuarique.setLayoutManager(glm);
          huariqueAdapter = new HuariqueAdapter(lista, ListarHuariqueActivity.this);
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

    editText2=(EditText)findViewById(R.id.editText2);
    editText2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        buscar();
      }
    });

  }

  private void buscar(){
    BUSCARPRODUCT tarea = new BUSCARPRODUCT();
    tarea.execute();
  }


  //Tarea Asíncrona para llamar al WS de listado en segundo plano
  private class BUSCARPRODUCT extends AsyncTask<String,Integer,Boolean> {

    private List<HuariqueBean> listaProductos;

    protected Boolean doInBackground(String... params) {
      HttpURLConnection connection = null;
      BufferedReader reader = null;
      boolean resul = true;
      String urlApiREST = "http://34.74.187.30/ApiRestHackathon2019/Controller/HuariqueController.php?op=3"+"&region="+"2"+"&nombre="+ editText2.getText().toString();
      try {
        URL url = new URL(urlApiREST);
        connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        InputStream stream = connection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(stream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
          buffer.append(line);
        }
        String finalJson = buffer.toString();
        JSONObject parentObject = new JSONObject(finalJson);
        JSONArray parentArray = parentObject.getJSONArray("huariques");
        listaProductos = new ArrayList<HuariqueBean>();
        for (int i = 0; i < parentArray.length(); i++) {
          JSONObject finalObject = parentArray.getJSONObject(i);
          String idproducto = finalObject.getString("idproducto");
          String nombre = finalObject.getString("nombre");
          String distrito = finalObject.getString("distrito");
          String distancia = finalObject.getString("distancia");
          String promociones = finalObject.getString("promociones");
          String imagen = finalObject.getString("imagen");



          listaProductos.add(new HuariqueBean(idproducto, nombre,promociones,distrito,distancia, imagen));
        }
      } catch (Exception ex) {
        Log.e("ServicioRest", "Error!", ex);
        resul = false;
      }
      return resul;
    }


    protected void onPostExecute(Boolean result) {


      if (result) {
        HuariqueAdapterList adaptador = new HuariqueAdapterList(ListarHuariqueActivity.this, listaProductos);
        lstProductos.setAdapter(adaptador);
      }
    }
  }

}
