package com.lbins.SharePic.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.lbins.SharePic.R;
import com.lbins.SharePic.SharePicApplication;
import com.lbins.SharePic.base.InternetURL;
import com.lbins.SharePic.entity.ArticleObj;
import com.lbins.SharePic.ui.GalleryUrlActivity;
import com.lbins.SharePic.ui.RegActivity;
import com.lbins.SharePic.util.Constants;
import com.lbins.SharePic.util.StringUtil;
import com.lbins.SharePic.widget.PictureGridview;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态
 */
public class RecordAdapter extends BaseAdapter {
    private ViewHolder holder;
    private List<ArticleObj> records;
    private Context mContext;
    private String mEmp_id;//当前登陆者的UUID
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    ImageLoader imageLoader = ImageLoader.getInstance();//图片加载类

    private OnClickContentItemListener onClickContentItemListener;

    public void setOnClickContentItemListener(OnClickContentItemListener onClickContentItemListener) {
        this.onClickContentItemListener = onClickContentItemListener;
    }

    public RecordAdapter(List<ArticleObj> records, Context mContext, String emp_id) {
        this.records = records;
        this.mContext = mContext;
        this.mEmp_id = emp_id;
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.home_item, null);
            holder.item_cover = (ImageView) convertView.findViewById(R.id.item_cover);
            holder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            holder.item_content = (TextView) convertView.findViewById(R.id.item_content);
            holder.relate_img = (RelativeLayout) convertView.findViewById(R.id.relate_img);
            holder.relate_share = (RelativeLayout) convertView.findViewById(R.id.relate_share);
            holder.img_one = (ImageView) convertView.findViewById(R.id.img_one);
            holder.gridview_1 = (PictureGridview) convertView.findViewById(R.id.gridview_1);
            holder.gridview_2 = (PictureGridview) convertView.findViewById(R.id.gridview_2);
            holder.item_cover_t = (ImageView) convertView.findViewById(R.id.item_cover_t);
            holder.item_content_t = (TextView) convertView.findViewById(R.id.item_content_t);
            holder.item_dateline = (TextView) convertView.findViewById(R.id.item_dateline);
            holder.icon_index_comment = (ImageView) convertView.findViewById(R.id.icon_index_comment);
            holder.icon_index_share = (ImageView) convertView.findViewById(R.id.icon_index_share);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.gridview_1.setVisibility(View.GONE);
        holder.gridview_2.setVisibility(View.GONE);
        holder.img_one.setVisibility(View.GONE);

        holder.gridview_1.setSelector(new ColorDrawable(Color.TRANSPARENT));
        holder.gridview_2.setSelector(new ColorDrawable(Color.TRANSPARENT));

        holder.relate_img.setVisibility(View.GONE);
        holder.relate_share.setVisibility(View.GONE);

        final ArticleObj cell = records.get(position);//获得元素
        if (cell != null) {
            imageLoader.displayImage(InternetURL.INTERNAL_PIC+cell.getCover(), holder.item_cover, SharePicApplication.txOptions, animateFirstListener);

            holder.item_name.setText(cell.getNick_name());
            holder.item_content.setText(cell.getTitle());
            final List<String> listPics = new ArrayList<String>();
            if(!StringUtil.isNullOrEmpty(cell.getPicture1())){
                listPics.add(cell.getPicture1());
            }
            if(!StringUtil.isNullOrEmpty(cell.getPicture2())){
                listPics.add(cell.getPicture2());
            }
            if(!StringUtil.isNullOrEmpty(cell.getPicture3())){
                listPics.add(cell.getPicture3());
            }
            if(!StringUtil.isNullOrEmpty(cell.getPicture4())){
                listPics.add(cell.getPicture4());
            }
            if(!StringUtil.isNullOrEmpty(cell.getPicture5())){
                listPics.add(cell.getPicture5());
            }
            if(!StringUtil.isNullOrEmpty(cell.getPicture6())){
                listPics.add(cell.getPicture6());
            }
            if(!StringUtil.isNullOrEmpty(cell.getPicture7())){
                listPics.add(cell.getPicture7());
            }
            if(!StringUtil.isNullOrEmpty(cell.getPicture8())){
                listPics.add(cell.getPicture8());
            }
            if(!StringUtil.isNullOrEmpty(cell.getPicture9())){
                listPics.add(cell.getPicture9());
            }

            //把图片放到数组中 方便放大查看用
            final String[] picUrls = new String[listPics.size()];
            for(int i=0;i<listPics.size();i++){
                picUrls[i] = listPics.get(i);
            }
            if (listPics.size() > 0) {
                holder.relate_img.setVisibility(View.VISIBLE);
                switch (listPics.size()){
                    case 1:
                    {
                        holder.img_one.setVisibility(View.VISIBLE);
                        imageLoader.displayImage(InternetURL.INTERNAL_PIC+listPics.get(0), holder.img_one, SharePicApplication.options, animateFirstListener);
                    }
                        break;
                    case 2:
                    case 4:
                    {
                        holder.gridview_1.setVisibility(View.VISIBLE);
                        holder.gridview_1.setClickable(true);
                        holder.gridview_1.setPressed(true);
                        holder.gridview_1.setEnabled(true);
                        holder.gridview_1.setAdapter(new ImageGridViewAdapter(listPics, mContext));
                        holder.gridview_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(mContext, GalleryUrlActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                intent.putExtra(Constants.IMAGE_URLS, picUrls);
                                intent.putExtra(Constants.IMAGE_POSITION, position);
                                mContext.startActivity(intent);
                            }
                        });
                    }
                        break;
                    case 3:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        holder.gridview_2.setVisibility(View.VISIBLE);
                        holder.gridview_2.setClickable(true);
                        holder.gridview_2.setPressed(true);
                        holder.gridview_2.setEnabled(true);
                        holder.gridview_2.setAdapter(new ImageGridViewAdapter(listPics, mContext));
                        holder.gridview_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(mContext, GalleryUrlActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                intent.putExtra(Constants.IMAGE_URLS, picUrls);
                                intent.putExtra(Constants.IMAGE_POSITION, position);
                                mContext.startActivity(intent);
                            }
                        });
                        break;
                }
            }
        }

        //评论
        holder.icon_index_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickContentItemListener.onClickContentItem(position, 1, null);
            }
        });

        //分享
        holder.icon_index_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickContentItemListener.onClickContentItem(position, 2, null);
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView item_cover;//头像
        TextView item_name;//昵称
        TextView item_content;

        RelativeLayout relate_img;//
        RelativeLayout relate_share;//


        ImageView img_one;//
        PictureGridview gridview_1;//
        PictureGridview gridview_2;//


        ImageView item_cover_t;//
        TextView item_content_t;//

        TextView item_dateline;//
        ImageView icon_index_comment;//
        ImageView icon_index_share;//

    }

}
