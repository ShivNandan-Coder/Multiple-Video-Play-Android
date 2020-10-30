package com.example.videom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  MediaPlayer.OnCompletionListener{


    Button clk;
    VideoView videov;
    MediaController mediaC;

   ArrayList<Integer> videolist = new ArrayList<>();

   // int n=5;
  /*  String str[]={"first","five"};
    ArrayList<String> videolist = new ArrayList<String>();*/
    int currvideo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   clk = findViewById(R.id.button);
        videov = findViewById(R.id.videoView);

        videov.setMediaController(new MediaController(this));
        videov.setOnCompletionListener(this);


    /*   for(int i=0;i<=str.length;i++)
            videolist.add(str[i]);*/

     //   mediaC = new MediaController(this);
 videolist.add(R.raw.first);
 //videolist.add(R.raw.aeroplane);
 videolist.add(R.raw.faded);
 videolist.add(R.raw.middle);
// videolist.add(R.raw.second);
//videolist.add(R.raw.second);
setVideo(videolist.get(0));
    }

    public void setVideo(int currvideo)
    {
        String uriPath="android.resource://com.example.videom/" + getPackageName() + "/" + currvideo;

        Uri uri=Uri.parse(uriPath);
        videov.setVideoURI(uri);
        videov.start();
    }
  /*  public void videoplay(View v) {
        String videopath = "android.resource://com.example.videom/" + R.raw.first;
        Uri uri = Uri.parse(videopath);
        videov.setVideoURI(uri);

        videov.setMediaController(mediaC);
        mediaC.setAnchorView(videov);

        videov.start();
    }*/

  public void onCompletion(MediaPlayer mediaPlayer)
  {
      AlertDialog.Builder obj=new AlertDialog.Builder(this);
      obj.setTitle("Playback finished");
      obj.setIcon(R.mipmap.ic_launcher);
      MyListener m=new MyListener();
      obj.setPositiveButton("Replay",m);
      obj.setNegativeButton("Next",m);
      obj.setMessage("Want to replay or play next video");
      obj.show();
  }
  class MyListener implements DialogInterface.OnClickListener
  {
      public void onClick(DialogInterface dialog, int which) {
          if(which==-1)
          {
              videov.seekTo(0);
              videov.start();
          }
          else
          {
              ++currvideo;
              if(currvideo == videolist.size())
                  currvideo = 0;
              setVideo(videolist.get(currvideo));
          }
      }
  }


}