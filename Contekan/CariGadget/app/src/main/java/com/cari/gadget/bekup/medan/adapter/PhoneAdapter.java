package com.cari.gadget.bekup.medan.adapter;

/**
 * Created by iqbalhood on 30/10/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import com.cari.gadget.bekup.medan.R;
import com.cari.gadget.bekup.medan.setget.Phone;

public class PhoneAdapter extends ArrayAdapter<Phone>  {

    ArrayList<Phone> phoneList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public PhoneAdapter(Context context, int resource, ArrayList<Phone> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        phoneList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview    = (ImageView) v.findViewById(R.id.img_phone);
            holder.tvTitle      = (TextView) v.findViewById(R.id.tvTitle);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.placehold);
        new DownloadImageTask(holder.imageview).execute(phoneList.get(position).getImage());
        holder.tvTitle.setText(phoneList.get(position).getTitle());

        return v;

    }


    static class ViewHolder {
        public ImageView imageview;
        public TextView  tvTitle;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }






}
