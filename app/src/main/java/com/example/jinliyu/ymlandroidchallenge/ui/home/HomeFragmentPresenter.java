package com.example.jinliyu.ymlandroidchallenge.ui.home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.jinliyu.ymlandroidchallenge.data.GitHubUsersApi;
import com.example.jinliyu.ymlandroidchallenge.data.RetrofitInstance;
import com.example.jinliyu.ymlandroidchallenge.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragmentPresenter implements HomeFragmentContract.IPresenter{
    @NonNull
    private final GitHubUsersApi gitHubUsersApi = RetrofitInstance.getRetrofitInstance().create(GitHubUsersApi.class);
    @NonNull private final static String TAG_HOME_FRAGMENT = "homeFragment";
    @NonNull private final CompositeDisposable disposables = new CompositeDisposable();
    private HomeFragmentContract.IView homeFragment;

    public HomeFragmentPresenter(HomeFragmentContract.IView homeFragment) {
        this.homeFragment = homeFragment;
    }
    @Override
    public void getFollowersCall(String userName) {
        Observable<List<User>> followersObservable = gitHubUsersApi.getFollowersByUsername(userName);
        disposables.add(followersObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(followers -> {
                            homeFragment.initRecyclerView(followers);
                        }
                        , throwable -> Log.i(TAG_HOME_FRAGMENT, throwable.getMessage())));
    }

    @Override
    public void unsubscribeAll() {
        disposables.clear();
    }
}
