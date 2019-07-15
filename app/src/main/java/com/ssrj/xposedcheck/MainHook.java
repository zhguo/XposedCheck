package com.ssrj.xposedcheck;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

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
