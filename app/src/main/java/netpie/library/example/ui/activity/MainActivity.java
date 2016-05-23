package netpie.library.example.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import netpie.library.example.R;
import netpie.library.example.app.App;
import netpie.library.example.rest.model.ApiResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @InjectView(R.id.imageButtonSub)
    protected ImageButton imgSub;

    @InjectView(R.id.imageButtonPub)
    protected ImageButton imgPub;

    @InjectView(R.id.imageButtonRead)
    protected ImageButton imgRead;

    @InjectView(R.id.imageButtonDelete)
    protected ImageButton imgDel;

    @InjectView(R.id.editTextSub)
    protected EditText etSub;

    @InjectView(R.id.editTextAPPID)
    protected EditText etApp;

    @InjectView(R.id.editTextKey)
    protected EditText etKey;

    @InjectView(R.id.editTextData)
    protected EditText etPub;

    @InjectView(R.id.editTextRead)
    protected EditText etRead;

    //@InjectView(R.id.tvSerial)
    protected TextView tvSerial;
    private ScrollView mSvText;

    String strTopic,strPayload;
    String strCode,strMessage;

    String app_id     = "YOUR APP ID";
    String key_secret = "YOUR REST API KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mSvText = (ScrollView) findViewById(R.id.svText);
        tvSerial = (TextView) findViewById(R.id.tvSerial);
        tvSerial.setMovementMethod(new ScrollingMovementMethod());

        ButterKnife.inject(this);
        tvSerial.setText("");

        etApp.setText(app_id);
        etKey.setText(key_secret);

    }//onCreate

    @OnClick(R.id.imageButtonSub)
    protected void onSubClick() {

        String strSub = etSub.getText().toString();
        App.getRestClient().getNetPieService()
            .subTopic(app_id,strSub, key_secret,
                new Callback<ApiResponse>() {
                    @Override
                    public void success(ApiResponse apiResponse, Response response) {
                        try{
                            strCode = apiResponse.getCode();
                            strMessage = apiResponse.getMessage();
                        }
                        catch (Exception e){
                            strCode = "No Data";
                            strMessage = "No Data";
                        };

                        Log.e(TAG, "code : " + strCode);
                        Log.e(TAG, "message : " + strMessage);

                        tvSerial.setTextColor(0xFF00FF00);
                        tvSerial.append("\ncode : " + strCode);
                        tvSerial.append("\nmessage : " + strMessage);
                        mSvText.fullScroll(ScrollView.FOCUS_DOWN);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, "Error : " + error.getMessage());
                        tvSerial.setTextColor(0xFFFF0000);
                        tvSerial.setText(error.getMessage());
                        Crouton.makeText(MainActivity.this, error.getMessage(), Style.ALERT).show();
                    }
                });
    } // onSubClick

    @OnClick(R.id.imageButtonPub)
    protected void onPubClick() {

        String strSub = etSub.getText().toString(); // topic
        String strPub = etPub.getText().toString(); //

        App.getRestClient().getNetPieService()
            .pubTopic(app_id,strSub,"", key_secret,strPub,
                new Callback<ApiResponse>() {
                    @Override
                    public void success(ApiResponse apiResponse, Response response) {

                        try{
                            strCode = apiResponse.getCode();
                            strMessage = apiResponse.getMessage();
                        }
                        catch (Exception e){
                            strCode = "No Data";
                            strMessage = "No Data";
                        };

                        Log.e(TAG, "code : " + strCode);
                        Log.e(TAG, "message : " + strMessage);

                        tvSerial.setTextColor(0xFF00FF00);
                        tvSerial.append("\ncode : " + strCode);
                        tvSerial.append("\nmessage : " + strMessage);
                        mSvText.fullScroll(ScrollView.FOCUS_DOWN);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, "Error : " + error.getMessage());
                        tvSerial.setTextColor(0xFFFF0000);
                        tvSerial.setText(error.getMessage());
                        Crouton.makeText(MainActivity.this, error.getMessage(), Style.ALERT).show();
                    }
                });
} // onPubClick


    @OnClick(R.id.imageButtonRead)
    protected void onReadClick() {

        String strRead = etRead.getText().toString();
        App.getRestClient().getNetPieService()
            .getData(app_id,strRead, key_secret,
                new Callback<List<ApiResponse>>() {
                    @Override
                    public void success(List<ApiResponse> apiResponse, Response response) {

                        try{
                            strTopic = apiResponse.get(0).getStrTopic();
                            strPayload = apiResponse.get(0).getStrPayload();
                        }
                        catch (Exception e){
                            strTopic = "No Data";
                            strPayload = "No Data";
                        };

                        Log.e(TAG, "topic : " + strTopic);
                        Log.e(TAG, "payload : " + strPayload);

                        tvSerial.setTextColor(0xFF00FF00);
                        tvSerial.append("\nTopic : " + strTopic);
                        tvSerial.append("\nPayload : " + strPayload);
                        mSvText.fullScroll(ScrollView.FOCUS_DOWN);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, "Error : " + error.getMessage());
                        tvSerial.setTextColor(0xFFFF0000);
                        tvSerial.setText(error.getMessage());
                        Crouton.makeText(MainActivity.this, error.getMessage(), Style.ALERT).show();
                    }
                });
    } // onReadClick

    @OnClick(R.id.imageButtonDelete)
    protected void onDelClick() {
        tvSerial.setText("");
    }


}//class

