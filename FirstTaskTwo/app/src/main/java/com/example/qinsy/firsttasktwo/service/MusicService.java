package com.example.qinsy.firsttasktwo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.qinsy.firsttasktwo.R;

public class MusicService extends Service {
    public MusicService() {
    }
    MediaPlayer player=new MediaPlayer();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
        player = MediaPlayer.create(this, R.raw.music);
        player.setLooping(true);
        player.start();
    }

    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
        player.stop();
    }
}
