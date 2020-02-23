package com.example.jinliyu.ymlandroidchallenge.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.jinliyu.ymlandroidchallenge.R;
import com.example.jinliyu.ymlandroidchallenge.ui.home.HomeFragment;


public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, new StartFragment()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_id);
        menuItem.setActionView(R.layout.search_action_bar_layout);
        searchEditText = menuItem.getActionView().findViewById(R.id.action_bar_editText);
        attachKeyBoardListener();
        return true;
    }

    private void attachKeyBoardListener() {
        searchEditText.setOnKeyListener((view, keyCode, event)-> {
            if(keyCode == KeyEvent.KEYCODE_ENTER) {
                HomeFragment homeFragment = HomeFragment.newInstance(searchEditText.getText().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, homeFragment).commit();
            }
            return true;
        });
    }

}
