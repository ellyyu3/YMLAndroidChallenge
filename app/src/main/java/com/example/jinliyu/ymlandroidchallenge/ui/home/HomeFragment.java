package com.example.jinliyu.ymlandroidchallenge.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jinliyu.ymlandroidchallenge.R;
import com.example.jinliyu.ymlandroidchallenge.adapter.UsersAdapter;
import com.example.jinliyu.ymlandroidchallenge.model.User;
import com.example.jinliyu.ymlandroidchallenge.ui.DetailActivity;

import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment implements HomeFragmentContract.IView {
    private RecyclerView recyclerView;
    @NonNull private List<User> followerList = Collections.emptyList();
    private HomeFragmentContract.IPresenter presenter;
    private final static String KEY_USER_NAME = "userName";
    private String userName = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            userName = bundle.getString(KEY_USER_NAME);
        }
    }

    public static HomeFragment newInstance(String userName) {
        Bundle args = new Bundle();
        args.putString(KEY_USER_NAME, userName);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.users_recyclerview);
        presenter = new HomeFragmentPresenter(this);
        presenter.getFollowersCall(userName);
        return view;
    }

    UsersAdapter.RecyclerViewClickListener recyclerViewClickListener = (view, position) -> {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(KEY_USER_NAME, followerList.get(position).login);
        startActivity(intent);
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribeAll();
    }

    @Override
    public void initRecyclerView(List<User> userList) {
        this.followerList = userList;
        UsersAdapter usersAdapter = new UsersAdapter(getContext(), userList, recyclerViewClickListener);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(usersAdapter);
    }

}
