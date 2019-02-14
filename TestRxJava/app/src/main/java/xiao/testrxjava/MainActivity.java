package xiao.testrxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * RxJava
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startToBaseMessage(View view) {
        startToClass(RxJavaBaseMessageActivity.class);
    }
    public void startToOperator(View view) {
        startToClass(RxJavaOperatorActivity.class);
    }


    private void startToClass(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
