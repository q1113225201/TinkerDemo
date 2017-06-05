package com.sjl.tinkerdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.sjl.tinkerdemo.reporter.SampleLoadReporter;
import com.sjl.tinkerdemo.reporter.SamplePatchListener;
import com.sjl.tinkerdemo.reporter.SamplePatchReporter;
import com.sjl.tinkerdemo.service.TinkerResultService;
import com.sjl.loglib.log.LogUtil;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.listener.PatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * TinkerApplicationLike
 *
 * @author SJL
 * @date 2017/5/31
 */
@DefaultLifeCycle(application = "com.sjl.tinkerdemo.MyTinkerApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class TinkerApplicationLike extends DefaultApplicationLike {
    private static final String TAG = "TinkerApplicationLike";

    public TinkerApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e(TAG,"MyTinkerApplication onCreate");
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
//        TinkerInstaller.install(this);
        installTinker(this);
    }

    private void installTinker(ApplicationLike applicationLike) {
        LoadReporter loadReporter = new SampleLoadReporter(applicationLike.getApplication());
        PatchReporter patchReporter = new SamplePatchReporter(applicationLike.getApplication());
        PatchListener patchListener = new SamplePatchListener(applicationLike.getApplication());
        AbstractPatch abstractPatch = new UpgradePatch();

        TinkerInstaller.install(applicationLike, loadReporter, patchReporter, patchListener, TinkerResultService.class, abstractPatch);
    }

}
