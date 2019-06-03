package com.turismogo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.turismogo.Bean.PlatosBean;
import com.squareup.picasso.Picasso;
import com.turismogo.R;

import java.util.ArrayList;

public class PlatosAdapter extends RecyclerView.Adapter<PlatosAdapter.PlatosViewHolder>{

    private ArrayList<PlatosBean> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public PlatosAdapter(ArrayList<PlatosBean> data, Context context) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PlatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlatosViewHolder(layoutInflater.from(parent.getContext()).inflate(R.layout.cardciew_platos,parent,false));
    }

    @Override
    public void onBindViewHolder(final PlatosViewHolder holder, final int position){
        final PlatosBean platosBean = data.get(position);
        holder.nombreMenu.setText(platosBean.getMenu());
        holder.precioMenu.setText("S/. "+platosBean.getPrecio());
        String url = platosBean.getUrl();

        Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imagePlato,new com.squareup.picasso.Callback(){
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

    class PlatosViewHolder extends RecyclerView.ViewHolder{
        TextView nombreMenu,precioMenu;
        ImageView imagePlato;

        public PlatosViewHolder(View itemView){
            super(itemView);
            nombreMenu = itemView.findViewById(R.id.nombreMenu);
            precioMenu = itemView.findViewById(R.id.precioMenu);

            imagePlato = itemView.findViewById(R.id.imagePlato);
        }
    }
}