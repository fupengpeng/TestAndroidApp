package com.jiudianlianxian.memoryspace;

import android.content.Context;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @class_name: LocationActivity
 * @Description: 描述
 * @Company: 山东九点连线信息技术有限公司
 * @ProjectName: com.jiudianlianxian.memoryspace
 * @author fupengpeng
 * @date 2017/11/28 0028 9:10
 */
public class LocationActivity extends AppCompatActivity {

   /**
    * @MethodName:
    * @Description: 描述
    * @ProjectName: com.jiudianlianxian.memoryspace.LocationActivity
    * @author: fupengpeng
    * @Date: 2017/11/28 0028 9:10
    * @MethodParameters:
    * @MethodReturnType:
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        init(5,"5");
    }


    /**
     * @MethodName:
     * @Description: 描述
     * @ProjectName: com.jiudianlianxian.memoryspace.LocationActivity
     * @author: fupengpeng
     * @Date: 2017/11/28 0028 9:10
     * @MethodParameters:
     * @MethodReturnType:
     */
    private String init(int a ,String b) {

        String serviceString = Context.LOCATION_SERVICE;// 获取的是位置服务
        LocationManager locationManager = (LocationManager) getSystemService(serviceString);// 调用getSystemService()方法来获取LocationManager对象
        return  "zhangsan";
    }

}
