package com.sjl.tinkerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.sjl.loglib.Fix;
import com.sjl.loglib.log.LogUtil;
import com.sjl.tinkerdemo.util.PermisstionUtil;
import com.tencent.tinker.lib.library.TinkerLoadLibrary;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btnLoadPatch;
    private Button btnClearPatch;
    private Button btnLoadLib;
    private Button btnShowInfo;
    private Button btnJump;
    private Button btnLog1;
    private Button btnLog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnLoadPatch = (Button) findViewById(R.id.btnLoadPatch);
        btnLoadPatch.setOnClickListener(this);
        btnClearPatch = (Button) findViewById(R.id.btnClearPatch);
        btnClearPatch.setOnClickListener(this);
        btnLoadLib = (Button) findViewById(R.id.btnLoadLib);
        btnLoadLib.setOnClickListener(this);
        btnShowInfo = (Button) findViewById(R.id.btnShowInfo);
        btnShowInfo.setOnClickListener(this);
        btnJump = (Button) findViewById(R.id.btnJump);
        btnJump.setOnClickListener(this);
        btnLog1 = (Button) findViewById(R.id.btnLog1);
        btnLog1.setOnClickListener(this);
        btnLog2 = (Button) findViewById(R.id.btnLog2);
        btnLog2.setOnClickListener(this);

        PermisstionUtil.requestPermissions(this,new String[]{PermisstionUtil.STORAGE},PermisstionUtil.STORAGE_CODE,"",null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoadPatch:
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                        Environment.getExternalStorageDirectory() + "/patch.apk");
                break;
            case R.id.btnClearPatch:
                Tinker.with(getApplicationContext()).cleanPatch();
                break;
            case R.id.btnLoadLib:
                TinkerLoadLibrary.installNavitveLibraryABI(this,
                        "armeabi");
                break;
            case R.id.btnShowInfo:
                LogUtil.e(TAG, "VERSION_NAME:"+BuildConfig.VERSION_NAME);
                break;
            case R.id.btnJump:
                LogUtil.e(TAG,"jump");
                startActivity(new Intent(this,AnotherActivity.class));
                break;
            case R.id.btnLog1:
                LogUtil.i(TAG,"log1");
                break;
            case R.id.btnLog2:
                LogUtil.i(TAG,"log2"+ Fix.getData());
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermisstionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
