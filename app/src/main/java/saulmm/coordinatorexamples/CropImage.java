package saulmm.coordinatorexamples;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.naver.android.helloyako.imagecrop.view.ImageCropView;

public class

CropImage extends AppCompatActivity {

    ImageCropView imageView;
    Button done;
    int x,y,h,w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        done=(Button)findViewById(R.id.done);
        imageView=(ImageCropView)findViewById(R.id.zoomImageView) ;
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inScaled=false;
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.image,options);
        imageView.setAspectRatio(3, 1);

//          imageView.setScrollEnabled(true);
//         imageView.setScrollX(131);
//        imageView.setScrollY(180);


        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        Log.i("LOG",metrics.densityDpi+"");
       //imageView.
        imageView.setImageBitmap(image);

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
                float viewBitmpaWidth = imageView.getCropInfo().getViewBitmapWidth();
                float viewImageLeft = imageView.getCropInfo().getViewImageLeft();
                float viewImageTop = imageView.getCropInfo().getViewImageTop();
                float cropLeft = imageView.getCropInfo().getCropLeft();
                float cropTop = imageView.getCropInfo().getCropTop();
                float scale = imageView.getCropInfo().getScale();
                float cropWidth = imageView.getCropInfo().getCropWidth();
                float cropHeight = imageView.getCropInfo().getCropHeight();


                int scrollX = imageView.getScrollX();
                int scrollY = imageView.getScrollY();


                PointF pointF = imageView.getCenter();

                float centerX = pointF.x;
                float centerY = pointF.y;

                x = (int) (Math.abs(viewImageLeft - cropLeft) / scale);
                y = (int) (Math.abs(viewImageTop - cropTop) / scale);
                w = (int) (cropWidth / scale);
                h = (int) (cropHeight / scale);
                Intent intent = new Intent(CropImage.this, Main2Activity.class);
                intent.putExtra("x", x);
                intent.putExtra("y", y);
                intent.putExtra("h", h);
                intent.putExtra("w", w);
                Log.d("LOG", "h= " + h + " w= " + w + " x= " + x + " y= " + y);
                startActivity(intent);
            }
        });

    }



}

