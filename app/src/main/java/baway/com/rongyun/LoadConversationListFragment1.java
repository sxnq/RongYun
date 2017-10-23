package baway.com.rongyun;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * Created by AMing on 15/10/27.
 * Company RongCloud
 */
public class LoadConversationListFragment1 extends FragmentActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.load_fragment1);
    }

}
