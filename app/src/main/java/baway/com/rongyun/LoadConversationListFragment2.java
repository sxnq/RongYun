package baway.com.rongyun;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by AMing on 15/10/27.
 * Company RongCloud
 */
public class LoadConversationListFragment2 extends FragmentActivity {

    private ViewPager mViewPager;

    /**
     * Fragment的数据适配器
     */
    private FragmentPagerAdapter mFragmentPagerAdapter;
    /**
     * ViewPager中的数据
     */
    private List<Fragment> mFragmentList;

    private Fragment mConversationListFragment;//会话列表的fragment 的声明

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.load_fragment2);

        mConversationListFragment = initConversationListFragment();
        initView();
    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.rc_viewpager);
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new OtherFragment());
        mFragmentList.add(mConversationListFragment);

        mFragmentPagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragmentList.get(i);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
    }

    /**
     * 封装的代码加载融云的会话列表的 fragment
     * @return
     */
    private Fragment initConversationListFragment() {
        ConversationListFragment fragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                .build();
        fragment.setUri(uri);
        return fragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentList = null;
        mViewPager = null;
        mFragmentPagerAdapter = null;
    }
}
