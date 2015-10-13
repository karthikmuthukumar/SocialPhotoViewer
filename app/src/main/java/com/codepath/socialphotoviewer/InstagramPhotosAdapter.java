package com.codepath.socialphotoviewer;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by kmuthu1 on 10/10/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        InstagramPhoto photo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_item, parent, false);
        }
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        ImageView ivProfilePhoto = (ImageView) convertView.findViewById(R.id.ivProfilePhoto);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        TextView tvRelativeTime = (TextView) convertView.findViewById(R.id.tvRelativeTime);
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
        TextView tvComments = (TextView) convertView.findViewById(R.id.tvViewAllComments);

        tvUserName.setText(photo.getUserName());
        tvCaption.setText(Html.fromHtml("<font color='#3f729b'><b>" + photo.getUserComment().getUserName() + "</b></font> " + photo.getUserComment().getComment()));
        tvRelativeTime.setText(photo.getRelativeTime());
        tvLikes.setText(String.valueOf(photo.getLikesCount())+" likes");
        if (photo.getCommentsCount() > 1) {
            tvComments.setText(String.format("View all %s comments", photo.getCommentsCount()));
        }

        ivProfilePhoto.setImageResource(0);
        ivPhoto.setImageResource(0);

        Picasso.with(getContext()).load(photo.getImageUrl()).into(ivPhoto);
        Picasso.with(getContext()).load(photo.getProfileImageUrl()).into(ivProfilePhoto);

        return convertView;
    }
}
