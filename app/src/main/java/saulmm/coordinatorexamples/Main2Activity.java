package saulmm.coordinatorexamples;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    //144,191,329,109
    ImageView imageView;
    private int START_X = 29;
    private int START_Y = 54;
    private int WIDTH_PX = 153;
    private int HEIGHT_PX = 51;
//LOG: h= 51 w= 153 x= 29 y= 54
 //   D/NEW  XY: X=87  Y=261  HIEGHT=153  WIDTh459
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        START_X= getIntent().getIntExtra("x",0);
        START_Y=getIntent().getIntExtra("y",0);
        HEIGHT_PX=getIntent().getIntExtra("h",0);
        WIDTH_PX=getIntent().getIntExtra("w",0);
       setXY();
       /* BitmapFactory.Options options=new BitmapFactory.Options();
        options.inDensity=480;*/
        Bitmap SOURCE_BITMAP = BitmapFactory.decodeResource(getResources(), R.drawable.actress2); // Get the source Bitmap using your favorite method :-)
        Bitmap newBitmap;
// Crop bitmap
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        Log.i("LOG", metrics.densityDpi + "  " + metrics.xdpi + "  " + metrics.ydpi);
        int kk = 0;
        newBitmap = Bitmap.createBitmap(SOURCE_BITMAP, START_X , (START_Y - kk), WIDTH_PX+START_X>SOURCE_BITMAP.getWidth()?SOURCE_BITMAP.getWidth()-START_X:WIDTH_PX ,  HEIGHT_PX+START_Y>SOURCE_BITMAP.getHeight()?SOURCE_BITMAP.getHeight()-START_Y:HEIGHT_PX, null, false);
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

        imageView.setImageBitmap(newBitmap);
    }

    public void setXY()
    {    DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();

        Log.d("OLD  XY","X="+START_X+"  Y="+START_Y+"  HIEGHT="+HEIGHT_PX+"  WIDTh"+WIDTH_PX);
        START_X = (int) (((float)metrics.densityDpi/160)*START_X);

        START_Y = (int) (((float)metrics.densityDpi/160)*START_Y);

        HEIGHT_PX = (int) (((float)metrics.densityDpi/160)*HEIGHT_PX);

        WIDTH_PX = (int) (((float)metrics.densityDpi/160)*WIDTH_PX);
        Log.d("NEW  XY","X="+START_X+"  Y="+START_Y+"  HIEGHT="+HEIGHT_PX+"  WIDTh"+WIDTH_PX);
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


}
