package baway.com.rongyun;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by AMing on 15/10/27.
 * Company RongCloud
 */
public class ConversationActivity extends FragmentActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.conversation_activity);

        mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText(getIntent().getData().getQueryParameter("title")); //获取用户名

        Log.e("type", "type is:" + getIntent().getData().getPath());
    }
}
