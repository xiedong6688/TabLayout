package ls.example.tablayouttitle;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TabLayout tl_titile;
    private ViewPager vp_data;
    private String[] items = new String[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        tl_titile = (TabLayout) findViewById(R.id.tl_titile);
        vp_data = (ViewPager) findViewById(R.id.vp_data);
    }

    private void initData() {
        for (int i = 0; i < items.length; i++) {
            items[i] = "第" + i + "天";
        }
    }

    private void initListener() {
        tl_titile.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_data.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tl_titile.setTabsFromPagerAdapter(mPagerAdapter);

        final TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(tl_titile);
        vp_data.addOnPageChangeListener(listener);
        vp_data.setAdapter(mPagerAdapter);


    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public CharSequence getPageTitle(int position) {
            return items[position];
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(View container, int position) {
            TextView tv = new TextView(MainActivity.this);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(35.f);
            tv.setText("我是" + items[position]);
            ((ViewPager) container).addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    };
}
