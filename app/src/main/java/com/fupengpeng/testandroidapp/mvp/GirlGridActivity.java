package com.fupengpeng.testandroidapp.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.fupengpeng.testandroidapp.R;
import com.fupengpeng.testandroidapp.mvp.adapter.GirlAdapter;
import com.fupengpeng.testandroidapp.mvp.bean.Girl;
import com.fupengpeng.testandroidapp.mvp.presenter.GirlPresenterV2;
import com.fupengpeng.testandroidapp.mvp.view.IGirlView;

import java.util.List;

public class GirlGridActivity extends AppCompatActivity implements IGirlView{

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_grid);
        gridView = (GridView) findViewById(R.id.gridview);

        //中间者
        new GirlPresenterV2(this).fetch();
    }


    @Override
    public void showLoading() {
        Toast.makeText(this,"正在拼命加载中。。。 。。。",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showGirls(List<Girl> girls) {
        gridView   .setAdapter(new GirlAdapter(this,girls));
    }
}
