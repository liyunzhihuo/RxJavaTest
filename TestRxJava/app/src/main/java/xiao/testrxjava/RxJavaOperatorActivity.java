package xiao.testrxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 1.Create
 * 创建一个Observable
 */
public class RxJavaOperatorActivity extends AppCompatActivity {
    private String TAG = "RxJavaOperator";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_operator_act);
        initUI();
    }

    private void initUI() {
        Button btnCreate = findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutCreate();
            }
        });
    }

    private void aboutCreate() {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                //可以多次调用onNext("大家好")发射数据
                emitter.onNext("大家好");
                emitter.onNext("我开始学习RxJava");
                emitter.onComplete();
            }
        });
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
