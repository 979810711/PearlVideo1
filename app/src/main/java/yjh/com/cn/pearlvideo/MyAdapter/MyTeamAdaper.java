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

public class MyTeamAdaper extends RecyclerView.Adapter<MyTeamAdaper.MyTeam>{

    private List<String> list;
    private Context context;

    public MyTeamAdaper(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyTeam onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teamitem, parent, false);
        return new MyTeam(v);
    }

    @Override
    public void onBindViewHolder(MyTeam holder, int position) {
        Glide.with(context).load(R.drawable.mei)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(context, 1, Color.parseColor("#dddddd"))))
                .into(holder.hear);

        holder.team_name.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyTeam extends RecyclerView.ViewHolder{
        public ImageView hear;
        public TextView team_name;

        public MyTeam(View itemView) {
            super(itemView);
            hear=itemView.findViewById(R.id.hear);
            team_name=itemView.findViewById(R.id.team_name);
        }
    }


}
