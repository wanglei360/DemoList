package www.yj.com.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import www.yj.com.myapplication.dialog.SweetAlertDialog;

public class MainActivity extends Activity {
    private Handler handler = new Handler();
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void showDialog_(int errorType){
        dialog = new SweetAlertDialog(this, errorType);
        dialog.setTitleText("提示").setContentText("你想看到啥?").show();
    }

    public void NORMAL_TYPE(View view) {
        showDialog_(SweetAlertDialog.NORMAL_TYPE);
    }

    public void ERROR_TYPE(View view) {
        showDialog_(SweetAlertDialog.ERROR_TYPE);
    }

    public void SUCCESS_TYPE(View view) {
        showDialog_(SweetAlertDialog.SUCCESS_TYPE);
    }

    public void WARNING_TYPE(View view) {
        showDialog_(SweetAlertDialog.WARNING_TYPE);
    }

    public void CUSTOM_IMAGE_TYPE(View view) {
        showDialog_(SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 5000);
    }

    public void PROGRESS_TYPE(View view) {
        showDialog_(SweetAlertDialog.PROGRESS_TYPE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 5000);
    }

    public void PROGRESS_TYPES(View view) {
        showDialog_(SweetAlertDialog.PROGRESS_TYPES);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 5000);
    }

}
