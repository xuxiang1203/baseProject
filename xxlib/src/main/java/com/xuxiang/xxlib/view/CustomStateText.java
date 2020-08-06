package com.xuxiang.xxlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;

import com.xuxiang.xxlib.R;

/**
 * @describe 自定义背景状态的Button
 * 08-06: 新增:hintColor和 hintText
 * TODO:StateListDrawable 不够完善,各种view的状态还有bug---渐变未完善。需要重构
 *
 */
public class CustomStateText extends AppCompatTextView {
    private Context mContext;
    private boolean isSelector;
    private static int INT_DEFAULT = -9999999;
    private int defaultColor = Color.WHITE;//默认颜色
    private int defaultStrokeWidth = -1;//默认描边宽度
    private int shapeType;
    private int solidColor;//填充颜色
    private int strokeColor;//描边颜色
    private int solidPressedColor;//按下填充颜色
    private int solidSelectedColor;//被选中的填充颜色
    private int strokeSelectedColor;//被选中的描边颜色
    private int selectedTextColor;//被选中的文字颜色
    private int strokePressedColor;//按下描边颜色
    private float cornersRadius; //圆角
    private int strokeWidth; //描边宽度
    private String hintText;
    private int hintColor;//提示字体颜色
    //不能能点击的填充颜色
    private int notEnabledSolidColor;
    //普通按钮，只关心按下状态
    private final static int PRESSED = android.R.attr.state_pressed;
    private final static int WINDOW_FOCUSED = android.R.attr.state_window_focused;
    //是否获取焦点，一般用于输入框
    private final static int FOCUSED = android.R.attr.state_focused;
    //是否选中，例如单选框，复选框
    private final static int SELECTED = android.R.attr.state_selected;
    //是否可点击
    private final static int enabled = android.R.attr.state_enabled;

    private GradientDrawable gradientDrawable;
    private StateListDrawable stateListDrawable;
    private int startColor,endColor;
    private boolean isGradualChange;
    public CustomStateText(Context context) {
        this(context, null);
    }

    public CustomStateText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomStateText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
        setGravity(Gravity.CENTER);

        ViewCompat.setBackground(this, isSelector ? getSelector() : getDrawable());
    }

    /**
     * 解析配置
     *
     * @param attrs
     */
    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CustomStateText);
        isGradualChange = typedArray.getBoolean(R.styleable.CustomStateText_isGradualChange, false);
        startColor = typedArray.getColor(R.styleable.CustomStateText_startColor, defaultColor);
        endColor = typedArray.getColor(R.styleable.CustomStateText_endColor, defaultColor);
        isSelector = typedArray.getBoolean(R.styleable.CustomStateText_isSelector, false);
        solidColor = typedArray.getColor(R.styleable.CustomStateText_solidColor, defaultColor);
        strokeColor = typedArray.getColor(R.styleable.CustomStateText_strokeColor, INT_DEFAULT);
        solidPressedColor = typedArray.getColor(R.styleable.CustomStateText_solidPressedColor, INT_DEFAULT);
        notEnabledSolidColor = typedArray.getColor(R.styleable.CustomStateText_notEnabledSolidColor, INT_DEFAULT);
        strokePressedColor = typedArray.getColor(R.styleable.CustomStateText_strokePressedColor, INT_DEFAULT);
        cornersRadius = typedArray.getDimension(R.styleable.CustomStateText_cornersRadius, INT_DEFAULT);
        solidSelectedColor = typedArray.getColor(R.styleable.CustomStateText_solidSelectedColor, INT_DEFAULT);
        selectedTextColor = typedArray.getColor(R.styleable.CustomStateText_selectedTextColor, INT_DEFAULT);
        strokeSelectedColor = typedArray.getColor(R.styleable.CustomStateText_strokeSelectedColor, INT_DEFAULT);
        strokeWidth = (int) typedArray.getDimension(R.styleable.CustomStateText_strokeWidth, INT_DEFAULT);
        shapeType = typedArray.getInt(R.styleable.CustomStateText_shape_type, INT_DEFAULT);
        hintColor =  typedArray.getColor(R.styleable.CustomStateText_hintTextColor, defaultColor);
        hintText = typedArray.getString(R.styleable.CustomStateText_hintText);

        typedArray.recycle();
    }


    public StateListDrawable getSelector() {
          stateListDrawable = new StateListDrawable();
        //按下的颜色
        if (solidPressedColor != INT_DEFAULT || strokePressedColor != INT_DEFAULT){
            stateListDrawable.addState(new int[]{PRESSED}, getStateDrawable(solidPressedColor,strokePressedColor));
        }
        //有不可点击颜色
        if (notEnabledSolidColor != INT_DEFAULT){
            stateListDrawable.addState(new int[] {-enabled},  getStateDrawable(notEnabledSolidColor,INT_DEFAULT));
        }
        if (strokeSelectedColor  != INT_DEFAULT){
            stateListDrawable.addState(new int[]{SELECTED}, getStateDrawable(solidPressedColor,strokeSelectedColor));

        }
        //正常状态的颜色
        stateListDrawable.addState(new int[] {},  getDrawable());

        return stateListDrawable;
    }

    private Drawable getStateDrawable(int solidSelectedColor, int strokeSelectedColor) {

        GradientDrawable gradientDrawable = new GradientDrawable();
        //设置描边
        if (strokeWidth != defaultStrokeWidth && strokeSelectedColor != INT_DEFAULT) {
            gradientDrawable.setStroke(strokeWidth, strokeSelectedColor);
        }


        //设置填充
        if (solidSelectedColor != INT_DEFAULT) {
            gradientDrawable.setColor(solidSelectedColor);
        }

        if (cornersRadius != INT_DEFAULT) {
            gradientDrawable.setCornerRadius(cornersRadius);
        }
        if (shapeType != INT_DEFAULT){
            gradientDrawable.setShape(shapeType);
        }
        return gradientDrawable;
    }

    /**
     * 默认状态下的Drawable
     * @return
     */
    public GradientDrawable getDrawable() {
        gradientDrawable = new GradientDrawable();
        if (strokeWidth != defaultStrokeWidth && strokeColor != INT_DEFAULT) {
            gradientDrawable.setStroke(strokeWidth, strokeColor);
        }
        if (cornersRadius != INT_DEFAULT) {
            gradientDrawable.setCornerRadius(cornersRadius);
        }
        if (shapeType != INT_DEFAULT){
            gradientDrawable.setShape(shapeType);
        }
        if (isGradualChange){
            gradientDrawable.setColors(new int[]{startColor,endColor});
            gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        }else {
            gradientDrawable.setColor(solidColor);

        }
        return gradientDrawable;
    }

    public void setSolidColor(int solidColor) {
        this.solidColor = solidColor;
        ViewCompat.setBackground(this, isSelector ? getSelector() : getDrawable());
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        ViewCompat.setBackground(this, isSelector ? getSelector() : getDrawable());

    }

    public void setStrokeWith(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        ViewCompat.setBackground(this, isSelector ? getSelector() : getDrawable());
    }
}
