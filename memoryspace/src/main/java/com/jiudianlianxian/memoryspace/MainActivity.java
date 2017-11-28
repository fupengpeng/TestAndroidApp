package com.jiudianlianxian.memoryspace;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jiudianlianxian.utils.MemoryManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @Title: MainActivity
 * @Description: 描述
 * @Company: 山东九点连线信息技术有限公司
 * @ProjectName: com.jiudianlianxian.memoryspace
 * @author fupengpeng
 * @date 2017/11/28 0028 8:48
 * ---27
 * 1--MainActivity
 * 2--
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "--MainActivity--";

    @BindView(R.id.btn_activity_main_internal_storage)
    Button btnActivityMainInternalStorage;
    @BindView(R.id.tx_activity_main_internal_storage)
    TextView txActivityMainInternalStorage;
    @BindView(R.id.btn_activity_main_sd_card)
    Button btnActivityMainSdCard;
    @BindView(R.id.tx_activity_main_sd_card)
    TextView txActivityMainSdCard;

    MemoryManager mm = new MemoryManager();
    @BindView(R.id.btn_activity_main_operation)
    Button btnActivityMainOperation;
    @BindView(R.id.tx_activity_main_operation)
    TextView txActivityMainOperation;
    @BindView(R.id.btn_activity_main_usable_operation)
    Button btnActivityMainUsableOperation;
    @BindView(R.id.tx_activity_main_usable_operation)
    TextView txActivityMainUsableOperation;
    @BindView(R.id.btn_activity_main_equipment_information)
    Button btnActivityMainEquipmentInformation;
    @BindView(R.id.tx_activity_main_equipment_information)
    TextView txActivityMainEquipmentInformation;
    @BindView(R.id.btn_activity_main_battery)
    Button btnActivityMainBattery;
    private Intent intent;

//    private static final String TAG = "FileScan";
    Map<String, String> fileList = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if ( !verifyStoragePermissions(this)){
            Log.e(TAG, "deleteFolderFile:------------------------------------- " );
        }


        String serviceString = Context.LOCATION_SERVICE;// 获取的是位置服务
        LocationManager locationManager = (LocationManager) getSystemService(serviceString);// 调用getSystemService()方法来获取LocationManager对象

    }


    @OnClick({R.id.btn_activity_main_internal_storage,
            R.id.btn_activity_main_sd_card,
            R.id.btn_activity_main_operation,
            R.id.btn_activity_main_usable_operation,
            R.id.btn_activity_main_equipment_information,
            R.id.btn_activity_main_battery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_main_internal_storage:
                Long selfLong = MemoryManager.getSelfMemory(true);
                Log.e(TAG, "onViewClicked: "+"内存大小 == " + MemoryManager.getSize(selfLong) );

                if ( !verifyStoragePermissions(this)){
                    Log.e(TAG, "deleteFolderFile:------------------------------------- " );
                }
                getDataDirectory();


                txActivityMainInternalStorage.setText("内存大小 == " + MemoryManager.getSize(selfLong));
                break;
            case R.id.btn_activity_main_sd_card:
                Long sdLong = MemoryManager.getSelfSDCardsMemory(true);
                Log.e(TAG, "onViewClicked: " + "sd卡大小 == " + MemoryManager.getSize(sdLong) );

                if ( !verifyStoragePermissions(this)){
                    Log.e(TAG, "deleteFolderFile:------------------------------------- " );
                }
                getExternalStorage();


                txActivityMainSdCard.setText("sd卡大小 == " + MemoryManager.getSize(sdLong));
                break;
            case R.id.btn_activity_main_operation:
                String operationLong = MemoryManager.getOperationMemory(this);
                Log.e(TAG, "onViewClicked: operationLong== " + operationLong.toString());
                txActivityMainOperation.setText("总运行内存大小 == " + operationLong);
                break;
            case R.id.btn_activity_main_usable_operation:
                String usableOperationLong = MemoryManager.getAvailMemory(this);
                Log.e(TAG, "onViewClicked: operationLong== " + usableOperationLong.toString());
                txActivityMainUsableOperation.setText("可用运行内存大小 == " + usableOperationLong);
                break;
            case R.id.btn_activity_main_equipment_information:
                String deviceInfo = MemoryManager.getDeviceInfo();
                Log.e(TAG, "onViewClicked: operationLong== " + deviceInfo.toString());
                txActivityMainEquipmentInformation.setText("设备信息 == " + deviceInfo);
                break;
            case R.id.btn_activity_main_battery:
                intent = new Intent(this,BatteryActivity.class);
                startActivity(intent);
                Log.e(TAG, "跳转至BatteryActivity");

                break;
        }
    }

    //获取sd卡文件
    private void getExternalStorage() {
        // Environment.getExternalStorageState();//注意：需要判断内置sd卡的一个状态
        // 只有在挂载状态才可以使用
        // 判断是否为挂载（可使用）状态
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.e(TAG, "onViewClicked: " + "sd卡未挂载");
        }else{
            File sdFile = Environment.getExternalStorageDirectory();
            fileList = getMusicListOnSys(sdFile);

            for (String key : fileList.keySet()) {
                Log.e(TAG, "onViewClicked: sdkey= "+ key + " and sdvalue= " + fileList.get(key) );
                //  sd  /storage/emulated/0/Android/data/com.duowan.kiwi/cache/uil-images/-1229897956.0
                //      /storage/emulated/0/HWThemes/.cache/diycache/-1828756114757984785
            }
        }
    }

    // 获取自身内存文件
    private void getDataDirectory() {
        // 拿到内置存储的自身内存文件
        File file = Environment.getDataDirectory();
        fileList = getMusicListOnSys(file);
        for (String key : fileList.keySet()) {
            Log.e(TAG, "onViewClicked: key= "+ key + " and value= " + fileList.get(key) );
        }
    }

    public Map<String, String> getMusicListOnSys(File file) {
        //从根目录开始扫描
        Log.e(TAG, file.getPath());
        getFileList(file, fileList);
        return fileList;
    }

    /**
     * @param path
     * @param fileList 注意的是并不是所有的文件夹都可以进行读取的，权限问题
     */
    private void getFileList(File path, Map<String, String> fileList) {
        Log.e(TAG, "getFileList: " + "001" );
        //如果是文件夹的话
        if (path.isDirectory()) {
            Log.e(TAG, "getFileList: " + "002" );




            //返回文件夹中有的数据
            File[] files = path.listFiles();
            //先判断下有没有权限，如果没有权限的话，就不执行了
            if (null == files)
                return;
            Log.e(TAG, "getFileList: " + "003" );
            for (int i = 0; i < files.length; i++) {
                Log.e(TAG, "getFileList--: " + "004" );
                getFileList(files[i], fileList);
            }
        }
        //如果是文件的话直接加入
        else {
            Log.e(TAG, "getFileList: " + "005" );
            Log.e(TAG, path.getAbsolutePath());
            //进行文件的处理
            String filePath = path.getAbsolutePath();
            //文件名
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            //添加
            fileList.put(fileName, filePath);
        }
    }


    /**
     * 删除指定目录下文件及目录
     * @param deleteThisPath
     * @return
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {


            try {
                File file = new File(filePath);
                //判断此file是文件夹还是文件
                if (file.isDirectory()) {// 是文件夹
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 权限的申请判断
     * 返回true，说明有权限，返回false说明没有权限，需要获取权限
     */
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private boolean verifyStoragePermissions(Activity activity) {

        if(Build.VERSION.SDK_INT < 23) /*******below android 6.0*******/
        {
            return true;
        }

        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity,PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
            return false;
        }
        else
        {
            return true;
        }
    }

    //如果verifyStoragePermissions 返回时false，那么需要实现权限的申请处理，实现onRequestPermissionResult
    // 获取到权限之后，再次调用一下所要做的事的方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //do request function(into apk)
                    ButterKnife.bind(this);
                    Log.e(TAG, "onRequestPermissionsResult: " + "=====================" );
                } else {

                    Toast.makeText(getApplicationContext(),
                            "storage permission denied,it will exit apk",
                            Toast.LENGTH_LONG).show();
                    Log.e(TAG, "onRequestPermissionsResult: " + "ssssssssssssssssssssss" );
                    ButterKnife.bind(this);

                 //do request function(exitapk)
                    finish();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}


