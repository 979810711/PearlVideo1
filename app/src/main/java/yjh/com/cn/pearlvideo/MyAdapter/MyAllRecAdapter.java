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
 * Created by 979810711 on 2019/3/21.
 */

public class MyAllRecAdapter extends RecyclerView.Adapter<MyAllRecAdapter.MyAll> {


    private List<String> list;
    private Context context;

    public MyAllRecAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyAll onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.allrecy_item, parent, false);
        return new MyAll(v);
    }

    @Override
    public void onBindViewHolder(MyAll holder, int position) {
        Glide.with(context).load(R.drawable.mei)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(context, 1, Color.parseColor("#dddddd"))))
                .into(holder.head);

        holder.name_text.setText(list.get(position));

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
        return list.size();
    }


    public class MyAll extends RecyclerView.ViewHolder {

        public ImageView head, icon;
        public TextView name_text, type, data_text;

        public MyAll(View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.head);
            icon = itemView.findViewById(R.id.icons);
            name_text = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            data_text = itemView.findViewById(R.id.data);
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
