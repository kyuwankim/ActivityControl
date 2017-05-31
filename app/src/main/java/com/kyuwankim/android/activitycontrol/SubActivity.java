package com.kyuwankim.android.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    String value="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        // 1. 이전 activity에서 넘어온 intent 객체
        Intent intent = getIntent(); // 여기는 null 이 안된다.
        // 2. 값의 묶음을 꺼낸다.
        Bundle bundle = intent.getExtras(); // 여기는 전달된 값이 없으면 null 이 된다.
        // 3. 단일 값을 꺼낸다. 꺼내기 전에 null 체크를 해줘야 한다.
        if(bundle != null) {
            value = bundle.getString("key");
            // 3.1 값이 있으면 textView에 출력한다.
            textView.setText(value);
        }
        // 위의 두 줄(2,3번)을 합쳐놓은 method
        // - 자체적으로 bundle에 대한 null 처리 로직을 포함하고 있다.
        //String value = intent.getStringExtra("key");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity에서 넘겨받은 값을 int 로 변환
                int num1 = Integer.parseInt(value);
                // 현재 Activity에 입력된 값을 받아서
                String temp = editText.getText().toString();
                // int 로 변환
                int num2 = Integer.parseInt(temp);
                int result = num1 + num2;

                /* 값 반환하기 */

                // 1. 결과값을 intent에 담아서
                Intent intent = new Intent();
                intent.putExtra("result", result);

                // 2. setResult에 넘겨준다.
                setResult(RESULT_OK, intent);

                // 3. 현재 activity를 종료한다.
                finish();
            }
        });
    }
}
