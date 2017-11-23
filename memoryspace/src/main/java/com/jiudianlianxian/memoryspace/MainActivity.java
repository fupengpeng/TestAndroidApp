package com.jiudianlianxian.memoryspace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiudianlianxian.utils.MemoryManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_activity_main_internal_storage,
            R.id.btn_activity_main_sd_card,
            R.id.btn_activity_main_operation,
            R.id.btn_activity_main_usable_operation,
            R.id.btn_activity_main_equipment_information})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_main_internal_storage:
                Long selfLong = MemoryManager.getSelfMemory(true);
                txActivityMainInternalStorage.setText("内存大小 == " + MemoryManager.getSize(selfLong));
                break;
            case R.id.btn_activity_main_sd_card:
                Long sdLong = MemoryManager.getSelfSDCardsMemory(true);
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
        }
    }

}
