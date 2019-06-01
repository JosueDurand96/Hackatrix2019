package com.turismogo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.turismogo.Bean.HuariqueBean;
import com.turismogo.R;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HuariqueAdapterList  extends BaseAdapter {
  LayoutInflater inflater;

  private static String EtidproductoCarrito;

  List<HuariqueBean> items;
  private Context mContext;
  private String urlServer = "http://34.74.187.30/ApiRestHackathon2019/Controller/HuariqueController.php?op=2";

  public HuariqueAdapterList(Activity context, List<HuariqueBean> items) {
    super();
    this.mContext = context;
    this.items = items;
    this.inflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount() {
    return items.size();
  }

  @Override
  public Object getItem(int position) {
    return items.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final HuariqueBean item = items.get(position);

    if (convertView == null)
      convertView = inflater.inflate(R.layout.cardview_noticias, null);

    final ImageView imgThumbnail = (ImageView) convertView.findViewById(R.id.imageViewImagen);
    final TextView nombreHuarique = (TextView) convertView.findViewById(R.id.nombreHuarique);
    final TextView lugarHuarique = (TextView) convertView.findViewById(R.id.lugarHuarique);
    final TextView promocionesHuarique = (TextView) convertView.findViewById(R.id.promocionesHuarique);
    //   final TextView txttotal =(TextView)convertView.findViewById(R.id.textViewTotal);
    final TextView distanciaHuarique = (TextView) convertView.findViewById(R.id.distanciaHuarique);
    //final ElegantNumberButton txtcantidad =(ElegantNumberButton)convertView.findViewById(R.id.number_button) ;
    String url = item.getMensaje();
   // Picasso.with(mContext).load(urlServer + "producto/" + item.ima).into(imgThumbnail);
//    Picasso.with(mContext).load(url).placeholder(R.mipmap.ic_launcher)
//      .error(R.mipmap.ic_launcher)
//      .into(convertView.distanciaHuarique,new com.squareup.picasso.Callback(){
//        @Override
//        public void onSuccess() {
//        }
//        @Override
//        public void onError() {
//        }
//      });


    return convertView;
  }


}