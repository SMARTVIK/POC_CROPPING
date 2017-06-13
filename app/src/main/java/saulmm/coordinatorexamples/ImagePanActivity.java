package saulmm.coordinatorexamples;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class ImagePanActivity extends AppCompatActivity {

    TouchImageView image;
    SeekBar seek;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pan);
        done=(Button)findViewById(R.id.done);
        image=(TouchImageView)findViewById(R.id.image);
        seek=(SeekBar)findViewById(R.id.seek);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inScaled=false;
        options.inDensity=420;
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.actress2,options);
        image.setImageBitmap(bitmap);

        //image.setScrollPosition(0,127/(float)seek.getMax());
        image.postDelayed(new Runnable() {
            @Override
            public void run() {
                image.setCurrentZoom(1.7519896f);
                image.setScrollPosition(0.46671402f,0.29123208f);

            }
        },1000);
               // image.setZoom( 1.9247524f);
       // image.setDoubleTap(false);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              image.setScrollPosition(0,progress/(float)seek.getMax());
                Log.d("TAGS",image.getScrollPosition().toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              PointF pi=image.getScrollPosition();
                Log.d("TAGS","y="+pi.y+"  x=  "+pi.x+"   "+ image.getCurrentZoom());
// 0.6686075    0.5294296   1.9247524

            }
        });
    }
}
