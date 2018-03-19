package com.fupengpeng.testandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fupengpeng.testandroidapp.mvp.GirlGridActivity;
import com.fupengpeng.testandroidapp.mvp.GirlListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        TextView tvGrid = (TextView) findViewById(R.id.tv_grid);
        TextView tvList = (TextView) findViewById(R.id.tv_list);
        tvGrid.setOnClickListener(this);
        tvList.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_grid:
                intent = new Intent(MainActivity.this, GirlGridActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_list:
                intent = new Intent(MainActivity.this, GirlListActivity.class);
                startActivity(intent);
                break;

        }
    }
}
