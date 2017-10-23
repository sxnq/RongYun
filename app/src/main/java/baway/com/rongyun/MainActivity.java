
package baway.com.rongyun;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends FragmentActivity implements View.OnClickListener/*, RongIM.UserInfoProvider*/ {

    protected static final String TAG = "MainActivity";

    private String mUserId;

    private List<Friend> userIdList;

    private static final String token1 = "wzhXhiOJrc7bEoNLj+trMM0ygwMV+BOx6MOAuaRHCIq4MdpqiwJtW7Gy+CAvPksJYrbuSc6da4vl6WictTgKeg==";

    private static final String token2 = "qkPGVImT+GSTAf9UGym2iM0ygwMV+BOx6MOAuaRHCIq4MdpqiwJtW9+m8YRwnN2wlmFSLJZdHme+c3y+yDyWXw==";

    private Button mUser1, mUser2, mLoadFragment1, mLoadFragment2,mChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

//        userIdList = new ArrayList<Friend>();
//        userIdList.add(new Friend("10010", "联通", "http://www.51zxw.net/bbs/UploadFile/2013-4/201341122335711220.jpg"));
//        userIdList.add(new Friend("10086", "移动", "http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));


        //加头像用
//        RongIM.setUserInfoProvider(MainActivity.this, true);


    }

    private void init() {
        mUser1 = (Button) findViewById(R.id.connect_10010);
        mUser2 = (Button) findViewById(R.id.connect_10086);
        mLoadFragment1 = (Button) findViewById(R.id.load1);
        mLoadFragment2 = (Button) findViewById(R.id.load2);
        mChat = (Button) findViewById(R.id.chat);

        mUser1.setOnClickListener(this);
        mUser2.setOnClickListener(this);
        mLoadFragment1.setOnClickListener(this);
        mLoadFragment2.setOnClickListener(this);
        mChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.connect_10010) {
            connectRongServer(token1);
        } else if (v.getId() == R.id.connect_10086) {
            connectRongServer(token2);
        } else if (v.getId() == R.id.load1) {
//            //刷新用户信息头像
//            RongContext.getInstance().getUserInfoCache().put(
//                    mUserId.equals("10010") ? "10086" : "10010",
//                    new UserInfo(mUserId.equals("10010") ? "10086" : "10010",mUserId.equals("10010") ? "我曾经是移动" : "我曾经是联通",
//                            mUserId.equals("10010") ? Uri.parse("http://static.yingyonghui.com/screenshots/1657/1657011_4.jpg") : Uri.parse("http://i5.hexunimg.cn/2012-11-07/147694350.jpg"))
//            );
        } else if(v.getId() == R.id.load2){
            startActivity(new Intent(MainActivity.this,
                    LoadConversationListFragment2.class));
        }else if(v.getId() == R.id.chat) {


            if (mUserId != null && RongIM.getInstance() != null)
                //此处聊天是写死的 实际开发中 大家应该写成动态的

           //第一个参数 ：上下文   第二个参数  ：与谁聊天响应的userId  第三个参数 ：要聊天人的名字

                RongIM.getInstance().startPrivateChat(MainActivity.this, mUserId.equals("10010") ? "10086" : "10010", mUserId.equals("10010") ? "移动" : "联通");
        }
    }

    /**
     * 连接融云服务器
     *
     * @param token
     */
    private void connectRongServer(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            @Override
            public void onSuccess(String userId) {
                mUserId = userId;
                if (userId.equals("10010")) {
                    mUser1.setText("用户1连接服务器成功");
                    mUser2.setClickable(false);
                    mUser2.setTextColor(Color.GRAY);
                    Toast.makeText(MainActivity.this, "connet server success",
                            Toast.LENGTH_SHORT).show();
                    ;
                } else {
                    mUser2.setText("用户2连接服务器成功");
                    mUser1.setClickable(false);
                    mUser1.setTextColor(Color.GRAY);
                    Toast.makeText(MainActivity.this, "connet server success",
                            Toast.LENGTH_SHORT).show();
                    ;
                }
                Log.e(TAG, "connect success userid is :" + userId);

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG,
                        "connect failure errorCode is :" + errorCode.getValue());
            }

            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "token is error , please check token and appkey ");
            }
        });
    }

//    @Override
//    public UserInfo getUserInfo(String s) {
//        for (Friend i : userIdList) {
//            if (i.getUserId().equals(s)) {
//                Log.e(TAG, i.getPortraitUri());
//                return new UserInfo(i.getUserId(),i.getUserName(), Uri.parse(i.getPortraitUri()));
//            }
//        }
//        Log.e("MainActivity","UserId is ：" +s );
//        return null;
//    }
}
