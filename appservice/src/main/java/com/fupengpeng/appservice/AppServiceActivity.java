package com.fupengpeng.appservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.NotFoundCacheError;
import com.yolanda.nohttp.error.ParseError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.net.ProtocolException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AppServiceActivity extends AppCompatActivity {


    @BindView(R.id.btn_activity_app_service_button)
    Button btnActivityAppServiceButton;
    @BindView(R.id.tv_activity_app_service_content)
    TextView tvActivityAppServiceContent;

    private static final String TAG = "Socket_Android";


    Unbinder unbinder;
    @BindView(R.id.tv_activity_app_service_title)
    TextView tvActivityAppServiceTitle;
    @BindView(R.id.et_activity_app_service_account)
    EditText etActivityAppServiceAccount;
    @BindView(R.id.et_activity_app_service_password)
    EditText etActivityAppServicePassword;
    @BindView(R.id.tv_activity_app_service_account_password)
    TextView tvActivityAppServiceAccountPassword;

    //请求队列
    private RequestQueue requestQueue;
    //请求时的等待框
    private WaitDialog waitDialog;

    //用来标志请求的what
    private static final int NOHTTP_WHAT = 0x001;

    private String account;

    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_service);
        unbinder = ButterKnife.bind(this);

        //创建请求队列
        requestQueue = NoHttp.newRequestQueue();

        //创建等待框
        waitDialog = new WaitDialog(this);


        etActivityAppServiceAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                account = etActivityAppServiceAccount.getText().toString().trim();
            }
        });
        etActivityAppServicePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = etActivityAppServicePassword.getText().toString().trim();
            }
        });

    }


    @OnClick(R.id.btn_activity_app_service_button)
    public void onViewClicked() {

        // TODO: 2017/7/10 0010 网络请求


//        setTitle("测试Socket连接");
//        Socket socket = null;
//
//        try {
//                    /* 指定Server的IP地址，此地址为局域网地址，如果是使用WIFI上网，则为PC机的WIFI IP地址
//                     * 在ipconfig查看到的IP地址如下：
//                     * Ethernet adapter 无线网络连接:
//                     * Connection-specific DNS Suffix  . : IP Address. . . . . . . . . . . . : 192.168.1.100
//                     */
//            InetAddress serverAddr = InetAddress.getByName("192.168.1.100");// TCPServer.SERVERIP
//            Log.d("TCP", "C: Connecting...");
//
//            // 应用Server的IP和端口建立Socket对象
//            socket = new Socket(serverAddr, 51706);
//            String message = "---Test_Socket_Android---";
//
//            Log.d("TCP", "C: Sending: '" + message + "'");
//
//            // 将信息通过这个对象来发送给Server
//            PrintWriter out = new PrintWriter(new BufferedWriter(
//                    new OutputStreamWriter(socket.getOutputStream())),
//                    true);
//
//            // 把用户输入的内容发送给server
//            String toServer = tvActivityAppServiceTitle.getText().toString();
//            Log.d(TAG, "To server:'" + toServer + "'");
//            out.println(toServer);
//            out.flush();
//
//
//            // 接收服务器信息
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()));
//            // 得到服务器信息
//            String msg = in.readLine();
//            Log.d(TAG, "From server:'" + msg + "'");
//            // 在页面上进行显示
//            tvActivityAppServiceContent.setText(msg);
//        } catch (UnknownHostException e) {
//            Log.e(TAG, "192.168.1.100 is unkown server!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                socket.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


        //创建String请求
        Request<String> request = NoHttp.createStringRequest("http://192.168.1.174:8080/ServletTest/FirstServlet");

        //添加请求参数
            request.add("account", account);// String类型
            request.add("password", password);
//            request.add("userAge", 20);// int类型
//            request.add("userSex", '1');// char类型，还支持其它类型

        //将request加入requestQueue
        requestQueue.add(NOHTTP_WHAT, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int i) {
                // 请求开始，这里可以显示一个dialog
                if (waitDialog != null && !waitDialog.isShowing())
                    waitDialog.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                // 根据what判断是哪个请求的返回，这样就可以用一个OnResponseListener来接受多个请求的结果。
                if (what == NOHTTP_WHAT) {
                    // 服务器响应码。
                    int responseCode = response.getHeaders().getResponseCode();
                    // 这里一定要判断状态码，经测试，404错误时也走这个回调方法
                    if (responseCode == 200) {
                        // 响应结果。
                        String result = response.get();
                        //显示结果
                        tvActivityAppServiceContent.setText(result);

                        // 拿到请求时设置的tag。
                        Object tag = response.getTag();

                        // 响应头
                        Headers headers = response.getHeaders();

                        String headResult = "响应码：%1$d\n花费时间：%2$d毫秒。";
                        headResult = String.format(Locale.getDefault(), headResult,
                                headers.getResponseCode(), response.getNetworkMillis());
                        tvActivityAppServiceTitle.setText(headResult);
                    }
                }
            }

            @Override
            public void onFailed(int i, Response<String> response) {
                // 请求失败
                Exception exception = response.getException();
                if (exception instanceof NetworkError) {// 网络不好
                    Toast.makeText(AppServiceActivity.this, "请检查网络。", Toast.LENGTH_SHORT).show();
                } else if (exception instanceof TimeoutError) {// 请求超时
                    Toast.makeText(AppServiceActivity.this, "请求超时，网络不好或者服务器不稳定。", Toast.LENGTH_SHORT).show();
                } else if (exception instanceof UnKnownHostError) {// 找不到服务器
                    Toast.makeText(AppServiceActivity.this, "未发现指定服务器，清切换网络后重试。", Toast.LENGTH_SHORT).show();
                } else if (exception instanceof URLError) {// URL是错的
                    Toast.makeText(AppServiceActivity.this, "URL错误。", Toast.LENGTH_SHORT).show();
                } else if (exception instanceof NotFoundCacheError) {
                    // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
                    Toast.makeText(AppServiceActivity.this, "没有找到缓存.", Toast.LENGTH_SHORT).show();
                } else if (exception instanceof ProtocolException) {
                    Toast.makeText(AppServiceActivity.this, "系统不支持的请求方法。", Toast.LENGTH_SHORT).show();
                } else if (exception instanceof ParseError) {
                    Toast.makeText(AppServiceActivity.this, "解析数据时发生错误。", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AppServiceActivity.this, "未知错误。", Toast.LENGTH_SHORT).show();
                }
                Logger.e("错误：" + exception.getMessage());
            }

            @Override
            public void onFinish(int i) {
                // 请求结束，这里关闭dialog
                if (waitDialog != null && waitDialog.isShowing())
                    waitDialog.dismiss();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
