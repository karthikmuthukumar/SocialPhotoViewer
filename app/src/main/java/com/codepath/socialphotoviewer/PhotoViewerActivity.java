package com.codepath.socialphotoviewer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PhotoViewerActivity extends ActionBarActivity {

    private List<InstagramPhoto> photos;
    private InstagramPhotosAdapter photosAdapter;
    //ClientId: c72f7b1a00ae432c891aa6e0088ac78b
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
        photos = new ArrayList<>();
        photosAdapter = new InstagramPhotosAdapter(this,photos);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photosAdapter);
        fetchPopularPhotos();
    }

    public void fetchPopularPhotos() {
        String url = "https://api.instagram.com/v1/media/popular?client_id=c72f7b1a00ae432c891aa6e0088ac78b";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray data = null;
                try {
                    data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject photoJson = data.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto();
                        photo.setUserName(photoJson.getJSONObject("user").getString("username"));
                        photo.setProfileImageUrl(photoJson.getJSONObject("user").getString("profile_picture"));
                        photo.setCaption(photoJson.getJSONObject("caption").getString("text"));
                        photo.setImageUrl(photoJson.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
                        photo.setImageHeight(photoJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"));
                        photo.setImageWidth(photoJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("width"));
                        photo.setLikesCount(photoJson.getJSONObject("likes").getInt("count"));
                        photo.setCreatedTime(photoJson.getString("created_time"));
                        photo.setCommentsCount(photoJson.getJSONObject("comments").getInt("count"));
                        UserComment userComment = new UserComment(photoJson.getJSONObject("comments").getJSONArray("data").getJSONObject(0).getJSONObject("from").getString("username"),
                                photoJson.getJSONObject("comments").getJSONArray("data").getJSONObject(0).getString("text"));
                        photo.setUserComment(userComment);
                        photos.add(photo);
                    }
                } catch (Exception e) {
                    //no-op
                }
                photosAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
