package saulmm.coordinatorexamples;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    private ImageView imageView;
    private RectF croppedRect = new RectF();
    private Bitmap SOURCE_BITMAP;
    private
    @DrawableRes
    int imageID = R.drawable.image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        this.croppedRect.set(CropImage.getCroppedRect(getIntent().getExtras()));
        //        setXY();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        SOURCE_BITMAP = BitmapFactory.decodeResource(getResources(), imageID, options); // Get the source Bitmap using your favorite method :-)
        Bitmap newBitmap;
        // Crop bitmap
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        Log.i("LOG", metrics.densityDpi + "  " + metrics.xdpi + "  " + metrics.ydpi);
        int kk = 0;

      /*  if (480 > metrics.densityDpi) {
            kk = (480 - metrics.densityDpi) / 3;

            if ((START_X + WIDTH_PX) <= SOURCE_BITMAP.getWidth()) {
                newBitmap = Bitmap.createBitmap(SOURCE_BITMAP, (START_X - kk)>0?(START_X - kk):0, (START_Y - kk), WIDTH_PX - kk, HEIGHT_PX - kk, null, false);
            } else {
                newBitmap = Bitmap.createBitmap(SOURCE_BITMAP,( START_X - kk), (START_Y - kk), SOURCE_BITMAP.getWidth() - START_X - kk, HEIGHT_PX - kk, null, false);
            }
        } else
        {
            kk = (metrics.densityDpi - 480) / 3;
            if ((START_X + WIDTH_PX) <= SOURCE_BITMAP.getWidth()) {
                newBitmap = Bitmap.createBitmap(SOURCE_BITMAP, (START_X + kk), (START_Y + kk), WIDTH_PX + kk, HEIGHT_PX + kk, null, false);
            } else {
                newBitmap = Bitmap.createBitmap(SOURCE_BITMAP, (START_X + kk), (START_Y + kk), SOURCE_BITMAP.getWidth() + START_X + kk, HEIGHT_PX + kk, null, false);
            }
        }*/

// Assign new bitmap to ImageView
        imageView = (ImageView) findViewById(R.id.image);

        udpateImage();

        findViewById(R.id.btn_cropImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.cropImage(Main2Activity.this, imageID, croppedRect);
            }
        });
    }

    public void setXY() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();

        Log.d("OLD  XY", croppedRect.toShortString());
        croppedRect.left = (int) (((float) metrics.densityDpi / 160) * croppedRect.left);

        croppedRect.top = (int) (((float) metrics.densityDpi / 160) * croppedRect.top);

        croppedRect.bottom = (int) (((float) metrics.densityDpi / 160) * croppedRect.bottom);

        croppedRect.right = (int) (((float) metrics.densityDpi / 160) * croppedRect.right);
        Log.d("NEW  XY", croppedRect.toShortString());
    }

    public static int convertPixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }


    public static int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }


    private void udpateImage() {
        Bitmap newBitmap = SOURCE_BITMAP;
        if (!this.croppedRect.isEmpty())
            newBitmap = Bitmap.createBitmap(SOURCE_BITMAP, (int) this.croppedRect.left, (int) this.croppedRect.top, (int) this.croppedRect.width(), (int) this.croppedRect.height(), null, false);

        imageView.setImageBitmap(newBitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        if (CropImage.isCropImageResult(requestCode)) {
            this.croppedRect.set(CropImage.getCroppedRect(data.getExtras()));
            udpateImage();
        }
    }
}
