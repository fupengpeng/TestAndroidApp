package com.jdlx.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity implements OnItemSelectedListener {



    private Spinner spinner,spinner03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spinner = (Spinner) findViewById(R.id.spinner02);
        spinner.setOnItemSelectedListener(this);

        // 获取string.xml的资源文件的数组
        String[] citys = getResources().getStringArray(R.array.citys);

        // 数组适配器的创建
        // 1.实例化的方式创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, citys);
        // 2.API建议的方式创建adapt
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.citys, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        //设置下拉试图的样式
        //adapter1.setDropDownViewResource(android.R.layout.simple_list_item_checked);

        spinner.setAdapter(adapter);

        //自定义的适配器下垃选项
        spinner03 = (Spinner) findViewById(R.id.spinner03);
        // 建立数据源
        List<Person>  persons=new ArrayList<Person>();
        persons.add(new Person("张三", "上海 "));
        persons.add(new Person("李四", "上海 "));
        persons.add(new Person("王五", "北京" ));
        persons.add(new Person("赵六", "广州 "));
        //  建立Adapter绑定数据源
        MyAdapter _MyAdapter=new MyAdapter(this, persons);
        //绑定Adapter
        spinner03.setAdapter(_MyAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //选中按钮的实现方法
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
        System.out.println("parent:"+parent.getClass()); //父组件 spinner
        System.out.println("view:"+view.getClass()); //加载的下拉列表 xml类型 TextView
        System.out.println("position="+position);
        System.out.println("id="+id);
        //spinner.getSelectedItem().toString() 被选中的下拉列表值
        //spinner.getSelectedView() 被选中的 下垃组件属性
        //spinner.getSelectedItemPosition()  被选中的位置
        //spinner.getSelectedItemId() 被选中的id
        String str=parent.getItemAtPosition(position).toString();

        Toast.makeText(this, "你点击选中的是:"+str, Toast.LENGTH_LONG).show();
    }

    //未选中的实现方法
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
