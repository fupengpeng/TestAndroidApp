package com.fupengpeng.shopcartselect.shopping01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.fupengpeng.shopcartselect.R;
import com.fupengpeng.shopcartselect.shopping01.fragment.ShoppingCartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingCartActivity extends AppCompatActivity {



    @BindView(R.id.iv_title_activity_left)
    ImageView ivTitleActivityLeft;

    @BindView(R.id.tv_title_activity_left)
    TextView tvTitleActivityLeft;

    @BindView(R.id.tv_title_activity_title)
    TextView tvTitleActivityTitle;

    @BindView(R.id.tv_title_activity_right)
    TextView tvTitleActivityRight;

    @BindView(R.id.iv_title_activity_right)
    ImageView ivTitleActivityRight;


    @BindView(R.id.ll_activity_main_parent)
    LinearLayout llActivityMainParent;

    @BindView(R.id.iv_activity_main_home)
    ImageView ivActivityMainHome;
    @BindView(R.id.tv_activity_main_home)
    TextView tvActivityMainHome;
    @BindView(R.id.ll_activity_main_home)
    LinearLayout llActivityMainHome;

    @BindView(R.id.iv_activity_main_classify)
    ImageView ivActivityMainClassify;
    @BindView(R.id.tv_activity_main_classify)
    TextView tvActivityMainClassify;
    @BindView(R.id.ll_activity_main_classify)
    LinearLayout llActivityMainClassify;

    @BindView(R.id.iv_activity_main_shopping_cart)
    ImageView ivActivityMainShoppingCart;
    @BindView(R.id.tv_activity_main_shopping_cart)
    TextView tvActivityMainShoppingCart;
    @BindView(R.id.ll_activity_main_shopping_cart)
    LinearLayout llActivityMainShoppingCart;

    @BindView(R.id.iv_activity_main_share)
    ImageView ivActivityMainShare;
    @BindView(R.id.tv_activity_main_share)
    TextView tvActivityMainShare;
    @BindView(R.id.ll_activity_main_share)
    LinearLayout llActivityMainShare;

    @BindView(R.id.iv_activity_main_person_center)
    ImageView ivActivityMainPersonCenter;
    @BindView(R.id.tv_activity_main_person_center)
    TextView tvActivityMainPersonCenter;
    @BindView(R.id.ll_activity_main_person_center)
    LinearLayout llActivityMainPersonCenter;


    /**
     * 购物车Fragment
     */
    private ShoppingCartFragment shoppingCartFragment;


    private Intent intent;

    /**
     * 设置展示Fragment的参数
     */
    private int setFragment = 100;

    /**
     * 主界面
     */
    private static final int HOME = 1;
    /**
     * 分类
     */
    private static final int CLASSIFY = 2;
    /**
     * 分享
     */
    private static final int SHARE = 3;
    /**
     * 购物车
     */
    private static final int SHOPPING_CART = 4;
    /**
     * 个人中心
     */
    private static final int PERSON_CENTER = 5;


    /**
     * 用于对Fragment的管理
     */
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    public static final String TAG = "MainActivity";
    private Bundle bundle;

    /**
     * 用于购物车编辑按钮的处理事件
     */
    private boolean batchModel;
    private Button btnFragmentShoppingCartSettlement;
    private LinearLayout llFragmentShoppingCartPriceTotal;
    private double totalPrice;
    private TextView tvFragmentShoppingCartTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);



//        EventBus.getDefault().register(this);//订阅


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//			EventBus.getDefault().unregister(this);//解除订阅

    }

//		@Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
//		public void onDataSynEvent(ShoppingCartFragmentEvent shoppingCartFragmentEvent) {
//			batchModel = shoppingCartFragmentEvent.isBatchModel();
//			Log.e(TAG, "onDataSynEvent: ---------------------------------"+batchModel );
//			btnFragmentShoppingCartSettlement = shoppingCartFragmentEvent.getBtnFragmentShoppingCartSettlement();
//			llFragmentShoppingCartPriceTotal = shoppingCartFragmentEvent.getLlFragmentShoppingCartPriceTotal();
//			totalPrice = shoppingCartFragmentEvent.getTotalPrice();
//			tvFragmentShoppingCartTotal = shoppingCartFragmentEvent.getTvFragmentShoppingCartTotal();
//			Log.e(TAG, "event---->" + shoppingCartFragmentEvent.getTotalPrice());
//
//
//		}


    @OnClick({R.id.ll_activity_main_home,
            R.id.ll_activity_main_classify,
            R.id.ll_activity_main_shopping_cart,
            R.id.ll_activity_main_share,
            R.id.ll_activity_main_person_center,
            R.id.iv_title_activity_right,
            R.id.tv_title_activity_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_activity_main_home:
                setTabSelection(1);
                break;
            case R.id.ll_activity_main_classify:
                setTabSelection(2);
                break;
            case R.id.ll_activity_main_share:
                setTabSelection(3);
                break;
            case R.id.ll_activity_main_shopping_cart:
                setTabSelection(4);
                break;
            case R.id.ll_activity_main_person_center:
                setTabSelection(5);
                break;
            case R.id.iv_title_activity_right:
                break;
            case R.id.tv_title_activity_right:
                // TODO: 2017/6/29 0029  在购物车fragment展示时，此按钮的变化，及相关操作

                Log.e(TAG, "onClick: " + "----0008----");
                batchModel = !batchModel;
                if (batchModel) {
                    tvTitleActivityRight.setText(getResources().getString(R.string.menu_enter));
                    btnFragmentShoppingCartSettlement.setText(getResources().getString(R.string.menu_del));
                    llFragmentShoppingCartPriceTotal.setVisibility(View.GONE);

                } else {
                    tvTitleActivityRight.setText(getResources().getString(R.string.menu_edit));

                    llFragmentShoppingCartPriceTotal.setVisibility(View.VISIBLE);
                    btnFragmentShoppingCartSettlement.setText(getResources().getString(R.string.menu_sett));
                    totalPrice = 0;
                    tvFragmentShoppingCartTotal.setText("￥" + totalPrice);

                }

                Log.e(TAG, "onViewClicked: "+batchModel+"=-=-=-=-=-=-=-=-=-" );
//                ShoppingCartFragmentEvent shoppingCartFragmentEvent =
//                        new ShoppingCartFragmentEvent(
//                                batchModel,
//                                btnFragmentShoppingCartSettlement,
//                                llFragmentShoppingCartPriceTotal,
//                                totalPrice,
//                                tvFragmentShoppingCartTotal);
//
//                EventBus.getDefault().post(shoppingCartFragmentEvent);
//                EventBus.getDefault().postSticky(new ShoppingCartFragmentEvent());

                break;
        }
    }


    /**
     * 根据传入的index参数来设置选中的Fragment。
     */
    private void setTabSelection(int index) {
//        Log.e(TAG, "setTabSelection: " );
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        //添加一个FragmentTransaction的实例
        fragmentManager = getSupportFragmentManager();
        // 开启一个Fragment事务
        transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);


        if (index == SHOPPING_CART) {
            // TODO: 2017/6/28 0028 判断是否是购物车Fragment在展示，如果是，界面不同地方的改变及事件处理
            tvTitleActivityRight.setVisibility(View.VISIBLE);
            tvTitleActivityRight.setText("编辑");

        } else {
            tvTitleActivityRight.setVisibility(View.GONE);
        }

        switch (index) {


            /**
             * 购物车界面
             */
            case SHOPPING_CART:
                tvTitleActivityTitle.setText("购物车");


                tvActivityMainShoppingCart.setTextColor(0xffff0000);
                if (shoppingCartFragment == null) {
                    shoppingCartFragment = new ShoppingCartFragment();
                    transaction.add(R.id.ll_activity_main_parent, shoppingCartFragment);
                } else {
                    transaction.show(shoppingCartFragment);
                }
                break;

        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。（字体颜色和view背景颜色）
     */
    private void clearSelection() {
        tvActivityMainHome.setTextColor(0xff000000);

        tvActivityMainClassify.setTextColor(0xff000000);

        tvActivityMainShare.setTextColor(0xff000000);

        tvActivityMainShoppingCart.setTextColor(0xff000000);

        tvActivityMainPersonCenter.setTextColor(0xff000000);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     * 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
//        Log.e(TAG, "hideFragments: " );

        if (shoppingCartFragment != null) {
            transaction.hide(shoppingCartFragment);
        }

    }

    /**
     * 初始化展示和点击筛选之后的fragment展示
     */
    private void questionList() {
        Intent intent = this.getIntent();
        setFragment = intent.getIntExtra("setFragment", 0);
        Log.e(TAG, "questionList: --------" + setFragment);
        switch (setFragment) {
            /**
             * 首页界面
             */
            case 100:
                setTabSelection(HOME);
                break;
            /**
             * 分类界面
             */
            case 200:
                setTabSelection(CLASSIFY);
                break;
            /**
             * 分享界面
             */
            case 300:
                setTabSelection(SHARE);
                break;
            /**
             * 购物车界面
             */
            case 400:
                setTabSelection(SHOPPING_CART);
                break;
            /**
             * 个人中心界面
             */
            case 500:
                setTabSelection(PERSON_CENTER);
                break;
            /**
             * 默认首页界面
             */
            default:
                setTabSelection(HOME);
                break;
        }
    }









}
