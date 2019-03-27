package yjh.com.cn.pearlvideo.MyAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yjh.com.cn.pearlvideo.R;

/**
 * Created by 979810711 on 2019/3/19.
 */

public class MyRescAdapter extends RecyclerView.Adapter<MyRescAdapter.Vh> {

    private List<Integer> list;

    public MyRescAdapter(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rescyitem, parent, false);
        return new Vh(v);
    }




    @Override
    public void onBindViewHolder(Vh holder, int position) {

        System.out.println("position  "+position);
        holder.item_img.setImageResource(list.get(position));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vh extends RecyclerView.ViewHolder{

        public ImageView item_img,ax_img;
        public TextView number_tx;

        public Vh(View itemView) {
            super(itemView);
            item_img =itemView.findViewById(R.id.item_img);
            ax_img =itemView.findViewById(R.id.ax_img);
            number_tx =itemView.findViewById(R.id.number_tx);
        }
    }


}
