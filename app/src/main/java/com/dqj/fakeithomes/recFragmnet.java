package com.dqj.fakeithomes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class recFragmnet extends Fragment {
    Context context;

    SwipeRefreshLayout mSwrl;
RecyclerView recyclerView;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static recFragmnet newInstance(int sectionNumber) {
        recFragmnet fragment = new recFragmnet();

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        recyclerView=view.findViewById(R.id.rec);
        mSwrl = (SwipeRefreshLayout) view.findViewById(R.id.swrl);

mSwrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        Snackbar.make(view,"莫法刷新！",Snackbar.LENGTH_SHORT).setAction("哦", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }
});

        return view;
    }

    public void dod(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
