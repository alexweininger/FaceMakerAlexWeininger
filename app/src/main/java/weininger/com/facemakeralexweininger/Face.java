package weininger.com.facemakeralexweininger;

import android.content.Context;
import android.util.AttributeSet;
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
}
