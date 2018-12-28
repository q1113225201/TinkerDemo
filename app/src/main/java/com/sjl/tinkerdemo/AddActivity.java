package com.sjl.tinkerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AddActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
