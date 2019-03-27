package yjh.com.cn.pearlvideo.MyAdapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import yjh.com.cn.pearlvideo.R;
import yjh.com.cn.pearlvideo.Utlis.GlideCircleTransform;

/**
 * Created by 979810711 on 2019/3/25.
 */

public class FocusAdapter extends RecyclerView.Adapter<FocusAdapter.MyFocus>{

    private List<String> list;

    private Context context;

    public FocusAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyFocus onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.focus_item, parent, false);
        return new MyFocus(v);
    }

    @Override
    public void onBindViewHolder(MyFocus holder, int position) {
        Glide.with(context).load(R.drawable.mei)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(context, 1, Color.parseColor("#dddddd"))))
                .into(holder.head);
        holder.sname.setText(list.get(position));
        if(position%2 ==0){
           holder.itemView.setPadding(0,0,0,5);
        }else {
            holder.itemView.setPadding(8,0,0,5);
        }
        final int i = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(i,v);

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyFocus extends RecyclerView.ViewHolder {

        public ImageView head,love;
        public TextView sname,number_size;
        public RelativeLayout bj_layout;

        public MyFocus(View itemView) {
            super(itemView);
            head =itemView.findViewById(R.id.head);
            sname =itemView.findViewById(R.id.sname);
            number_size =itemView.findViewById(R.id.number_size);
            love =itemView.findViewById(R.id.love);
            bj_layout =itemView.findViewById(R.id.bj_layout);


        }
    }

    /**
     * 点击
     */
    public interface OnItemClickListener {
        void onClick(int position,View v);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
