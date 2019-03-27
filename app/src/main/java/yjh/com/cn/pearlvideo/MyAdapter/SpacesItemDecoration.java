package yjh.com.cn.pearlvideo.MyAdapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 设置RecyclerView item间距
 * Created by taoji on 2017/7/20 0020.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int mLeft;
    private int mTop;
    private int mRight;
    private int mBottom;

    public SpacesItemDecoration(int mLeft, int mRight, int mTop, int mBottom) {
        this.mLeft = mLeft;
        this.mRight = mRight;
        this.mTop = mTop;
        this.mBottom = mBottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=mLeft;
        outRect.top=mTop;
        outRect.right=mRight;
        outRect.bottom=mBottom;
    }
}
