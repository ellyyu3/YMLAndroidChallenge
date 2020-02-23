package com.example.jinliyu.ymlandroidchallenge.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.example.jinliyu.ymlandroidchallenge.R;
import com.example.jinliyu.ymlandroidchallenge.databinding.ActivityDetailBinding;
import com.example.jinliyu.ymlandroidchallenge.viewModel.DetailActivityViewModel;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    private ImageView backButtonImg;
    ActivityDetailBinding binding;
    private final static String KEY_USER_NAME = "userName";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userName = getIntent().getExtras().getString(KEY_USER_NAME);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        DetailActivityViewModel viewModel = new DetailActivityViewModel();
        binding.setViewModel(viewModel);
        ImageView imageView = findViewById(R.id.user_detail_image);
        Objects.requireNonNull(getSupportActionBar()).setTitle(userName);
        viewModel.getUserDetails(imageView, userName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.back_id);
        menuItem.setActionView(R.layout.back_action_bar_layout);
        backButtonImg = menuItem.getActionView().findViewById(R.id.back_button_img);
        attachKeyBoardListener();
        return true;
    }

    private void attachKeyBoardListener() {
        backButtonImg.setOnClickListener(v -> finish());
    }

}
