package com.efpaga.valorantprojectfinal.ui.themes;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.efpaga.valorantprojectfinal.R;
import com.efpaga.valorantprojectfinal.valorantapi.models.Theme;

public class VideoActivity extends AppCompatActivity {

    private String urlVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_skin);
        urlVideo =  getIntent().getStringExtra("url");

        VideoView videoView = findViewById(R.id.videoView);

        Uri uri = Uri.parse(urlVideo);

        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(videoView);

        mediaController.setMediaPlayer(videoView);

        videoView.setMediaController(mediaController);

        videoView.start();
    }
}
