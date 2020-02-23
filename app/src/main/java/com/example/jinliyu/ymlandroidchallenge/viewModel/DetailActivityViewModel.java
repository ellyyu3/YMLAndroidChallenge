package com.example.jinliyu.ymlandroidchallenge.viewModel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.jinliyu.ymlandroidchallenge.R;
import com.example.jinliyu.ymlandroidchallenge.data.GitHubUsersApi;
import com.example.jinliyu.ymlandroidchallenge.data.RetrofitInstance;
import com.example.jinliyu.ymlandroidchallenge.model.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailActivityViewModel extends ViewModel {
    @NonNull private final GitHubUsersApi gitHubUsersApi = RetrofitInstance.getRetrofitInstance().create(GitHubUsersApi.class);
    @NonNull private final CompositeDisposable disposables = new CompositeDisposable();
    @NonNull private final static String TAG = "detailActivityViewModel";
    @NonNull public ObservableField<String> userName = new ObservableField<>("");
    @NonNull public ObservableField<String> bio = new ObservableField<>("");
    @NonNull public ObservableField<String> followers = new ObservableField<>("");
    @NonNull public ObservableField<String> following = new ObservableField<>("");
    @NonNull public ObservableField<String> location = new ObservableField<>("");
    @NonNull public ObservableField<String> repositories = new ObservableField<>("");
    @NonNull public ObservableField<String> email = new ObservableField<>("");
    @NonNull public ObservableField<String> company = new ObservableField<>("");
    @NonNull public ObservableBoolean showLocation = new ObservableBoolean(false);
    @NonNull public ObservableBoolean showEmail = new ObservableBoolean(false);
    @NonNull public ObservableBoolean showCompany = new ObservableBoolean(false);
    ObservableField<String> imgUrl = new ObservableField<>("");

    public void getUserDetails(ImageView imageView, String userName) {
        disposables.add(gitHubUsersApi.getUserDetail(userName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    setImageView(imageView, user.avatar_url);
                    setUserDetail(user);
                }, throwable -> Log.i(TAG, throwable.getMessage())));
    }

    private void setImageView(ImageView profileImageView, String url) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(profileImageView.getContext())
                .load(url)
                .apply(options)
                .into(profileImageView);
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    private void setUserDetail(User user) {
        this.userName.set(user.login);
        this.bio.set(user.bio);
        this.followers.set(String.valueOf(user.followers));
        this.following.set(String.valueOf(user.following));
        this.repositories.set(String.valueOf(user.public_repos));
        this.location.set(user.location);
        this.email.set(user.email);
        this.company.set(user.company);
        this.showLocation.set(!(user.location == null ||user.location.equals("")));
        this.showEmail.set(!(user.email == null || user.email.equals("")));
        this.showCompany.set(!(user.company == null || user.company.equals("")));
        this.imgUrl.set(user.avatar_url);
    }



}
