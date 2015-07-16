package ronandoyle.ie.ufcrankings;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO Update this line
 *
 */
public class Seperator extends View {

    private Paint mPaint;
    private float mSize = 2f;

    public Seperator(Context context) {
        super(context);
        initPaint();
    }

    public Seperator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet,
                R.styleable.SeperatorView, 0, 0);

        if (typedArray != null) {
            try {
                mSize = typedArray.getDimension(R.styleable.SeperatorView_size, 2);
            } finally {
                typedArray.recycle();
            }
        }

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#aaaaaa"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mSize);
    }

    public float getSize() {
        return mSize;
    }

    public void setSize(float size) {
        this.mSize = size;
        invalidate();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Get the initial positions.
        int startX = getLeft();
        int stopX = getRight();
        int startY = 0;
        int stopY = 0;

        // Adjust for Padding
        startX += getPaddingLeft();
        startY += getPaddingBottom();
        stopX -= getPaddingRight();
        stopY -= getPaddingTop();

        canvas.drawLine(startX, startY, stopX, stopY, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, (int) getSize());
    }

}
