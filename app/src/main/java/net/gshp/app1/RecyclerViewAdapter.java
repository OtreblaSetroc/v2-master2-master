package net.gshp.app1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by leo on 20/06/18.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<SKU> lstSku;

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected View layout;
        private TextView txtSku;
        private RadioButton radio1;
        private RadioButton radio2;


        public ViewHolder(final View itemView) {
            super(itemView);
            layout = itemView;
            txtSku = itemView.findViewById(R.id.sku);
            radio1 = itemView.findViewById(R.id.radio1);
            radio2 = itemView.findViewById(R.id.radio2);
        }

    }


    public RecyclerViewAdapter(List<SKU> sku) {
        this.lstSku = sku;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.activity_option, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SKU us = lstSku.get(position);
        holder.txtSku.setText(us.getValor());


        holder.radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String valor = "";
                if (isChecked) {
                    us.setAnswer(1);
                }
            }
        });
        holder.radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String valor = "";
                if (isChecked) {
                    us.setAnswer(2);
                }
            }
        });

        if (us.getAnswer() == 1) {
            holder.radio1.setChecked(true);
            holder.radio2.setChecked(false);
        } else if (us.getAnswer() ==2) {
            holder.radio1.setChecked(false);
            holder.radio2.setChecked(true);
        } else {
            holder.radio1.setChecked(false);
            holder.radio2.setChecked(false);
        }

        /*if (dato.getValor().equalsIgnoreCase("Si")) {
            holder.radio1.setChecked(true);
        } else if (dato.getValor().equalsIgnoreCase("No")) {
            holder.radio2.setChecked(true);
        }else{
            holder.radio1.setChecked(false);
            holder.radio2.setChecked(false);
        }*/

    }

    @Override
    public int getItemCount() {
        return lstSku.size();
    }
}


