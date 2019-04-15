package com.dqj.fakeithomes;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
     Handler Mhandler = new Handler() {
        public void handleMessage(Message message) {
            String a = (String) message.obj;
            switch (message.what) {
                case 1:
                    drawer.openDrawer(Gravity.START);
                    break;
                case 2:

                    mwebViewFragment.mAppBar.setExpanded(false);

            }
        }
    };

Boolean isfrist=true;
    private CoordinatorLayout mMainContent;
String head="<style>\n" +
        "  \n" +
        "img{\n" +
        " max-width:100%;\n" +
        " height:auto;\n" +
        "}\n" +
        "  \n" +
        "</style>";


    DrawerLayout drawer;
    List<bean.NewslistBean> arrayList=new ArrayList<>();
httpUntils httpUntils;
    recFragmnet mrecFragmnet=null;
    webViewFragment mwebViewFragment=null;
    private fragmentAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
public void requset(){
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://api.ithome.com/json/newslist/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    DoRequset doRequset=retrofit.create(DoRequset.class);
    Call<bean> call=doRequset.getCall();
    call.enqueue(new Callback<bean>() {
        @Override
        public void onResponse(Call<bean> call, Response<bean> response) {
             arrayList = response.body().getNewslist();
            mrecFragmnet.recyclerView.setLayoutManager(new LinearLayoutManager(mrecFragmnet.recyclerView.getContext()));
            final homeadpter holder=new homeadpter(mrecFragmnet.recyclerView.getContext(),arrayList);
            mrecFragmnet.recyclerView.setAdapter(holder);
            mwebViewFragment.mToolbarLayout.setTitle(arrayList.get(0).getTitle());
           // mwebViewFragment.mContenttx.setText(arrayList.get(0).getDescription());
           mwebViewFragment.mSiv.setImageUrl(arrayList.get(0).getImage());
           mwebViewFragment.webView.loadData("选择左边列表",null,"UFT-8");

holder.setclick(new homeadpter.setclick() {
    @Override
    public void onClick(int pos) {

        mViewPager.setCurrentItem(2);
        mwebViewFragment.mAppBar.setExpanded(true);
        mwebViewFragment.mNsv.scrollTo(100,0);
        // Toast.makeText(MainActivity.this,"uu"+arrayList.get(pos).getTitle(),Toast.LENGTH_SHORT).show();
        mwebViewFragment.mToolbarLayout.setTitle(arrayList.get(pos).getTitle());
       // mwebViewFragment.webView.set(arrayList.get(pos).getDescription());

        mwebViewFragment.mSiv.setImageUrl(arrayList.get(0).getImage());
        Mhandler.sendEmptyMessageDelayed(2,1500);
       // mContenttx.setText("hghffjygj");
        String urlstr2 =  arrayList.get(pos).getUrl();
        if (urlstr2.startsWith("https://lapi"))
        //https://m.ithome.com/html/414017.htm
            // https://lapin.ithome.com/html/digi/414017.htm
        {
            String u2[]=urlstr2.split("/");
            final String url="https://m.ithome.com/html/"+u2[u2.length-1];
      //      Log.e("-------------",url);

           // Toast.makeText(MainActivity.this,urlstr2,Toast.LENGTH_SHORT).show();

          //  mwebViewFragment.webView.loadUrl(urlstr2);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    httpUntils.Gohttp(url,2);
                    final String conetent = httpUntils.code;
                    //Log.e("------BanerImgUrl",BanerImgUrl);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mwebViewFragment.webView.getSettings().setDefaultTextEncodingName("utf-8");
                            // mwebViewFragment.webView.loadData(head+conetent,"text/html", "utf-8");
                            mwebViewFragment.webView.loadDataWithBaseURL(null, head+conetent, "text/html", "UTF-8", null);
                         //   Log.e("---------------",conetent);

                            // Toast.makeText(MainActivity.this,"Wadwadwa",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }.start();

        }
        else {


                String[] urlstr = arrayList.get(pos).getUrl().split("/");
                final String BanerImgUrl = "https://api.ithome.com/xml/newscontent/" + urlstr[2] + "/" + urlstr[3].substring(0, 3) + ".xml";
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        httpUntils.Gohttp(BanerImgUrl,2);
                        final String conetent = httpUntils.code;
                        //Log.e("------BanerImgUrl",BanerImgUrl);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //  Log.e("---------------",BanerImgUrl);
                                mwebViewFragment.webView.getSettings().setDefaultTextEncodingName("utf-8");
                                // mwebViewFragment.webView.loadData(head+conetent,"text/html", "utf-8");
                                mwebViewFragment.webView.loadDataWithBaseURL(null, head + conetent, "text/html", "UTF-8", null);
                                // Toast.makeText(MainActivity.this,"Wadwadwa",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }.start();


            }





    }

    @Override
    public void onLongClick(int pos) {

    }
});
            //Toast.makeText(MainActivity.this,  response.body().getNewslist().get(1).getDescription(),Toast.LENGTH_LONG).show();


        }

        @Override
        public void onFailure(Call<bean> call, Throwable t) {
            Toast.makeText(MainActivity.this, "获取json数据错误，协议有更新或者没网。。。",Toast.LENGTH_LONG).show();

        }
    });


}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        List<Fragment> list=new ArrayList<>();

        requset();
httpUntils=new httpUntils();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMainContent = (CoordinatorLayout) findViewById(R.id.main_content);
        mrecFragmnet=new recFragmnet();
        mrecFragmnet.dod(null);
        mwebViewFragment=new webViewFragment();
        list.add(mrecFragmnet);
        list.add(mwebViewFragment);
        mSectionsPagerAdapter = new fragmentAdapter(getSupportFragmentManager(),list);

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Mhandler.sendEmptyMessageDelayed(1, 700);

    }
public void showDW(){


}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            }
            else if (mViewPager.getCurrentItem()==1)
        {
            mViewPager.setCurrentItem(0);
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public class fragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public fragmentAdapter(FragmentManager fm, List list) {
            super(fm);
            this.list=list;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return list.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return list.size();
        }
    }


}
