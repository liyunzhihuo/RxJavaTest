package xiao.testrxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableFromCallable;


/**
 * 1.Create
 * 创建一个Observable
 * 2.From
 * 其它种类的对象和数据类型转换为Observable
 * 3.Just
 * 类似于From
 * 4.Empty/Never/Error
 * Empty 创建一个不发射任何数据但是正常终止的Observable
 * Never 创建一个不发射数据也不终止的Observable
 * Error 创建一个不发射数据以一个错误终止的Observable
 * 5.Range
 * 该操作符创建特定整数序列的Observable
 * 6.Timer
 * Timer 操作符创建一个在给定的时间段之后返回一个特殊值的Observable.它在延迟一段给定的时间后发射一个简单的数字0
 * 7.Interval
 * Interval 该操作符按固定的时间间隔发射一个无限递增的整数序列
 * 8.Repeat
 * Repeat 该操作符是重复的发射某个数据序列，并且可以自己设置重复的次数
 */
public class RxJavaOperatorActivity extends AppCompatActivity {
    private String TAG = "RxJavaOperator";
    String text = "旧数据";

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

        Button btnFrom = findViewById(R.id.btn_from);
        btnFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutFrom();
            }
        });

        Button btnJust = findViewById(R.id.btn_just);
        btnJust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutJust();
            }
        });

        Button btnRange = findViewById(R.id.btn_range);
        btnRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutRange();
            }
        });

        Button btnTimer = findViewById(R.id.btn_timer);
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutTimer();
            }
        });

        Button btnInterval = findViewById(R.id.btn_interval);
        btnInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutInterval();
            }
        });

        Button btnRepeat = findViewById(R.id.btn_repeat);
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutRepeat();
            }
        });

        Button btnDefer = findViewById(R.id.btn_defer);
        btnDefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutDefer();
            }
        });
    }

    private void aboutDefer() {
           Observable observable =Observable.defer(new Callable<ObservableSource>() {
            @Override
            public ObservableSource call() throws Exception {
                return Observable.just(text);
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
        text = "新数据";
        observable.subscribe(observer);
    }

    private void aboutRepeat() {
        String[] strs = {"也许当初忙着微笑和哭泣", "忙着追逐天空中的流星"};
        Observable.fromArray(strs).repeat(2).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe ");
            }

            @Override
            public void onNext(String o) {
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
        });
    }

    private void aboutInterval() {
        Observable observable = Observable.interval(1, TimeUnit.SECONDS);
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

    private void aboutTimer() {
        Observable observable = Observable.timer(5, TimeUnit.SECONDS);
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


    private void aboutRange() {
        Observable observable = Observable.range(4, 2);
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

    private void aboutFrom() {
        Object[] datas = new Object[]{1, "2", 3, null, 4};
        Observable observable = Observable.fromArray(datas);
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

    private void aboutJust() {
        Observable observable = Observable.just(1, 2, "3");
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
