package com.greatplan.myapplication.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import com.greatplan.myapplication.Bean.InformationBean;
import com.greatplan.myapplication.R;

import java.text.SimpleDateFormat;

import java.util.List;

/**
 * @Author :jack
 * @Date :2021/7/19
 * @Effect :
 **/
public class InformationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<InformationBean.ResultBean> list;
    Context context;

    public InformationAdapter(List<InformationBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = View.inflate(context, R.layout.information_item2, null);
            return new informationViewHolder(view);
        } else {
            View view = View.inflate(context, R.layout.information_item, null);
            return new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InformationBean.ResultBean resultBean = list.get(position);
        if (holder instanceof informationViewHolder) {//广告
            try {
                Uri uri = Uri.parse(resultBean.getInfoAdvertisingVo().getPic());
                ((informationViewHolder) holder).imageView.setImageURI(uri);
                ((informationViewHolder) holder).textView.setText(resultBean.getInfoAdvertisingVo().getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {//新闻
            if (resultBean != null) {
                try {
                    Uri uri = Uri.parse(resultBean.getThumbnail());
                    ((ViewHolder) holder).simpleDraweeView_img.setImageURI(uri);//图片
                    ((ViewHolder) holder).textView_collection.setText(resultBean.getCollection() + "");//收藏数量
                    ((ViewHolder) holder).textView_share.setText(resultBean.getShare() + "");//分享数量
                    ((ViewHolder) holder).textView_title.setText(resultBean.getTitle());//标题
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//时间格式
                    ((ViewHolder) holder).textView_time.setText(simpleDateFormat.format(resultBean.getReleaseTime()));//时间
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getWhetherAdvertising();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title, textView_time, textView_share, textView_collection;
        SimpleDraweeView simpleDraweeView_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_title = itemView.findViewById(R.id.information_title);
            textView_share = itemView.findViewById(R.id.information_share);
            textView_time = itemView.findViewById(R.id.information_releaseTime);
            textView_collection = itemView.findViewById(R.id.information_collection);
            simpleDraweeView_img = itemView.findViewById(R.id.information_img);

        }
    }

    public class informationViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imageView;
        TextView textView;
        public informationViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.information_img2);
            textView=itemView.findViewById(R.id.information_title2);
        }
    }
}
