package com.handsomezhou.xdesktophelper.util;

import android.content.Context;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.constant.Constant;
import com.iflytek.cloud.ErrorCode;

/**
 * Created by handsomezhou on 2016/10/20.
 * *Refrence: http://www.xfyun.cn/index.php/default/doccenter/doccenterInner?itemTitle=ZmFx&anchor=Y29udGl0bGU2Ng==
 *  错误码列表
 */

public class XfyunErrorCodeUtil {

    public static String getXfyunErrorCodeDescription(Context context,int xfyunErrorCode){
        String xfyunErrorCodeDescription= Constant.NULL_STRING;
        do{
            if(null==context||xfyunErrorCode<0){
                break;
            }
            xfyunErrorCodeDescription=context.getString(XfyunErrorCodeUtil.getXfyunErrorCodeResId(context,xfyunErrorCode));
        }while (false);

        return  xfyunErrorCodeDescription;
    }

    public static int  getXfyunErrorCodeResId(Context context,int xfyunErrorCode){
        int xfyunErrorCodeResId=-1;
        do {
            if(null==context){
                break;
            }
            switch (xfyunErrorCode){
                case ErrorCode.ERROR_NO_NETWORK:
                    xfyunErrorCodeResId=R.string.error_no_network;
                    break;
                case ErrorCode.ERROR_NETWORK_TIMEOUT:
                    xfyunErrorCodeResId=R.string.error_network_timeout;
                    break;
                case ErrorCode.ERROR_NET_EXCEPTION:
                    xfyunErrorCodeResId=R.string.error_net_expection;
                    break;
                case ErrorCode.ERROR_INVALID_RESULT:
                    xfyunErrorCodeResId=R.string.error_invalid_result;
                    break;
                case ErrorCode.ERROR_NO_MATCH:
                    xfyunErrorCodeResId=R.string.error_no_match;
                    break;
                case ErrorCode.ERROR_AUDIO_RECORD:
                    xfyunErrorCodeResId=R.string.error_audio_record;
                    break;
                case ErrorCode.ERROR_NO_SPEECH:
                    xfyunErrorCodeResId=R.string.error_no_sppech;
                    break;
                case ErrorCode.ERROR_SPEECH_TIMEOUT:
                    xfyunErrorCodeResId=R.string.error_speech_timeout;
                    break;
                case ErrorCode.ERROR_EMPTY_UTTERANCE:
                    xfyunErrorCodeResId=R.string.error_empty_utterance;
                    break;
                case ErrorCode.ERROR_FILE_ACCESS:
                    xfyunErrorCodeResId=R.string.error_file_access;
                    break;
                case ErrorCode.ERROR_PLAY_MEDIA:
                    xfyunErrorCodeResId=R.string.error_play_media;
                    break;
                case ErrorCode.ERROR_INVALID_PARAM:
                    xfyunErrorCodeResId=R.string.error_invalid_param;
                    break;
                case ErrorCode.ERROR_TEXT_OVERFLOW:
                    xfyunErrorCodeResId=R.string.error_text_overflow;
                    break;
                case ErrorCode.ERROR_INVALID_DATA:
                    xfyunErrorCodeResId=R.string.error_invalid_data;
                    break;
                case ErrorCode.ERROR_LOGIN:
                    xfyunErrorCodeResId=R.string.error_login;
                    break;
                case ErrorCode.ERROR_PERMISSION_DENIED:
                    xfyunErrorCodeResId=R.string.error_permission_denied;
                    break;
                case ErrorCode.ERROR_INTERRUPT:
                    xfyunErrorCodeResId=R.string.error_interrupt;
                    break;
                case ErrorCode.ERROR_VERSION_LOWER:
                    xfyunErrorCodeResId=R.string.error_version_lower;
                    break;
        /*        case ErrorCode.ERROR_INVALID_ENCODING:
                    xfyunErrorCodeResId=R.string.error_invalid_encoding;
                    break;
                case ErrorCode.ERROR_MEMORY_WRANING:
                    xfyunErrorCodeResId=R.string.error_memory_wraning;
                    break;*/
                case ErrorCode.ERROR_UNKNOWN:
                    xfyunErrorCodeResId=R.string.error_unknown;
                    break;
                case ErrorCode.ERROR_COMPONENT_NOT_INSTALLED:
                    xfyunErrorCodeResId=R.string.error_component_not_installed;
                    break;
                case ErrorCode.ERROR_ENGINE_NOT_SUPPORTED:
                    xfyunErrorCodeResId=R.string.error_engine_not_supported;
                    break;
                case ErrorCode.ERROR_ENGINE_INIT_FAIL:
                    xfyunErrorCodeResId=R.string.error_engine_init_fail;
                    break;
                case ErrorCode.ERROR_ENGINE_CALL_FAIL:
                    xfyunErrorCodeResId=R.string.error_engine_call_fail;
                    break;
                case ErrorCode.ERROR_ENGINE_BUSY:
                    xfyunErrorCodeResId=R.string.error_engine_busy;
                    break;
                case ErrorCode.ERROR_LOCAL_NO_INIT:
                    xfyunErrorCodeResId=R.string.error_local_no_init;
                    break;
                case ErrorCode.ERROR_LOCAL_RESOURCE:
                    xfyunErrorCodeResId=R.string.error_local_resource;
                    break;
                case ErrorCode.ERROR_LOCAL_ENGINE:
                    xfyunErrorCodeResId=R.string.error_local_engine;
                    break;
                case ErrorCode.ERROR_IVW_INTERRUPT:
                    xfyunErrorCodeResId=R.string.error_ivw_interrupt;
                    break;

                default:
                    xfyunErrorCodeResId=R.string.error_unknown;
                    break;
            }
        }while (false);
        return xfyunErrorCodeResId;
    }
}
