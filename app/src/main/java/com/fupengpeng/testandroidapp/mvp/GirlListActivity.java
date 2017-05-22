package com.fupengpeng.testandroidapp.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.fupengpeng.testandroidapp.R;
import com.fupengpeng.testandroidapp.mvp.adapter.GirlAdapter;
import com.fupengpeng.testandroidapp.mvp.bean.Girl;
import com.fupengpeng.testandroidapp.mvp.presenter.GirlPresenterV1;
import com.fupengpeng.testandroidapp.mvp.presenter.GirlPresenterV2;
import com.fupengpeng.testandroidapp.mvp.view.IGirlView;

import java.util.List;

public class GirlListActivity extends AppCompatActivity implements IGirlView{

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_list);

        listView = (ListView) findViewById(R.id.listview);
        /*
        MVP模式：
            View：
                view接口：目的是为了，其他Activity也可以使用此Presenter对象加载对应的数据model
                    例如：progressBar的使用，一个应用中多个Activity都加载网络数据，使用progressbar来展示加载过程，
                        此时，progressbar的view就可以通过Presenter对象加载完对应model的数据之后，提示展示
                持有（实例化）Presenter对象

            Presenter：
                持有（实例化）Model的接口类型（实例化接口，使用接口的实现类接收）

            ocp开闭原则
         */
//        listView.setAdapter(new GirlAdapter(this));
        //中间者,让他触发加载数据
//        new GirlPresenterV1(this).fetch();
        new GirlPresenterV2(this).fetch();
//        new GirlPresenterV1(this).setmGirlModel(1);//加载model001的数据本地
//        new GirlPresenterV1(this).setmGirlModel(2);//加载model002的数据网络

    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"正在拼命加载中。。。 。。。",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showGirls(List<Girl> girls) {
        listView.setAdapter(new GirlAdapter(this,girls));
    }
}
