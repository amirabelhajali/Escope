package escope.esprit.escope.Drawer;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by wala on 27/05/2018.
 */

public class OnSwipeTouchListener implements View.OnTouchListener {
    ListView list;
    private GestureDetector gestureDetector;
    private Context context;

    public OnSwipeTouchListener(Context ctx, ListView list) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
        context = ctx;
        this.list = list;
    }

    public OnSwipeTouchListener() {
        super();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void onSwipeRight(int pos) {
     System.out.println("right");
    }

    public void onSwipeLeft(int pos) {
        System.out.println("left");

        //Do what you want after swiping right to left
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        private int getPostion(MotionEvent e1) {
            return list.pointToPosition((int) e1.getX(), (int) e1.getY());
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY)
                    && Math.abs(distanceX) > SWIPE_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight(getPostion(e1));
                else
                    onSwipeLeft(getPostion(e1));
                return true;
            }
            return false;
        }

    }
}
