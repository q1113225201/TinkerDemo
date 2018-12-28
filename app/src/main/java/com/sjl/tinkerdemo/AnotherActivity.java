package com.sjl.tinkerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnotherActivity extends Activity implements View.OnClickListener {

    private TextView tvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        initView();
    }

    private void initView() {
        tvValue = findViewById(R.id.tv_value);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_show).setOnClickListener(this);
        findViewById(R.id.btn_jump).setOnClickListener(this);
        findViewById(R.id.btn_jump2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                tvValue.setText("加固 2+2=4");
                break;
            case R.id.btn_sub:
                tvValue.setText("加固 1-1=0");
                break;
            case R.id.btn_show:
                tvValue.setText(showValue());
                break;
            case R.id.btn_jump:
                startActivity(new Intent(this, AddActivity.class));
                break;
            case R.id.btn_jump2:
                startActivity(new Intent(this, AddReinforceActivity.class));
                break;
        }
    }

    private int cnt = 0;

    public String showValue() {
        cnt += 2;
        return "加固 showValue" + cnt;
    }
}
