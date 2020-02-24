package com.example.jinliyu.ymlandroidchallenge.ui.home;

import android.support.annotation.NonNull;

import com.example.jinliyu.ymlandroidchallenge.model.User;

import java.util.List;

public interface HomeFragmentContract {
    interface IView {
        void initRecyclerView (List<User> userList);
    }

    interface IPresenter {
        void getFollowersCall(@NonNull String userName);
        void unsubscribeAll();
    }
}
