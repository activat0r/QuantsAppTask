package com.activator.quantsapp;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.squareup.picasso.Downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class FileDownloadTask extends AsyncTask<String, Integer, String> {
    private Context mContext;

    FileDownloadTask(Context context){
        this.mContext = context;
    }

    @Override
    protected String doInBackground(String... urls) {
        InputStream is = null;
        OutputStream os = null;
        URL murl = null;
        try {
            murl = new URL(urls[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error forming URL";
        }


        HttpURLConnection socketConnection = null;


            try {
                socketConnection = (HttpURLConnection) murl.openConnection();
                socketConnection.connect();

                if(socketConnection.getResponseCode() != HttpURLConnection.HTTP_OK){
                    return "Error opening the file";
                }
                int length = socketConnection.getContentLength();

                is = socketConnection.getInputStream();

                os = mContext.openFileOutput("downloaded_file.xls",Context.MODE_PRIVATE);

                System.out.println(mContext.getFilesDir()+"/downloaded_file.xls");

                byte[] data = new byte[2048];
                long totalDownloaded = 0;
                int count = 0;

                while((count = is.read(data)) != -1){
                    if(isCancelled()) {
                        is.close();
                        return null;
                    }

                    totalDownloaded += count;

                    if(length > 0) {
                        System.out.println("Progress : --- "+(int) (totalDownloaded * 100 / length));
                        publishProgress((int) (totalDownloaded * 100 / length));
                        os.write(data,0,count);
                    }

                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            finally {
                try {
                    if( is != null)
                    is.close();

                    if( os != null)
                    os.close();
                }
                catch (Exception e){

                }
                if(socketConnection != null){
                    socketConnection.disconnect();
                }

            }

            return null;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
