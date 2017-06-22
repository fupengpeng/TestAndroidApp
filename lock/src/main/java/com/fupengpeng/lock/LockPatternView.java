package com.fupengpeng.lock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fupengpeng on 2017/6/16 0016.
 * 图案锁
 */

public class LockPatternView extends View {

    //矩阵，用于调整所要画的直线的长短
    public Matrix matrix = new Matrix();
    //选中点的数量
    public static final int POINT_SIZE = 5;
    //9个点
    private Point [] [] points = new Point[3][3];

    //画笔
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    //                     是否选择开始绘制    是否结束绘制   鼠标移动到已经选择过得点上面不再选择此点进行绘制，而是直接绘制直线
    private boolean isInit , isSelect       , isFinish    ,  movingNoPoint;
    //屏幕宽高,图片资源的半径，鼠标移动的x和y值
    private float width, height,bitmapR,movingX,movingY;

    //九宫格偏移量
    float offsetsX,offsetsY;

    private Bitmap pointNormal, pointPressed, pointError,lineNormal,linePressed;


    //按下的点集合
    private List<Point> pointList = new ArrayList<Point>();

    public LockPatternView(Context context) {
        super(context);
    }

    public LockPatternView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LockPatternView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (!isInit){
            initPoints();
        }
        //绘制点
        points2Canvas(canvas);

        //绘制线
        if (pointList.size() > 0){
            //绘制九宫格里面的点的连线
            Point a = pointList.get(0);
            for (int i = 0; i < pointList.size(); i++) {
                Point b = pointList.get(i);
                line2Canvas(canvas,a,b);
                a = b;
            }
            //绘制鼠标坐标点的连线
            if (movingNoPoint){
                line2Canvas(canvas,a,new Point(movingX,movingY));
            }
        }
    }

    /**
     * 将点绘制到画布上
     * @param canvas 画布
     */
    private void points2Canvas(Canvas canvas) {

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Point point = points[i][j];
                if (point.state == Point.STATE_PRESSED){
                    canvas.drawBitmap(pointPressed,point.x - bitmapR,point.y - bitmapR,paint);
                }else if (point.state == Point.STATE_ERROR){
                    canvas.drawBitmap(pointError,point.x - bitmapR,point.y - bitmapR,paint);
                }else {
                    canvas.drawBitmap(pointNormal,point.x - bitmapR,point.y - bitmapR,paint);
                }
            }
        }

    }

    /**
     * 绘制连接点的直线
     * @param canvas    画布
     * @param a         第一个点
     * @param b         第二个点
     */
    private void line2Canvas(Canvas canvas,Point a, Point b){

        //线的长度
        float lineLength = (float) Point.distance(a,b);
        //线的角度
        float degrees = getDegrees(a,b);
        canvas.rotate(degrees,a.x,a.y);
        if (a.state == Point.STATE_PRESSED){

            matrix.setScale(lineLength / linePressed.getWidth(),1);
            matrix.postTranslate(a.x - linePressed.getWidth() / 2,a.y - linePressed.getHeight() / 2);
            canvas.drawBitmap(linePressed,matrix,paint);
        }else {
            matrix.setScale(lineLength / lineNormal.getWidth(),1);
            matrix.postTranslate(a.x - lineNormal.getWidth() / 2,a.y - lineNormal.getHeight() / 2);
            canvas.drawBitmap(lineNormal,matrix,paint);
        }
        canvas.rotate( -degrees,a.x,a.y);
    }

    /**
     * 初始化点
     */
    private void initPoints() {
        //1.获取布局高宽
        width = getWidth();
        height = getHeight();

        //2.横竖屏判断来决定九宫格偏移量
        if (width > height){
            //横屏
            //九宫格偏移距离，把屏幕分为三个部分，九宫格和左右空白，
            //偏移距离就是屏幕宽度减去九宫格宽度（九宫格高度），除以2，就是偏移距离
            offsetsX = (width - height)/2;
            width = height;
        }else {
            //竖屏
            offsetsY = (height - width)/2;
            height = width;
        }

        //3.图片资源
        pointNormal =  BitmapFactory.decodeResource(getResources(),R.drawable.oval_normal);
        pointPressed = BitmapFactory.decodeResource(getResources(),R.drawable.oval_pressed);
        pointError = BitmapFactory.decodeResource(getResources(),R.drawable.oval_error);
        lineNormal = BitmapFactory.decodeResource(getResources(),R.drawable.line_pressed);
        linePressed = BitmapFactory.decodeResource(getResources(),R.drawable.line_error);



        //九宫格点的偏移量
        points[0][0] = new Point(offsetsX + width / 4 ,offsetsY + width / 4);
        points[0][1] = new Point(offsetsX + width / 2,offsetsY + width / 4);
        points[0][2] = new Point(offsetsX + width - width / 4,offsetsY + width / 4);

        points[1][0] = new Point(offsetsX + width / 4,offsetsY + width / 2);
        points[1][1] = new Point(offsetsX + width / 2,offsetsY + width / 2);
        points[1][2] = new Point(offsetsX + width - width / 4,offsetsY + width / 2);

        points[2][0] = new Point(offsetsX + width / 4,offsetsY + width - width / 4);
        points[2][1] = new Point(offsetsX + width / 2,offsetsY + width - width / 4);
        points[2][2] = new Point(offsetsX + width - width / 4,offsetsY + width - width / 4);


        //5.图片资源的半径
        bitmapR = pointNormal.getHeight() / 2 ;

        //6.初始化完成
        isInit = true;


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        movingX = event.getX();
        movingY = event.getY();
        movingNoPoint = false;
        Point point = null;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                resetPoint();//清除掉之前绘制的点
                //按下后，检查鼠标的x和y值是否和九宫格坐标相近，
                // 如果相近的话就让其开始绘制
                point = checkSelectPoint();
                if (point != null){
                    isSelect = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:

                if (isSelect){
                    point = checkSelectPoint();
                    if (point == null){
                        movingNoPoint = true;

                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isFinish = true;
                isSelect = false;
                break;
        }
        //选中重复检查，选中九宫格中的一个点后就不能在选中
        if (!isFinish &&isSelect && point != null){
            //交叉点
            if (crossPoint(point)){
                movingNoPoint = true;

            //新点，可连接，添加到连接到点的集合
            }else {
                point.state = Point.STATE_PRESSED;
                pointList.add(point);
            }
        }
        //绘制结束
        if (isFinish){
            //绘制不成立
            if (pointList.size() == 1){
                resetPoint();

            //绘制错误
            }else if (pointList.size() < POINT_SIZE && pointList.size() > 2){
                errorPoint();
            }
        }
        //刷新View，即重新绘制
        postInvalidate();
        return true;
    }

    /**
     * 交叉点
     * @param point 点
     * @return      是否交叉
     */
    private boolean crossPoint(Point point){
        if (pointList.contains(point)){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 绘制成功
     */
    public void resetPoint(){
        pointList.clear();//清除集合里的点
    }

    /**
     * 绘制错误
     */
    public void errorPoint(){
        for (Point point: pointList) {
            point.state = Point.STATE_ERROR;
        }
    }

    /**
     * 判断鼠标的坐标是否和九宫格坐标相近，相近的话就选中
     */
    private Point checkSelectPoint(){

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Point point = points[i][j];
                if (Point.with(point.x,point.y,bitmapR,movingX,movingY)){
                    return point;
                }
            }
        }

        return null;
    }
    /**
     * 获取角度
     * @param a    第一个点
     * @param b    第二个点
     * @return     角度
     */
    public float getDegrees(Point a,Point b){
        float ax = a.x;
        float ay = a.y;
        float bx = b.x;
        float by = b.y;
        float degrees = 0;
        if (bx == ax){                //y轴相等，90度或者270度
            if (by == ay){            //在y轴的下边，90度
                degrees = 90;
            }else if (by < ay){       //在y轴的上边，270度
                degrees = 270;
            }
        }else if (by == ay){          //y轴相等，0度或者180度
            if (bx > ax){             //在y轴的下边，90度
                degrees = 0;
            }else if (bx < ax){       //在y轴的上边，270度
                degrees = 180;
            }
        }

        return degrees;
    }


    /**
     * 自定义点
     */
    public static class Point{
        //正常
        public static int STATE_NORMAL = 0;
        //选中
        public static int STATE_PRESSED = 0;
        //错误
        public static int STATE_ERROR = 0;
        public float x,y;
        public int index = 0,state = 0;

        public Point(){}

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 两点之间的距离
         * @param a 点a
         * @param b 点b
         * @return  距离
         */
        public static double distance(Point a,Point b){
            //x轴差的平方加上y轴差的平方，对和开方

            return Math.sqrt(Math.abs(a.x - b.x) * Math.abs(a.x - b.x) +
                    Math.abs(a.y - b.y) * Math.abs(a.y - b.y));
        }

        /**
         * 是否重合
         * @param pointX    参考点的x
         * @param pointY    参考点的y
         * @param r         图片的半径
         * @param movingX   移动点的x
         * @param movingY   移动点的y
         * @return          是否重合
         */
        public static boolean with(float pointX, float pointY,
                                   float r ,
                                   float movingX , float movingY){
            //开方

            return Math.sqrt(
                    (pointX - movingX) * (pointX - movingX) +
                            (pointY - movingY) * (pointY - movingY)) < r ;
        }

    }


}
