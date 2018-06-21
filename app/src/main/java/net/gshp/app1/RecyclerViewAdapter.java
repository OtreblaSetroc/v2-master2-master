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

    private List<CSKU> ans;

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected View layout;
        private TextView tv;
        private RadioButton radio1;
        private RadioButton radio2;


        public ViewHolder(final View itemView) {
            super(itemView);
            layout = itemView;
            tv = itemView.findViewById(R.id.sku);
            radio1 = itemView.findViewById(R.id.radio1);
            radio2 = itemView.findViewById(R.id.radio2);
        }

    }


    public RecyclerViewAdapter(List<CSKU> ans) {
        this.ans = ans;
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
        final CSKU dato = ans.get(position);
        holder.tv.setText("C_SKU ID: " + dato.getIdCSKU());

        holder.radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String valor = "";
                if(isChecked){
                    valor = holder.radio1.getText().toString();
                    holder.radio2.setChecked(false);
                    ans.get(position).setValor("" + valor);
                }
            }
        });

        holder.radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String valor = "";
                if(isChecked){
                    valor = holder.radio2.getText().toString();
                    holder.radio1.setChecked(false);
                    ans.get(position).setValor(valor);
                }
            }
        });

        if (dato.getValor().equalsIgnoreCase("Si")) {
            holder.radio1.setChecked(true);
        } else if (dato.getValor().equalsIgnoreCase("No")) {
            holder.radio2.setChecked(true);
        }else{
            holder.radio1.setChecked(false);
            holder.radio2.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return ans.size();
    }

}


