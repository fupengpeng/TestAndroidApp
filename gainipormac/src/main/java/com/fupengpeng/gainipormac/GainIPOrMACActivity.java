package com.fupengpeng.gainipormac;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GainIPOrMACActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "GainIPOrMACActivity";
    private TextView tvIP;
    private TextView tvMAC;
    InetAddress ip;
    private StringBuilder mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gain_ipor_mac);

        Log.e(TAG, "onCreate: "+"001" );
        tvIP = (TextView) findViewById(R.id.tv_ip);
        tvMAC = (TextView) findViewById(R.id.tv_mac);
        Button btnIP = (Button) findViewById(R.id.btn_ip);
        Button btnMAC = (Button) findViewById(R.id.btn_mac);
        btnIP.setOnClickListener(this);
        btnMAC.setOnClickListener(this);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
            try {
                ip = InetAddress.getLocalHost();
                System.out.println("本機ip地址 : " + ip.getHostAddress());

                tvIP.setText("本机ip地址："+ip.getHostAddress());
                Log.e(TAG, "onClick: "+"003" );


            } catch (UnknownHostException e) {

                e.printStackTrace();

            }
        }
    };

    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", "请求结果");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ip:

                Log.e(TAG, "onClick: "+"002" );
                // 开启一个子线程，进行网络操作，等待有返回结果，使用handler通知UI
                new Thread(networkTask).start();

                break;

            case R.id.btn_mac:
                NetworkInterface network = null;
                try {
                    network = NetworkInterface.getByInetAddress(ip);
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "onClick: "+"005" );
                byte[] mac = new byte[0];
                try {
                    mac = network.getHardwareAddress();
                } catch (SocketException e) {
                    e.printStackTrace();
                }


                this.mac = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    Log.e(TAG, "onClick: "+"006" );
                    this.mac.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                Log.e(TAG, "onClick: "+"007" );
                tvMAC.setText("本机MAC地址："+this.mac.toString());
                break;
        }
    }
}
