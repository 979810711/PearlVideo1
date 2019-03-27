package yjh.com.cn.pearlvideo.MyAdapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import yjh.com.cn.pearlvideo.R;
import yjh.com.cn.pearlvideo.Utlis.GlideCircleTransform;

/**
 * Created by 979810711 on 2019/3/22.
 */

public class MyMakeAdapter extends RecyclerView.Adapter<MyMakeAdapter.MyMake>  {


    private List<String> strings;
    private Context context;

    public MyMakeAdapter(List<String> strings,Context context) {
        this.strings = strings;
        this.context=context;
    }

    @Override
    public MyMake onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.makeitem, parent, false);
        return new MyMake(v);
    }

    @Override
    public void onBindViewHolder(MyMake holder, int position) {
        Glide.with(context).load(R.drawable.mei)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(context, 1, Color.parseColor("#dddddd"))))
                .into(holder.myheads);
        holder.name.setText(strings.get(position));
        holder.keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strings.remove(position);
                notifyItemRemoved(position); //
                if(position != strings.size()){ // 如果移除的是最后一个，忽略
                    notifyItemRangeChanged(position, strings.size() - position);
                }

            }
        });
       //item点击
        final int i = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


    public class MyMake extends RecyclerView.ViewHolder {

        public ImageView myheads, keep,sex;
        public TextView name,  my_zp,fans,goodly;

        public MyMake(View itemView) {
            super(itemView);
            myheads = itemView.findViewById(R.id.heads);
            keep = itemView.findViewById(R.id.keep);
            name = itemView.findViewById(R.id.name);
            sex = itemView.findViewById(R.id.sex);
            my_zp = itemView.findViewById(R.id.my_zp);
            fans = itemView.findViewById(R.id.fans);
            goodly = itemView.findViewById(R.id.goodly);
        }
    }
    /**
     * 点击
     */
    public interface OnItemClickListener {
        void onClick(int position);
    }

     OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



}
