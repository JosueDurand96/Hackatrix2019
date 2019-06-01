package com.turismogo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.turismogo.Bean.HuariqueBean;
import com.squareup.picasso.Picasso;
import com.turismogo.R;

import java.util.ArrayList;

public class HuariqueAdapter extends RecyclerView.Adapter<HuariqueAdapter.NoticiasViewHolder>{

    private ArrayList<HuariqueBean> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public HuariqueAdapter(ArrayList<HuariqueBean> data, Context context) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NoticiasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoticiasViewHolder(layoutInflater.from(parent.getContext()).inflate(R.layout.cardview_noticias,parent,false));
    }

    @Override
    public void onBindViewHolder(final NoticiasViewHolder holder, final int position){
        final HuariqueBean huariqueBean = data.get(position);
        holder.nombreHuarique.setText(huariqueBean.getNombre());
        holder.lugarHuarique.setText(huariqueBean.getDistrito());
        holder.promocionesHuarique.setText(huariqueBean.getPromociones());
        holder.distanciaHuarique.setText(huariqueBean.getDistancia());
        String url = huariqueBean.getMensaje();

        Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageHuarique,new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError() {
                    }
                });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NoticiasViewHolder extends RecyclerView.ViewHolder{
        TextView nombreHuarique,lugarHuarique,promocionesHuarique,distanciaHuarique;
        ImageView imageHuarique;

        public NoticiasViewHolder(View itemView){
            super(itemView);
            nombreHuarique = itemView.findViewById(R.id.nombreHuarique);
            lugarHuarique = itemView.findViewById(R.id.lugarHuarique);
            promocionesHuarique = itemView.findViewById(R.id.promocionesHuarique);
            distanciaHuarique = itemView.findViewById(R.id.distanciaHuarique);
            imageHuarique = itemView.findViewById(R.id.imageHuarique);
        }
    }

}
