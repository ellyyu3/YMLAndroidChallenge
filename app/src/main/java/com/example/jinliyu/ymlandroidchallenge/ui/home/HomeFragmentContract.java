package com.example.jinliyu.ymlandroidchallenge.ui.home;

import com.example.jinliyu.ymlandroidchallenge.model.User;

import java.util.List;

public interface HomeFragmentContract {
    interface IView {
        void initRecyclerView (List<User> userList);
    }

    interface IPresenter {
        void getFollowersCall(String userName);
        void unsubscribeAll();
    }
}
