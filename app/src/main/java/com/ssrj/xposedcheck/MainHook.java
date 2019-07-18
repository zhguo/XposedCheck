package com.ssrj.xposedcheck;

import android.util.Log;

import com.gentcent.zzk.xped.IXposedHookLoadPackage;
import com.gentcent.zzk.xped.XC_MethodReplacement;
import com.gentcent.zzk.xped.callbacks.XC_LoadPackage.LoadPackageParam;

import static com.gentcent.zzk.xped.XposedHelpers.findAndHookMethod;

/**
 * @author zuozhi
 * @since 2019-06-21
 */
public class MainHook implements IXposedHookLoadPackage {
	
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) {
		try {
			Log.e("Xposed", lpparam.packageName);
			if (lpparam.packageName.equals("com.ssrj.xposedcheck")) {
				// Xposed模块自检测
				findAndHookMethod("com.ssrj.xposedcheck.MainActivity", lpparam.classLoader, "isModuleActive", XC_MethodReplacement.returnConstant(true));
			}
		} catch (Exception e) {
			Log.e("Xposed", e.getMessage());
		}
	}
}
