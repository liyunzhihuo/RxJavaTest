package xiao.testrxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * RxJava 最重要的就是Observable（被观察者）,subscribe（订阅）,Observer（观察者）或者Subscriber（订阅者）
 * <p>
 * Observable也就是数据（事件）源,Subscriber负责接收以及处理数据（事件）.当然要想实现两者通信，需要有一种机制,那就是订阅.
 * Observer 通过 subscribe() 方法建立订阅关系,从而 Observable 可以在需要的时候发出事件来通知 Observer.
 * <p>
 * 在RxJava中，有三个事件回调方法,分别是onNext(),OnError(),onCompleted().onNext()是最终输出及处理数据的回调,在发射数据过程中出现错误异常会回调OnError()方法,当不会再有新的 onNext() 发出时,就会触发 onCompleted() 方法.OnError()和onCompleted()是互斥的
 */
public class RxJavaBaseMessageActivity extends AppCompatActivity {
    private String TAG = "RxJavaBaseMessage";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_base_message_act);
        initUI();
    }

    private void initUI() {
        Button btnBaseTest = findViewById(R.id.btn_base_test);
        btnBaseTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseTest();
            }
        });
    }

    private void baseTest() {
        Observable observable = Observable.just("练气", "筑基", "金丹", "元婴", "化神", "大乘");
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe ");
            }

            @Override
            public void onNext(Object o) {
                Log.e(TAG, "onNext o =" + o);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError e=" + e.getMessage() + "," + e.getCause());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        };
        observable.subscribe(observer);
    }

}
