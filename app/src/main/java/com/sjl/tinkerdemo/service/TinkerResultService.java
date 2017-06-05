package com.sjl.tinkerdemo.service;

import com.sjl.loglib.log.LogUtil;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;

import java.io.File;

public class TinkerResultService extends DefaultTinkerResultService {
    private static final String TAG = "TinkerResultService";

    @Override
    public void onPatchResult(final PatchResult result) {
        if (result == null) {
            LogUtil.e(TAG, "SampleResultService received null result!!!!");
            return;
        }
        LogUtil.i(TAG, String.format("SampleResultService receive result: %s", result.toString()));

        //first, we want to kill the recover process
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());

        // is success and newPatch, it is nice to delete the raw file, and restart at once
        // for old patch, you can't delete the patch file
        if (result.isSuccess) {
            deleteRawPatchFile(new File(result.rawPatchFilePath));

            //not like TinkerResultService, I want to restart just when I am at background!
            //if you have not install tinker this moment, you can use TinkerApplicationHelper api
            if (checkIfNeedKill(result)) {
//                if (Utils.isBackground()) {
//                    TinkerLog.i(TAG, "it is in background, just restart process");
//                    restartProcess();
//                } else {
//                    //we can wait process at background, such as onAppBackground
//                    //or we can restart when the screen off
//                    TinkerLog.i(TAG, "tinker wait screen to restart process");
//                    new Utils.ScreenState(getApplicationContext(), new Utils.ScreenState.IOnScreenOff() {
//                        @Override
//                        public void onScreenOff() {
//                            restartProcess();
//                        }
//                    });
//                }
                LogUtil.e(TAG, "加载完成");
            } else {
                LogUtil.i(TAG, "已加载");
            }
        }
    }

    /**
     * you can restart your process through service or broadcast
     */
    private void restartProcess() {
        TinkerLog.i(TAG, "app is background now, i can kill quietly");
        //you can send service or broadcast intent to restart your process
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
