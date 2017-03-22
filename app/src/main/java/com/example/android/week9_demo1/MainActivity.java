package com.example.android.week9_demo1;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String url = "rtsp://mpv.cdn3.bigCDN.com:554/bigCDN/definst/mp4:bigbuckbunnyiphone_400.";
    MediaPlayer mediaPlayer = new MediaPlayer();
    TextView mtv;
    VideoView videotv;
    WebView mWebView;

    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtv = (TextView) findViewById(R.id.tv_count);
        videotv = (VideoView) findViewById(R.id.video_view);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.loadUrl("http://www.bbc.com/sport/football");
    }

    public void countNum(View view) {
        mtv.setText("Num: " + count++);

    }

    public void playBtn(View view) throws IOException {


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                try {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mediaPlayer.start();
            }
        }.execute();

    }

    public void playVideo(View view) {

        Uri videoUri = Uri.parse(url);
        videotv.setVideoURI(videoUri);
        videotv.start();
    }
}
