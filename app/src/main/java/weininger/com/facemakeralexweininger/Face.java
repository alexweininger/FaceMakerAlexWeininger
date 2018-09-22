package weininger.com.facemakeralexweininger;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

public class Face extends SurfaceView {

    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;

    public Face(Context context) {
        super(context);
        randomize();
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        randomize();
    }

    protected void randomize() {

    }

    protected void OnDraw() {

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
    }

}
