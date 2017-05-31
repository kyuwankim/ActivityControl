package com.kyuwankim.android.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStart, btnResult;
    Intent intent;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, SubActivity.class);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnResult = (Button) findViewById(R.id.btnResult);
        editText = (EditText) findViewById(R.id.editText);

        btnStart.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }
    public static final int BUTTON_START = 98;
    public static final int BUTTON_RESULT = 99;

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            // 일반적인 Activity start
            case R.id.btnStart :
                intent.putExtra("key", "");
                startActivityForResult(intent, BUTTON_START);
                break;
            // 값을 돌려받는 Activity start
            case R.id.btnResult :

                intent.putExtra("key", editText.getText().toString());
                startActivityForResult(intent, BUTTON_RESULT);
                // start SubActivity > finish() > 결과값을 돌려준다 > MainActivity.onActivityResult(결과값)
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 결과값이 담겨온다.
        //super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "Result Code="+resultCode, Toast.LENGTH_SHORT).show();

        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case BUTTON_RESULT:
                    // Intent 인 data에서 result 변수로 값을 꺼내는데
                    // 값이 없을경우 디폴트값으로 0 을 사용한다.
                    int result = data.getIntExtra("result",0);

                    editText.setText("결과값="+result);
                    break;
                case BUTTON_START:
                    Toast.makeText(this, "Start 버튼을 눌렀다가 돌아옴", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
