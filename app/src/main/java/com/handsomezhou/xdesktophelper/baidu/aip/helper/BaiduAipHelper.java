package com.handsomezhou.xdesktophelper.baidu.aip.helper;

import com.baidu.aip.nlp.AipNlp;
import com.handsomezhou.xdesktophelper.baidu.aip.constant.BaiduConstant;
import com.handsomezhou.xdesktophelper.baidu.aip.model.NlpLexer;
import com.handsomezhou.xdesktophelper.util.CommonUtil;
import com.handsomezhou.xdesktophelper.util.JsonUtil;

import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zhoujq on 2018/2/7.
 */

public class BaiduAipHelper {
    private static final String TAG = "BaiduAipHelper";
    private static BaiduAipHelper sInstance;
    private AipNlp mAipNlp;
    private OnAipNlp mOnAipNlp;

    public static BaiduAipHelper getInstance() {
        if (null == sInstance) {
            sInstance = new BaiduAipHelper();
        }

        return sInstance;
    }

    public interface OnAipNlp{
        void onAipNlpSuccess(String text,NlpLexer nlpLexer);
        void onAipNlpFailed(String text);
    }

    private BaiduAipHelper(){
        initBaiduAipHelper();
    }

    private void initBaiduAipHelper(){
        initAipNlp();
        return;
    }

    private void initAipNlp(){
        if(null==mAipNlp){
            // 初始化一个AipNlp
             mAipNlp = new AipNlp(BaiduConstant.APP_ID, BaiduConstant.API_KEY, BaiduConstant.SECRET_KEY);

            // 可选：设置网络连接参数
            mAipNlp.setConnectionTimeoutInMillis(2000);
            mAipNlp.setSocketTimeoutInMillis(60000);

            // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
            //mAipNlp.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
            //mAipNlp.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

            // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
            // 也可以直接通过jvm启动参数设置此环境变量
            System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        }
    }

    public AipNlp getAipNlp() {
        return mAipNlp;
    }

    public void setAipNlp(AipNlp aipNlp) {
        mAipNlp = aipNlp;
    }

    public OnAipNlp getOnAipNlp() {
        return mOnAipNlp;
    }

    public void setOnAipNlp(OnAipNlp onAipNlp) {
        mOnAipNlp = onAipNlp;
    }

    /**
     * 开始自然语言处理
     * @param text
     * @param onAipNlp
     */
    public void startNlp(final String text, OnAipNlp onAipNlp){
        do{
            setOnAipNlp(onAipNlp);

            if(CommonUtil.isEmpty(text)){
                if(null!=mOnAipNlp){
                    mOnAipNlp.onAipNlpFailed(text);
                }
                break;
            }



            if(null==mAipNlp){
                initAipNlp();
            }


            Observable.create(new Observable.OnSubscribe<JSONObject>() {
                @Override
                public void call(Subscriber<? super JSONObject> subscriber) {

                    JSONObject lexerJsonObject=mAipNlp.lexer(text,null);

                    subscriber.onNext(lexerJsonObject);
                    subscriber.onCompleted();

                }
            }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                    .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                    .subscribe(new Action1<JSONObject>() {
                        @Override
                        public void call(JSONObject jsonObject) {
                            //TaskMsgHelper.getInstance().removeProcessedMessage(msg);//必须调用
                            //ToastUtil.toastLengthLong(XDesktopHelperApplication.getContext(),jsonObject.toString());
                            if(null!=mOnAipNlp){
                                NlpLexer nlpLexer= JsonUtil.fromJson(jsonObject.toString(),NlpLexer.class);
                                mOnAipNlp.onAipNlpSuccess(text,nlpLexer);
                            }
                        }
                    });


        }while (false);

        return;
    }

}
