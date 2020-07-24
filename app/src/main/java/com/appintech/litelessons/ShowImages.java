package com.appintech.litelessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowImages extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
    }

    public static final int REQUEST_PERMISSION = 1234;
    public static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,

    };
    public static final int PERMISSIONS_COUNT = 1;
    private boolean isGalleryInitialized;

    @SuppressLint("NewApi")
    public final boolean arePermissionDenied(){
        for(int i = 0; i < PERMISSIONS_COUNT; i++){
            if(checkSelfPermission(PERMISSIONS[i])!= PackageManager.PERMISSION_GRANTED)
                return  true;
        }
        return false;
    };

    @SuppressLint({"NewApi", "LongLogTag"})
    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permission,
                                           final int[]grantResults){
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        if(requestCode == REQUEST_PERMISSION && grantResults.length> 0){
            if(arePermissionDenied()){
                ActivityCompat.requestPermissions(this, permission, REQUEST_PERMISSION);
            }
            else{
                onResume();
            }
        }
    }

    @Override
    protected  void onResume(){

        super.onResume();
        final ListView listView = findViewById(R.id.list_view_gallery);
        final GalleryAdapter galleryAdapter = new GalleryAdapter();

        if(!isGalleryInitialized){
            final File imagesDir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));
            final File[] files = imagesDir.listFiles();
            Log.i("files ",files[1]+"");
            final int filesCount = files.length;
            final List<String> fileList = new ArrayList<>();
            for (int i = 0; i < filesCount ; i++){
                final String path = files[i].getAbsolutePath();
                if(path.endsWith(".jpg")||path.endsWith(".jpeg")||path.endsWith(".png")){
                    fileList.add(path);
                }
            }
            galleryAdapter.setData(fileList);
            listView.setAdapter(galleryAdapter);
            isGalleryInitialized = true;
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // pass the images to new activity and display the image
                    // apply the on swipe methods and go to the next image in the list
            }
        });
    }



    public class GalleryAdapter extends BaseAdapter {
        private List<String> data = new ArrayList<>();
        void setData(List<String> data){
            if(this.data.size()>0)
                data.clear();

            this.data.addAll(data);
            //after we update the data we have to tell adapter to update it to screen
            // that's why we use notifyDataSetChanged()
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //image we will show to the user
            ImageView imageView;
            if(convertView == null){
                imageView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            }
            else{
                imageView = (ImageView) convertView;
            }
            //load the image using glide
            //showImages might throw error
            Glide.with(ShowImages.this).load(data.get(position)).centerCrop().into(imageView);
            return imageView;
        }
    }

}
