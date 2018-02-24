package com.handsomezhou.xdesktophelper.application;

import android.app.Application;
import android.content.Context;

import com.handsomezhou.xdesktophelper.constant.MiConstant;
import com.handsomezhou.xdesktophelper.constant.XfyunConstant;
import com.handsomezhou.xdesktophelper.util.TimeUtil;
import com.iflytek.cloud.SpeechUtility;
import com.xiaomi.mistatistic.sdk.MiStatInterface;

public class XDesktopHelperApplication extends Application {
	private static Context sContext;

	@Override
	public void onCreate() {
		super.onCreate();
		sContext = getApplicationContext();

		initXfyunSpeechRecognizer();
		initMiStat();
	}

	public static Context getContext() {
		return sContext;
	}

	private void initXfyunSpeechRecognizer() {
		// 应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
		// 如在Application中调用初始化，需要在Mainifest中注册该Applicaiton
		// 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
		// 参数间使用半角“,”分隔。
		// 设置你申请的应用appid,请勿在'='与appid之间添加空格及空转义符

		// 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误

		SpeechUtility.createUtility(XDesktopHelperApplication.this, "appid=" + XfyunConstant.APP_ID);

		// 以下语句用于设置日志开关（默认开启），设置成false时关闭语音云SDK日志打印
		// Setting.setShowLog(false);
	}

	/**
	 * 初始化小米统计
	 */
	private void initMiStat(){
		MiStatInterface.initialize(this, MiConstant.APP_ID, MiConstant.APP_KEY, MiConstant.CHANNEL);
		long UPLOAD_POLICY_INTERVAL_MS= TimeUtil.min2ms(5);
		MiStatInterface.setUploadPolicy(MiStatInterface.UPLOAD_POLICY_INTERVAL, UPLOAD_POLICY_INTERVAL_MS);//设置上报策略
		//MiStatInterface.setUploadPolicy(MiStatInterface.UPLOAD_POLICY_REALTIME, 0);//设置上报策略
		MiStatInterface.enableExceptionCatcher(true);//崩溃日志收集
	}
}
