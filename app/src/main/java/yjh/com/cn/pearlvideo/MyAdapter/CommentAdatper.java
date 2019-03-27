package yjh.com.cn.pearlvideo.MyAdapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import yjh.com.cn.pearlvideo.R;
import yjh.com.cn.pearlvideo.Utlis.GlideCircleTransform;

/**
 * Created by 979810711 on 2019/3/26.
 */

public class CommentAdatper  extends RecyclerView.Adapter<CommentAdatper.VhComment>{

      private List<String> list;
      private Context context;
      private RecyclerView recyclerView;
      private VhComment vhComment;
      private  CommentItemAdapter commentItemAdapter;
      private  String listsize="";

    public CommentAdatper(List<String> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public VhComment onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new VhComment(v);
    }

    @Override
    public void onBindViewHolder(VhComment holder, int position) {

        Glide.with(context).load(R.drawable.mei)//暂时默认图片
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(context, 1, Color.parseColor("#dddddd"))))
                .into(holder.heads);
        holder.freedom.setText(list.get(position));

           vhComment= holder;

            listsize=list.get(position);
            recyclerView = vhComment.recy_item;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            commentItemAdapter = new CommentItemAdapter(listsize, context);
            recyclerView.setAdapter(commentItemAdapter);





    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VhComment extends RecyclerView.ViewHolder{

        public ImageView heads,ax_img;
        public TextView names,freedom,datas;
        public RecyclerView recy_item;



        public VhComment(View itemView) {
            super(itemView);
            heads = itemView.findViewById(R.id.heads);
            ax_img = itemView.findViewById(R.id.ax_img);
            names = itemView.findViewById(R.id.names);
            freedom = itemView.findViewById(R.id.freedom);
            datas = itemView.findViewById(R.id.datas);
            recy_item = itemView.findViewById(R.id.recy_item);
        }
    }




    public class CommentItemAdapter extends RecyclerView.Adapter<VhCommentItem>{

        private String list;
        private Context context;
        private List<String> list1 =new ArrayList<>();

        public CommentItemAdapter(String list, Context context) {
            this.list = list;
            this.context = context;
            list1.add(list);
        }

        @Override
        public VhCommentItem onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new VhCommentItem(v);

        }

        @Override
        public void onBindViewHolder(VhCommentItem holder, int position) {
            Glide.with(context).load(R.drawable.mei)//暂时默认图片
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .transform(new GlideCircleTransform(context, 1, Color.parseColor("#dddddd"))))
                    .into(holder.head1);
            holder.freedom1.setText(list1.get(position));
            holder.itemView.setBackgroundColor(Color.WHITE);


        }

        @Override
        public int getItemCount() {
            return list1.size();
        }
    }



    public class VhCommentItem extends RecyclerView.ViewHolder{

        public ImageView head1,ax_img1;
        public TextView name1,freedom1,datas1;



        public VhCommentItem(View itemView) {
            super(itemView);
            head1 = itemView.findViewById(R.id.head1);
            ax_img1 = itemView.findViewById(R.id.ax_img1);
            name1 = itemView.findViewById(R.id.names);
            freedom1 = itemView.findViewById(R.id.freedom1);
            datas1 = itemView.findViewById(R.id.datas1);
        }
    }





}
