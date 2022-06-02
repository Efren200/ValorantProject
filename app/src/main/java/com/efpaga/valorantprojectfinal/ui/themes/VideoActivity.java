package com.efpaga.valorantprojectfinal.ui.themes;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.efpaga.valorantprojectfinal.R;

public class VideoActivity extends AppCompatActivity {

    private String urlVideo;
    private VideoView mVideoView;
    private TextView mBufferingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_skin);
        urlVideo = getIntent().getStringExtra("url");
        mVideoView = findViewById(R.id.videoview);
        mBufferingTextView = findViewById(R.id.buffering_textview);
        //Configuracion del widget del controlador de medios y ajustarlo a la vista de video
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controller);
    }
    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
    }

    private void initializePlayer() {
        //Mostramos el texto de buffering
        mBufferingTextView.setVisibility(VideoView.VISIBLE);
        //Decodificando la url del video
        Uri videoUri = Uri.parse(urlVideo);
        mVideoView.setVideoURI(videoUri);
        //Una vez el video esta preparado para reproducirse
        mVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        //Ocultar el mensaje
                        mBufferingTextView.setVisibility(VideoView.INVISIBLE);
                        //Dar play al video una vez cargado
                        mVideoView.start();
                    }
                });
        //Se ejecuta una vez se haya completado el video
        mVideoView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        //Devolver el video al principio
                        mVideoView.seekTo(0);
                    }
                });
    }
}
