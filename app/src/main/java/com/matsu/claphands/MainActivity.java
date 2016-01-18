package com.matsu.claphands;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    //SoundPool(効果音再生)
    SoundPool mSoundPool;
    //soundの登録
    int sound;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SoundPoolのインスタンス作成
        //Android5.0(API 21)からは非推奨になった
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);

        //効果音のリロード(Context,リソースのID,優先度)
        sound = mSoundPool.load(this,R.raw.clapmusic,1);

        //ImageButtonの取得
        imageButton = (ImageButton)findViewById(R.id.imagebutton);
        //開いてる手の画像を設定
        imageButton.setBackgroundResource(R.drawable.openhand);

        //OnTouchListenerへ
        imageButton.setOnTouchListener(new TouchListener());
    }

    class TouchListener implements View.OnTouchListener{
        public boolean onTouch(View v, MotionEvent e){
            if(e.getAction() == MotionEvent.ACTION_DOWN){      //ボタンを押した状態

                //効果音流す(soundId,leftVolume,rightVolume,優先度,ループ0:しない 1:する,再生速度0.5~2.0)
                mSoundPool.play(sound,0.5f,0.5f,0,0,1);
                //ImageButtonに閉じた手の画像を設定
                imageButton.setBackgroundResource(R.drawable.closehand);
            }
            else if(e.getAction() ==MotionEvent.ACTION_UP){    //ボタンを離した状態
                //ImageButtonに開いた手の画像に戻す
                imageButton.setBackgroundResource(R.drawable.openhand);
            }
            return  true;
        }
    }

}
