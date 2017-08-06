package com.example.xutils_demo2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //查找控件
    @ViewInject(R.id.btn1)
    private Button btn1;
    @ViewInject(R.id.textview)
    private TextView textView;

    private List<DataBean> list = new ArrayList<>();
    private String urlPath = "http://huixinguiyu.cn/Assets/js/data.js";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //展示当前视图
        x.view().inject(this);
        //点击事件
        btn1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                doGet();
                break;
            default:;
                break;
        }

    }
    //获取数据方法
    private void doGet(){
        RequestParams params = new RequestParams("http://huixinguiyu.cn/Assets/js/data.js");
        //从网络获取数据
        x.http().get(params, new Callback.CommonCallback<String>() {
            //网络请求成功时的操作
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                DataBean json = gson.fromJson(result,DataBean.class);
                textView.setText(result);
            }
            //出现错误时的
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
            //取消时
            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


}
