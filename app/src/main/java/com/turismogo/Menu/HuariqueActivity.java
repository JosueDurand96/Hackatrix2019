package com.turismogo.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.turismogo.Adapter.PlatosAdapter;
import com.turismogo.Bean.HuariqueBean;
import com.turismogo.Bean.PlatosBean;
import com.squareup.picasso.Picasso;
import com.turismogo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HuariqueActivity extends AppCompatActivity {

    private GridLayoutManager glm;
    private PlatosAdapter platosAdapter;
    private RecyclerView recyclerPalto;

    TextView textoMensaje;
    ImageView imageHuarique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huarique);

        textoMensaje = findViewById(R.id.textoMensaje);
        imageHuarique = findViewById(R.id.imageHuarique);
        recyclerPalto = findViewById(R.id.recyclerPalto);

        final String identidificador = (String) getIntent().getSerializableExtra("identidificador");

        RequestQueue queue = Volley.newRequestQueue(this);  // this = context

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",identidificador);
        }catch (Exception e){

        }

        final ArrayList<PlatosBean> platosBeans = new ArrayList<>();

        String url = "http://34.74.187.30/ApiRestHackathon2019/Controller/HuariqueController.php?op=5";
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

                                    textoMensaje.setText("Abierto de "+jsonObject.getString("hora_atencion")+" hasta las "+jsonObject.getString("hora_cierre")+"");

                                    String url = jsonObject.getString("imagen");

                                    Picasso.with(HuariqueActivity.this).load(url).placeholder(R.mipmap.ic_launcher)
                                            .error(R.mipmap.ic_launcher)
                                            .into(imageHuarique,new com.squareup.picasso.Callback(){
                                                @Override
                                                public void onSuccess() {
                                                }
                                                @Override
                                                public void onError() {
                                                }
                                            });

                                    PlatosBean bean = new PlatosBean();
                                    bean.setMenu(jsonObject.getString("menu"));
                                    bean.setPrecio(jsonObject.getString("precio"));
                                    bean.setUrl(jsonObject.getString("url"));

                                    platosBeans.add(bean);
                                }

                                glm = new GridLayoutManager(HuariqueActivity.this, 1);
                                recyclerPalto.setLayoutManager(glm);
                                platosAdapter = new PlatosAdapter(platosBeans, HuariqueActivity.this);
                                recyclerPalto.setAdapter(platosAdapter);


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


    }



}
