package com.naver.android.helloyako.imagecrop.model;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

import com.naver.android.helloyako.imagecrop.util.BitmapLoadUtils;

import static android.R.attr.bitmap;

/**
 * Created by helloyako on 2016. 5. 10..
 */
public class CropInfo {
    private float scale;
    private float viewBitmapWidth;
    private float viewImageTop;
    private float viewImageLeft;
    private float cropTop;
    private float cropLeft;
    private float cropWidth;
    private float cropHeight;


    public CropInfo(float scale, float viewBitmapWidth, float viewImageTop, float viewImageLeft, float cropTop, float cropLeft, float cropWidth, float cropHeight) {
        this.scale = scale;
        this.viewBitmapWidth = viewBitmapWidth;
        this.viewImageTop = viewImageTop;
        this.viewImageLeft = viewImageLeft;
        this.cropTop = cropTop;
        this.cropLeft = cropLeft;
        this.cropWidth = cropWidth;
        this.cropHeight = cropHeight;
    }


    public Bitmap getCroppedImage(String path) {
        return getCroppedImage(path, 4000);
    }

    /**
     * @param reqSize for image sampling
     */
    public Bitmap getCroppedImage(String path, int reqSize) {
        Bitmap bitmap = BitmapLoadUtils.decode(path, reqSize, reqSize);
        return getCroppedImage(bitmap);
    }

    public Bitmap getCroppedImage(Bitmap bitmap) {
        float scale = this.scale * (viewBitmapWidth / (float) bitmap.getWidth());
        float x = Math.abs(viewImageLeft - cropLeft) / scale;
        float y = Math.abs(viewImageTop - cropTop) / scale;
        float actualCropWidth = cropWidth / scale;
        float actualCropHeight = cropHeight / scale;

        if (x < 0) {
            x = 0;
        }

        if (y < 0) {
            y = 0;
        }

        if (y + actualCropHeight > bitmap.getHeight()) {
            actualCropHeight = bitmap.getHeight() - y;
        }

        if (x + actualCropWidth > bitmap.getWidth()) {
            actualCropWidth = bitmap.getWidth() - x;
        }

        return Bitmap.createBitmap(bitmap, (int) x, (int) y, (int) actualCropWidth, (int) actualCropHeight);
    }


    public RectF getCroppedRect() {
        RectF croppedRect = new RectF();
        float left = Math.abs(viewImageLeft - cropLeft) / scale;
        float top = Math.abs(viewImageTop - cropTop) / scale;
        float right = Math.abs(left + cropWidth / scale);
        float bottom = Math.abs(top + cropHeight / scale);

        croppedRect.set(
                left,
                top,
                right,
                bottom
        );
        return croppedRect;
    }


}
