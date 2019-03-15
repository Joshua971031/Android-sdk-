package io.cryptonym.chatroom;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import io.agora.chatroom.R;
import io.agora.rtc.Constants;
import io.cryptonym.utils.Constant;

public class MainActivity extends AppCompatActivity {
    private Banner mBanner;
    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;
    private void initData() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.a1);
        imagePath.add(R.drawable.a2);
        imagePath.add(R.drawable.splash);
        imageTitle.add("");
        imageTitle.add("");
        imageTitle.add("");
    }

    private void initView() {
        mMyImageLoader = new MyImageLoader();
        mBanner = (Banner) findViewById(R.id.banner);
        //设置样式，里面有很多种样式可以自己都看看效果
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        mBanner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        mBanner.setImages(imagePath)

                //开始调用的方法，启动轮播图。
                .start();

    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }


    public void clickRoomGamingStandard(View view) {
        onClickJoin(Constant.ChatRoomGamingStandard, Constant.ChatRoomGamingStandardName, "游戏聊天室");
    }

    public void clickRoomEntertainmentStandard(View view) {
        onClickJoin(Constant.ChatRoomEntertainmentStandard, Constant.ChatRoomEntertainmentStandardName, "电台聊天室");
    }

    public void clickRoomEntertainmentHighQuality(View view) {
        onClickJoin(Constant.ChatRoomEntertainmentHighQuality, Constant.ChatRoomEntertainmentHighQualityName, "K歌房");
    }

    public void clickRoomGamingHighQuality(View view) {
        onClickJoin(Constant.ChatRoomGamingHighQuality, Constant.ChatRoomGamingHighQualityName, "交友聊天室");
    }

    public void clickSearchRoom(View view){
        EditText editText=(EditText)findViewById(R.id.edit_text);
        String inputText=editText.getText().toString();
        onClickJoin(Constant.ChatRoomEntertainmentHighQuality, inputText, inputText+"房间");

    }
    public void onClickJoin(final int roomId, final String roomName, final String titleName) {
        // show dialog to choose role
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.msg_choose_role);
        builder.setNegativeButton(R.string.label_audience, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startRoomActivity(Constants.CLIENT_ROLE_AUDIENCE, roomId, roomName, titleName);
            }
        });
        builder.setPositiveButton(R.string.label_broadcaster, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startRoomActivity(Constants.CLIENT_ROLE_BROADCASTER, roomId, roomName, titleName);
            }
        });
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void startRoomActivity(int clientRole, int roomId, String roomName, String titleName) {
        Intent intent = new Intent(this, RoomActivity.class);
        intent.putExtra(Constant.ACTION_KEY_CROLE, clientRole);
        intent.putExtra(Constant.ACTION_KEY_ROOM_MODE, roomId);
        intent.putExtra(Constant.ACTION_KEY_ROOM_NAME, roomName);
        intent.putExtra(Constant.ACTION_KEY_TITLE_NAME, titleName);
        startActivity(intent);
    }
}
