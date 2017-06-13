package saulmm.coordinatorexamples;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class StartActivity extends AppCompatActivity {
    private ScrollView container;
    private int currentX;
    private int currentY;
    Button done;
    ImageView ImageView01;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        ImageView01=(ImageView)findViewById(R.id.ImageView01) ;
        done=(Button)findViewById(R.id.done);
        container = (ScrollView) findViewById(R.id.Container);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = DisplayMetrics.DENSITY_LOW;
        options.inTargetDensity = getResources().getDisplayMetrics().densityDpi;
        options.inScaled = true;
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.actress2,options);
        ImageView01.setImageBitmap(bitmap);
        container.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
               View v= container.getChildAt(0);
                Log.d("POints","X="+container.getScrollX()+"  Y= "+container.getScrollY());
            }
        });
        container.post(new Runnable() {
            @Override
            public void run() {
                container.scrollTo(0, 121);

            }
        });
       done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("X and Y",currentX+ "   "+currentY  );
            }
        });
    }


}