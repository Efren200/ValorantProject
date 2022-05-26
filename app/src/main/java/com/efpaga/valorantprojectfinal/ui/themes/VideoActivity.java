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
    private int mCurrentPosition = 0;
    private static final String PLAYBACK_TIME = "play_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_skin);

        urlVideo =  getIntent().getStringExtra("url");
        mVideoView = findViewById(R.id.videoview);
        mBufferingTextView = findViewById(R.id.buffering_textview);

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }

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
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    //para cuando pause se pare en los milisegundos exactos
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
    }

    private void initializePlayer() {
        //Mostramos el texto de buffer
        mBufferingTextView.setVisibility(VideoView.VISIBLE);

        //Decodificando la url del video
        Uri videoUri = getMedia(urlVideo);
        mVideoView.setVideoURI(videoUri);

        //Para ejecutar el video cuando ya este cargado
        mVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                        //Ocultar el mensaje
                        mBufferingTextView.setVisibility(VideoView.INVISIBLE);

                        //Restaurar la posicion guardada
                        if (mCurrentPosition > 0) {
                            mVideoView.seekTo(mCurrentPosition);
                        } else {
                            //Saltar a 1 muestra el primer cuadro del video.
                            mVideoView.seekTo(1);
                        }
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
    //Liberamos los datos cargados con la url
    private void releasePlayer() {
        mVideoView.stopPlayback();
    }

    //Para recoger la url del video y comprobar que es correcta
    private Uri getMedia(String mediaName) {
        if (URLUtil.isValidUrl(mediaName))
            return Uri.parse(mediaName);

        return Uri.parse("android.resource://" + getPackageName() +
                    "/raw/" + mediaName);
    }
}
