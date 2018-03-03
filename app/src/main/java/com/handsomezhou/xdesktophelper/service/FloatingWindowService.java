package com.handsomezhou.xdesktophelper.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.activity.MainActivity;
import com.handsomezhou.xdesktophelper.helper.AppInfoHelper;
import com.handsomezhou.xdesktophelper.helper.SettingsHelper;
import com.handsomezhou.xdesktophelper.util.ActivityUtil;
import com.handsomezhou.xdesktophelper.util.LogUtil;

/**
 * Created by handsomezhou on 2018/3/3.
 *
 * reference:
 * 1.https://github.com/marshallino16/Demo-FloatingView
 * 2.http://blog.csdn.net/findsafety/article/details/78502190
 *
 */

public class FloatingWindowService extends Service {

    private static final String TAG="FloatingWindowService";
    public static final String ACTION_FLOATING_WINDOW_SERVICE="com.handsomezhou.xdesktophelper.service.FLOATING_WINDOW_SERVICE";

    private int MAX_LONG_PRESS_TIME=350;// 长按/双击最长等待时间
    private int MAX_SINGLE_CLICK_TIME=50;// 单击最长等待时间
    private int MAX_MOVE_FOR_CLICK=50;// 最长改变距离,超过则算移动

    private WindowManager mWindowManager;
    private ImageView mFloatingIv;
    private WindowManager.LayoutParams mLayoutParams;
    private long mLastPressTime;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "onCreate");
        initFloatingWindow();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppInfoHelper.getInstance().startLoadAppInfo();
        LogUtil.i(TAG, "onStartCommand");


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy");
        freeFloatingWindow();
    }


    public static void startService(Context context){
        Intent intent=new Intent(context,FloatingWindowService.class);
        intent.setAction(FloatingWindowService.ACTION_FLOATING_WINDOW_SERVICE);
        context.startService(intent);


    }

    public static void stopService(Context context){
        Intent intent=new Intent(context,FloatingWindowService.class);
        context.stopService(intent);
    }
    
    private void initFloatingWindow(){
        initData();
        initView();
        initListener();

        return;
    }

    private void freeFloatingWindow(){
        if(null!=mWindowManager){
            if(null!=mFloatingIv){
                mWindowManager.removeView(mFloatingIv);
            }
        }

        return;
    }

    private void initData(){

        return;
    }

    private void initView(){
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        mFloatingIv = new ImageView(this);
        mFloatingIv.setImageResource(R.mipmap.floating_icon);

        mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mLayoutParams.x = SettingsHelper.getInstance().getFloatingWindowPosX();
        mLayoutParams.y = SettingsHelper.getInstance().getFloatingWindowPosY();

        mWindowManager.addView(mFloatingIv, mLayoutParams);
        return;
    }

    private void initListener(){
        mFloatingIv.setOnTouchListener(new View.OnTouchListener() {
            WindowManager.LayoutParams layoutParams = mLayoutParams;
            int initialX;
            int initialY;
            float initialTouchX;
            float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean touch=false;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        long pressTime = System.currentTimeMillis();

                        // If double click...
                        if (pressTime - mLastPressTime <= MAX_LONG_PRESS_TIME) {
                            //FloatingWindowService.this.stopSelf();
                            //mHasDoubleClicked = true;
                        } else {     // If not double click....
                            // mHasDoubleClicked = false;
                        }
                        mLastPressTime = pressTime;
                        initialX = layoutParams.x;
                        initialY = layoutParams.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(Math.abs(layoutParams.y-initialY)>MAX_MOVE_FOR_CLICK||Math.abs(layoutParams.x-initialX)>MAX_MOVE_FOR_CLICK){
                            //move
                            touch=true;

                            SettingsHelper.getInstance().setFloatingWindowPosX(layoutParams.x);
                            SettingsHelper.getInstance().setFloatingWindowPosY(layoutParams.y);
                        }else {

                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        layoutParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                        layoutParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(mFloatingIv, layoutParams);
                        break;

                    default:
                        break;
                }
                return touch;
            }
        });


        mFloatingIv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
               //ToastUtil.toastLengthshort(XDesktopHelperApplication.getContext(),TAG);
                ActivityUtil.launch(getApplicationContext(), MainActivity.class,Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            }
        });
        return;
    }


}
