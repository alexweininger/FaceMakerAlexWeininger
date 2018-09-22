package weininger.com.facemakeralexweininger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class Face extends SurfaceView {

    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;

    public Face(Context context) {
        super(context);
        setWillNotDraw(false);
        randomize();
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        randomize();
    }

    protected void randomize() {
        Random random = new Random();

        this.hairStyle = random.nextInt(3);

        this.skinColor = Color.argb(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        this.eyeColor = Color.argb(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        this.hairColor = Color.argb(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));




        Log.d("user", "face randomized");
        invalidate();
    }


    public void onDraw(Canvas canvas) {



        Log.d("user", "OnDraw");
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.eyeColor);
        canvas.drawCircle(100, 100, 100, paint);

        paint.setColor(this.skinColor);
        canvas.drawCircle(300, 100, 100, paint);

        paint.setColor(this.hairColor);
        canvas.drawCircle(500, 100, 100, paint);
    }

    protected void drawHairStyle(int hairStyle) {

    }

    protected void setHairStyle(String hairStyleString) {
        switch (hairStyleString) {
            case "Style 1":
                this.hairStyle = 1;

                break;
            case "Style 2":
                this.hairStyle = 2;
                break;
            case "Style 3":
                this.hairStyle = 3;
                break;
            default:
                this.hairStyle = 0;
                Log.d("user", "did not find hairStyleString in switch statement - Face.setHairStyle: STRING: " + hairStyleString);
        }
        Log.d("user", "style set to: " + this.hairStyle);
        invalidate();
    }

}
