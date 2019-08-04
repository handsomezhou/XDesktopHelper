package com.handsomezhou.xdesktophelper.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.constant.AppPackageName;
import com.handsomezhou.xdesktophelper.constant.Constant;

import java.io.File;
import java.io.FileOutputStream;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ShareUtil {
	public static final int SHARE_SUCCESS = 0;
	public static final int SHARE_FAILED_APP_NOT_INSTALLED = 1;
	public static final int SHARE_FAILED_UNKNOW_REASON = 2;

	/**
	 * open qq
	 * @param context
	 * @return
	 */
	public static boolean enterQq(Context context){
		boolean startApp=false;
		do{
			if(null==context){
				startApp=false;
				break;
			}
			startApp=AppUtil.startApp(context, AppPackageName.PACKAGE_NAME_QQ);
			if(false==startApp){
				startApp=AppUtil.startApp(context, AppPackageName.PACKAGE_NAME_QQLITE);
		
				if(false==startApp){
					startApp=AppUtil.startApp(context, AppPackageName.PACKAGE_NAME_QQI);
			
					if(false==startApp){
						startApp=AppUtil.startApp(context, AppPackageName.PACKAGE_NAME_QQMINIHD);
						
						if(false==startApp){
							ToastUtil.toast(context, R.string.qq_not_installed,Toast.LENGTH_SHORT);
						}
					}
				}
				
			}
		}while(false);
	
		return startApp;
	}
	
	/**
	 * open wechat
	 * @param context
	 * @return
	 */
	public static boolean enterWechat(Context context){
		boolean startApp=false;
		do{
			if(null==context){
				startApp=false;
				break;
			}
			
			startApp=AppUtil.startApp(context, AppPackageName.PACKAGE_NAME_WECHAT);
			
			if(false==startApp){
				ToastUtil.toast(context,R.string.wechat_not_installed,
						Toast.LENGTH_SHORT);
			}
		}while(false);
		
		return startApp;
	}
	
	/**
	 * open weibo
	 * @param context
	 * @return
	 */
	public static boolean enterWeibo(Context context){
		boolean startApp=false;
		do{
			if(null==context){
				startApp=false;
				break;
			}
			startApp=AppUtil.startApp(context, AppPackageName.PACKAGE_NAME_SINA_WEIBO);
			if(false==startApp){
				startApp=AppUtil.startApp(context, AppPackageName.PACKAGE_NAME_SINA_WEIBOG3);
		
				if(false==startApp){
					startApp=AppUtil.startApp(context, AppPackageName.PACKAGE_NAME_SINA_WEIBOHD);
			
					if(false==startApp){
						startApp=AppUtil.startApp(context, AppPackageName.PACKAGE_NAME_SINA_WEIBOPRO);
						
						if(false==startApp){
							ToastUtil.toast(context,R.string.sina_micro_bo_not_installed,Toast.LENGTH_SHORT);
						}
					}
				}
				
			}
		}while(false);
	
		return startApp;
	}
	/**
	 * 微信分享文字信息
	 * 
	 * @param context
	 * @param textContent
	 */
	public static void shareTextToWechat(Context context, String textContent) {
		int ret=shareToApp(context, textContent,AppPackageName.PACKAGE_NAME_WECHAT);
		if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
			ToastUtil.toast(context,R.string.wechat_not_installed,
					Toast.LENGTH_SHORT);
		}
	}

	/**
	 * QQ分享文字信息
	 * 
	 * @param context
	 * @param textContent
	 */
	public static void shareTextToQq(Context context, String textContent) {
		int ret=shareToApp(context, textContent,AppPackageName.PACKAGE_NAME_QQ);
		if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
			ret=shareToApp(context, textContent,AppPackageName.PACKAGE_NAME_QQLITE);
			if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
				ret=shareToApp(context, textContent,AppPackageName.PACKAGE_NAME_QQI);
				if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
					ret=shareToApp(context, textContent,AppPackageName.PACKAGE_NAME_QQMINIHD);
					if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
						ToastUtil.toast(context,R.string.qq_not_installed,Toast.LENGTH_SHORT);
					}
				}
			}
			
		}
	}

	/**
	 * 腾讯微博分享文字信息
	 * 
	 * @param context
	 * @param textContent
	 */
	public static void shareTextToTencentWeibo(Context context, String textContent) {
		int ret=shareToApp(context, textContent,AppPackageName.PACKAGE_NAME_TENCENT_WEIBO);
		if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
			ToastUtil.toast(context,R.string.tencent_micro_bo_not_installed,
					Toast.LENGTH_SHORT);
		}
	}

	/**
	 * 新浪微博分享文字信息
	 * 
	 * @param context
	 * @param textContent
	 */
	public static void shareTextToSinaWeibo(Context context, String textContent) {
		int ret=shareToApp(context, textContent,AppPackageName.PACKAGE_NAME_SINA_WEIBO);
		if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
			ret=shareToApp(context, textContent, AppPackageName.PACKAGE_NAME_SINA_WEIBOG3);
			if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
				ret=shareToApp(context, textContent, AppPackageName.PACKAGE_NAME_SINA_WEIBOHD);
					if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
						ret=shareToApp(context, textContent, AppPackageName.PACKAGE_NAME_SINA_WEIBOHD);
						if(SHARE_FAILED_APP_NOT_INSTALLED==ret){
							ToastUtil.toast(context,R.string.sina_micro_bo_not_installed,
									Toast.LENGTH_SHORT);
						}
							
						
					}
				
				
			}
		}
	}

	/**
	 * 短信分享文字信息
	 * 
	 * @param context
	 * @param RecipientsPhoneNumber
	 *            用于接收联系人号码格式为"phoneNumber1;phoneNumber2;..."
	 * @param textContent
	 *            发送文字内容
	 */
	public static void shareTextToSms(Context context,
			String RecipientsPhoneNumber, String textContent) {
		Intent share = new Intent(Intent.ACTION_VIEW);

		share.putExtra("address", RecipientsPhoneNumber);
		share.putExtra("sms_body", textContent);
		share.setType("vnd.android-dir/mms-sms");

		context.startActivity(share);
	}

	/**
	 * 邮件分享文字信息
	 * 
	 * @param context
	 * @param subject
	 *            邮件主题
	 * @param textContent
	 *            邮件文字内容
	 */
	public static void shareTextToEmail(Context context, String subject,
			String textContent) {

		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("message/rfc822");
		// share.putExtra(Intent.EXTRA_TITLE, "TITLE");
		share.putExtra(Intent.EXTRA_SUBJECT, subject);
		share.putExtra(Intent.EXTRA_TEXT, textContent);

		context.startActivity(share);
	}

	/**
	 * 更多分享文字信息
	 * @param context
	 * @param title  
	 * 			标题选择
	 * @param textContent
	 *			分享文字内容
	 */
	public static void shareTextToMore(Context context,String title, String textContent){
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("text/plain");
		share.putExtra(Intent.EXTRA_TEXT, textContent);

		context.startActivity(Intent.createChooser(share,title));
		
		return;
	}

	/**
	 *
	 * @param context
	 * @param title
	 * @param imageView
	 * @param imageName
	 */
	/**
	 *
	 * @param context
	 * @param title
	 * @param imageView
	 * @param imageName
	 */
	public static void shareImageToMore(Context context, String title,String imageName){
		File root = Environment.getExternalStorageDirectory();
		String DCIM_CAMERA_PATH=root.getAbsolutePath() +"/DCIM/Camera/";
		String IMAGE_SUFFIX_JPG=".jpg";

		if(CommonUtil.isEmpty(imageName)){
			imageName=TimeUtil.getLogTime();
		}


		Intent share = new Intent(Intent.ACTION_SEND);
		share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		share.setType("image/*");
		share.putExtra(Intent.EXTRA_STREAM, Uri.parse(DCIM_CAMERA_PATH +imageName+ IMAGE_SUFFIX_JPG));
		context.startActivity(Intent.createChooser(share,title));
	}

	/**
	 *
	 * @param context
	 */
	public static void openGallery(Context context){
		do{
			if(null==context){
				break;
			}

			Intent intent = new Intent();
			intent.setAction(android.content.Intent.ACTION_VIEW);
			intent.setType("image/*");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}while (false);
	}

	public static void shareInstalledApp(Context context, String packageName){
		do{
			if(null==context){
				break;
			}

			if(CommonUtil.isEmpty(packageName)){
				break;
			}

			PackageInfo packageInfo=AppUtil.getPackageInfo(context,packageName);
			if(null==packageInfo){
				break;
			}

			/**
			 * reference:
			 * https://github.com/bparmentier/APKShare
			 */
			File file=new File(packageInfo.applicationInfo.sourceDir);

			Intent shareApkIntent = new Intent();
			shareApkIntent.setAction(Intent.ACTION_SEND);
			shareApkIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
			shareApkIntent.setType("application/vnd.android.package-archive");

			String shareInfo=context.getString(R.string.share)+ Constant.COLON+AppUtil.getAppName(context,packageName);
			context.startActivity(Intent.createChooser(shareApkIntent, shareInfo));

		}while (false);
	}
	
	private static int shareToApp(Context context, String textContent, String packageName) {
		if (!AppUtil.isAppExist(context, packageName)) {
			return SHARE_FAILED_APP_NOT_INSTALLED;
		}

		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("text/plain");
		share.setPackage(packageName);
		share.putExtra(Intent.EXTRA_TEXT, textContent);
		context.startActivity(Intent.createChooser(share, context
				.getResources().getText(R.string.please_select)));

		return SHARE_SUCCESS;

	}
	
	/**
	 * share text by sms
	 * 
	 * @param context
	 * @param RecipientsPhoneNumber	phoneNumberformat:"phoneNumber1;phoneNumber2;..."
	 * @param textContent
	 *          
	 */
	public static void shareTextBySms(Context context,
			String RecipientsPhoneNumber, String textContent) {
		Intent share = new Intent(Intent.ACTION_VIEW);

		share.putExtra("address", RecipientsPhoneNumber);
		share.putExtra("sms_body", textContent);
		share.setType("vnd.android-dir/mms-sms");

		context.startActivity(share);
	}
	
	/**
	 * copy text
	 * @param context
	 * @param content
	 */
	public static void copyText( Context context,String content){
		ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);  
		ClipData clipData = ClipData.newPlainText("text", content);
		clipboardManager.setPrimaryClip(clipData);
	}
	
	/**
	 * paste text
	 * @param context
	 * @return the string of paste text
	 */
	public static String pasteText(Context context){
		ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clipData=clipboardManager.getPrimaryClip();
		
		return clipData.getItemAt(0).getText().toString();  
	}
}
