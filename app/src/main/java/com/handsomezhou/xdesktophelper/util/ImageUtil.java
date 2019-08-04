package com.handsomezhou.xdesktophelper.util;

import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by handsomezhou on 2019/8/4.
 */

public class ImageUtil {

    /**
     *
     * @param imageView
     * @param imageName
     * @return
     */
    public static boolean saveImageToGallery(ImageView imageView, String imageName) {
        boolean saveSuccess=false;
        File root = Environment.getExternalStorageDirectory();
        String DCIM_CAMERA_PATH=root.getAbsolutePath() +"/DCIM/Camera/";
        String IMAGE_SUFFIX_JPG=".jpg";
        imageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = imageView.getDrawingCache();
        if(CommonUtil.isEmpty(imageName)){
            imageName=TimeUtil.getLogTime();
        }
        File cachePath = new File(DCIM_CAMERA_PATH +imageName+ IMAGE_SUFFIX_JPG);
        try
        {
            cachePath.createNewFile();
            FileOutputStream ostream = new FileOutputStream(cachePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.close();

            saveSuccess=true;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return saveSuccess;
    }
}
