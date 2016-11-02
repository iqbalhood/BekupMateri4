package com.cari.gadget.bekup.medan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DetailPhone extends AppCompatActivity {

    TextView tvDimensi;
    String dimensiHP ="";
    ImageView imageView, imageViewLagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phone);

        imageView = (ImageView) findViewById(R.id.gambarHp);
        imageViewLagi = (ImageView) findViewById(R.id.gambarHpLagi);

        Intent ambil = getIntent();
        String slug  =  ambil.getStringExtra("slug");
        String gambar  =  ambil.getStringExtra("gambar");

        new DetailPhone.JSONAsyncTask().execute("http://ibacor.com/api/gsm-arena?view=product&slug="+slug);
        Picasso.with(this).load(gambar).into(imageViewLagi);
        Glide.with(this).load(gambar).into(imageView);

        tvDimensi = (TextView)findViewById(R.id.tvDimensi);



    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(DetailPhone.this);
            dialog.setMessage("Sedang Mengambil Data...");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONObject deskripsi = jsono.getJSONObject("data");

                    JSONObject body = deskripsi.getJSONObject("body");
                    JSONObject display = deskripsi.getJSONObject("display");

                    String dimensiget = body.getString("dimensions");

                    System.out.println("DIMENSI " + dimensiget);

                    System.out.println("TYPE " + display.getString("type"));



                    dimensiHP = dimensiget;
                    return true;
                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            tvDimensi.setText(dimensiHP);
            if(result == false)
                Toast.makeText(DetailPhone.this, "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }








}
