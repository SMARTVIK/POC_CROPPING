package saulmm.coordinatorexamples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.naver.android.helloyako.imagecrop.view.ImageCropView;

public class CropImage extends AppCompatActivity {

    private static String EXTRA_CROPPED_RECT = "_extra_cropped_rect";
    private static String EXTRA_IMAGE_TO_CROP = "_extra_image_to_crop";
    private static int REQUEST_CODE_CROP_IMAGE = 123;

    public static boolean isCropImageResult(int requestCode) {
        return REQUEST_CODE_CROP_IMAGE == requestCode;
    }

    public static RectF getCroppedRect(Bundle data) {
        if (data == null || !data.containsKey(EXTRA_CROPPED_RECT) || !(data.getParcelable(EXTRA_CROPPED_RECT) instanceof RectF)) {
            //    private int START_X = 179;
//    private int START_Y = 178;
//    private int WIDTH_PX = 51;
//    private int HEIGHT_PX = 51;
//            Rect rect = new Rect();
            return new RectF();
        }

        return data.getParcelable(EXTRA_CROPPED_RECT);
    }


    public static void cropImage(Activity activity, @DrawableRes int bitmap, RectF rect) {
        Intent intent = new Intent(activity, CropImage.class);
        intent.putExtra(EXTRA_IMAGE_TO_CROP, bitmap);
        intent.putExtra(EXTRA_CROPPED_RECT, rect);
        activity.startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    public static void cropImage(Fragment fragment, @DrawableRes int bitmap, RectF rect) {
        Intent intent = new Intent(fragment.getContext(), CropImage.class);
        intent.putExtra(EXTRA_IMAGE_TO_CROP, bitmap);
        intent.putExtra(EXTRA_CROPPED_RECT, rect);
        //workaround for the issue regarding not deleivering results to Child Fragments
        if (fragment.getParentFragment() != null)
            fragment = fragment.getParentFragment();

        fragment.startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }


    private ImageCropView imageView;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        done = (Button) findViewById(R.id.done);
        imageView = (ImageCropView) findViewById(R.id.zoomImageView);

        imageView.setAspectRatio(3, 1);

//          imageView.setScrollEnabled(true);
//         imageView.setScrollX(131);
//        imageView.setScrollY(180);


        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        Log.i("LOG", metrics.densityDpi + "");
        //imageView.


        imageView.setImageBitmap(getBitmapFromIntent());


        imageView.setCropRect(getCroppedRectFromIntent());
/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.zoomTo(10.656026f, 720f, 1154f);
            }
        }, 5000);
*/

/*
        imageView.scale = scale; //11.12
        this.viewBitmapWidth = viewBitmapWidth; 256
        this.viewImageTop = viewImageTop;  -603
        this.viewImageLeft = viewImageLeft; -586
        this.cropTop = cropTop;  750
        this.cropLeft = cropLeft; 0.0
        this.cropWidth = cropWidth; 1080
        this.cropHeight = cropHeight; 360
*/

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                float viewBitmpaWidth = imageView.getCropInfo().getViewBitmapWidth();
//                float viewImageLeft = imageView.getCropInfo().getViewImageLeft();
//                float viewImageTop = imageView.getCropInfo().getViewImageTop();
//                float cropLeft = imageView.getCropInfo().getCropLeft();
//                float cropTop = imageView.getCropInfo().getCropTop();
//                float scale = imageView.getCropInfo().getScale();
//                float cropWidth = imageView.getCropInfo().getCropWidth();
//                float cropHeight = imageView.getCropInfo().getCropHeight();
//
//
////                int scrollX = imageView.getScrollX();
////                int scrollY = imageView.getScrollY();
////
//
//                PointF pointF = imageView.getCenter();
//
////                float centerX = pointF.x;
////                float centerY = pointF.y;
//
//
//                Intent intent = new Intent(CropImage.this, Main2Activity.class);
//
//                intent.putExtra("x", x);
//                intent.putExtra("y", y);
//                intent.putExtra("h", h);
//                intent.putExtra("w", w);
//                Log.d("LOG", "h= " + h + " w= " + w + " x= " + x + " y= " + y);


                Intent intent = new Intent(CropImage.this, Main2Activity.class);
                intent.putExtra(EXTRA_CROPPED_RECT, imageView.getCropInfo().getCroppedRect());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


    private Bitmap getBitmapFromIntent() {
        int intExtra = getIntent().getIntExtra(EXTRA_IMAGE_TO_CROP, R.drawable.image);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        return BitmapFactory.decodeResource(getResources(), intExtra, options);
    }


    private RectF getCroppedRectFromIntent() {
        return getIntent().getParcelableExtra(EXTRA_CROPPED_RECT);
    }


}

