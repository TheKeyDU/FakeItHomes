package com.dqj.fakeithomes;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.dqj.fakeithomes.MainActivity;
import com.dqj.fakeithomes.R;
import com.dqj.fakeithomes.siv.SmartImageView;

public class webViewFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public webViewFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
   AppBarLayout mAppBar;
     CollapsingToolbarLayout mToolbarLayout;
     Toolbar mToolbar;
     FloatingActionButton mFab;

    WebView webView;
   SmartImageView mSiv;
   NestedScrollView mNsv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_content_fragment, container, false);
        mSiv = (SmartImageView)rootView.findViewById(R.id.siv);

        mNsv = (NestedScrollView) rootView.findViewById(R.id.nsv);

        webView = (WebView) rootView.findViewById(R.id.web);

        mAppBar = (AppBarLayout) rootView.findViewById(R.id.app_bar);
        mToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.toolbar_layout);
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        return rootView;
    }
}