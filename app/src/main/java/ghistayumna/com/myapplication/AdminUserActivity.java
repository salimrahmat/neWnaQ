package ghistayumna.com.myapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import ghistayumna.com.myapplication.Fragment.PageFragment;
import ghistayumna.com.myapplication.Fragment.PageFragmentTwo;

public class AdminUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PageFragment(),"pengumuman");
        adapter.addFragment(new PageFragmentTwo(),"testing deui");
//        adapter.addFragment(PageFragment.newInsance("dua"),"dua");
//        adapter.addFragment(PageFragment.newInsance("tiga"),"tiga coi");
        viewPager.setAdapter(adapter);
        //viewPagerAdapter.addFragment(new AnnouncementContentFragment(), getResources().getString(R.string.tab_announcement));
    }


}
