package com.activator.quantsapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.net.URL;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link URLFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class URLFragment extends Fragment {

    private WebView webView;
    private MaterialButton downloadButton;
    private ProgressBar progressBar;
    private final String TAG = "URLFragment";
    public URLFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static URLFragment newInstance() {
        URLFragment fragment = new URLFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_url, container, false);

        downloadButton = root.findViewById(R.id.downloadButton);
        progressBar = root.findViewById(R.id.progressBar);

        webView = root.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {



            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);

            }
                                 }
        );


        String xlsURL = Uri.encode("https://qapptemporary.s3.ap-south-1.amazonaws.com/ritesh/zip_files/44418/Annexure123456&7_FO.xls");
        String url = "https://docs.google.com/gview?embedded=true&url="+xlsURL;
        System.out.println(url);
        webView.loadUrl(url);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                Toast.makeText(requireContext(),"Downloading",Toast.LENGTH_SHORT).show();
                downloadFile();
            }
        });


//        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(xlsURL));
//        startActivity(webIntent);

        return root;
    }

    private void downloadFile(){
        //create socket and download and write to internal storage
        String xlsURL = "https://qapptemporary.s3.ap-south-1.amazonaws.com/ritesh/zip_files/44418/Annexure123456&7_FO.xls";

        new FileDownloadTask(this.requireActivity().getApplicationContext()).execute(xlsURL);
    };

}