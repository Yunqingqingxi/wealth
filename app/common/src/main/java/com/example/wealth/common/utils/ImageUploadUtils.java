package com.example.wealth.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUploadUtils {
    private static final String TAG = "ImageUploadUtils";
    private static final int MAX_IMAGE_SIZE = 1024 * 1024; // 1MB
    
    /**
     * 压缩并准备图片用于上传
     * @param context 上下文
     * @param imageUri 图片URI
     * @return 压缩后的图片文件
     */
    public static File prepareImageForUpload(Context context, Uri imageUri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
            Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
            
            // 压缩图片
            Bitmap compressedBitmap = compressImage(originalBitmap);
            
            // 保存到临时文件
            File tempFile = new File(context.getCacheDir(), "temp_" + System.currentTimeMillis() + ".jpg");
            FileOutputStream fos = new FileOutputStream(tempFile);
            compressedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.close();
            
            return tempFile;
        } catch (IOException e) {
            Log.e(TAG, "Error preparing image: " + e.getMessage());
            return null;
        }
    }
    
    private static Bitmap compressImage(Bitmap image) {
        if (image == null) return null;
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        if (width <= 0 || height <= 0) return image;
        
        // 如果图片太大，按比例缩小
        float ratio = Math.min(1024f / width, 1024f / height);
        if (ratio < 1) {
            width = Math.round(width * ratio);
            height = Math.round(height * ratio);
            return Bitmap.createScaledBitmap(image, width, height, true);
        }
        
        return image;
    }
} 