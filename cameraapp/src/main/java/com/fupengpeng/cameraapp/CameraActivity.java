package com.fupengpeng.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {



    public static final int REQ_1 = 1;
    public static final int REQ_2 = 2;
    private static final String TAG = "CameraActivity";
    private ImageView mImageView;
    private String mFilePath;
    private FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mImageView = (ImageView) findViewById(R.id.image);
        //获取sd卡路径
        // TODO: 2017/5/23   判断sd卡是否挂载
        mFilePath = Environment.getExternalStorageDirectory().getPath();
        //将拍照的照片保存名称设置下方名称
        mFilePath = mFilePath + "/" + "temp.png";

    }
    public void startCamera1(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQ_1);

    }
    public void startCamera2(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //将一个file路径指定进来
        Uri photoUri = Uri.fromFile(new File(mFilePath));
        //对系统拍照存储路径更改为我们设定的存储路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
        Log.e(TAG, "startCamera2: "+"点击跳转至相机拍照，并保存图片" );
        startActivityForResult(intent,REQ_2);

    }

    /**
     * 调用自定义相机
     * @param view
     */
    public void customCamera(View view){
        startActivity(new Intent(this,CustomCamera.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == REQ_1){
                //返回饱含整个图片的二进制流
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                mImageView.setImageBitmap(bitmap);
            }else if (requestCode == REQ_2){
                //到指定的路径下面去，获取图片，并设置
                try {
                    Log.e(TAG, "onActivityResult: "+"获取到拍照文件" );
                    fis = new FileInputStream(mFilePath);
                    Log.e(TAG, "onActivityResult: "+"将流解析成bitmap" );
                    //将流解析成Bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    Log.e(TAG, "onActivityResult: "+"设置图片" );
                    mImageView.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
