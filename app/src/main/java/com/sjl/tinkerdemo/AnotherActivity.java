package com.sjl.tinkerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sjl.loglib.log.LogUtil;

public class AnotherActivity extends Activity {
    private static final String TAG = "AnotherActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e(TAG,"back");
                finish();
            }
        });
    }
}
