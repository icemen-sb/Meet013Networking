package ru.relastic.meet013networking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAdapterAsyncImageLoad extends AsyncTask<String, Integer, Bitmap> {
    public static final String BASE_URL = "http://openweathermap.org/img/w/";
    public static final String FILE_EXT = ".png";
    final Context context;
    final ImageView imageView;
    final String filename;
    final URL url;

    MyAdapterAsyncImageLoad(Context context, ImageView imageView, String icon_id) {

        this.context = context;
        this.imageView = imageView;
        this.filename = icon_id+FILE_EXT;
        URL url_tmp = null;
        try {
            url_tmp = new URL(BASE_URL+icon_id+FILE_EXT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.url = url_tmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap retVal = getLocalStorage();
        if (retVal == null) {
            retVal = getRemoteStorage();
        }
        return retVal;
    }
    private Bitmap getLocalStorage() {
        Bitmap retVal = null;
        File mFileDir = context.getFilesDir();
        File file = new File(mFileDir,filename);
        FileInputStream fis=null;
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                retVal = BitmapFactory.decodeStream(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fis != null) {
                    try {fis.close();} catch (IOException e) {e.printStackTrace();}
                }
            }
        }
        return retVal;
    }
    private Bitmap getRemoteStorage() {
        HttpURLConnection connection = null;
        Bitmap retVal = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                retVal = BitmapFactory.decodeStream(connection.getInputStream());
            }
        } catch (IOException e) {
            //
        }finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        if (retVal != null) {
            setLocalStorage(retVal);
        }
        return retVal;
    }
    private void setLocalStorage(Bitmap bitmap) {
        File mFileDir = context.getFilesDir();
        File file = new File(mFileDir,filename);
        FileOutputStream fos = null;
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,fos);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null) {
                try {fos.close();} catch (IOException e) {e.printStackTrace();}
            }
        }
    }
}
