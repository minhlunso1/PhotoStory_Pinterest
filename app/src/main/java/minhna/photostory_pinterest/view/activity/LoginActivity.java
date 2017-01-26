package minhna.photostory_pinterest.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.pinterest.android.pdk.PDKCallback;
import com.pinterest.android.pdk.PDKClient;
import com.pinterest.android.pdk.PDKException;
import com.pinterest.android.pdk.PDKResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhna.photostory_pinterest.R;
import minhna.photostory_pinterest.model.BasicProfile;
import minhna.photostory_pinterest.model.PSMode;
import minhna.photostory_pinterest.module.AC;
import minhna.photostory_pinterest.module.AP;

/**
 * Created by Administrator on 25-Jan-17.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.pb_img)
    ProgressBar progressBar;
    @BindView(R.id.content)
    View content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        hideBars();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PDKClient.getInstance().onOauthResponse(requestCode, resultCode, data);
        if (resultCode==AppCompatActivity.RESULT_OK && data!=null) {
            content.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void authenticatePDK() {
        List<String> scopes = new ArrayList<>();
        scopes.add(PDKClient.PDKCLIENT_PERMISSION_READ_PUBLIC);

        PDKClient.getInstance().login(this, scopes, new PDKCallback() {
            @Override
            public void onSuccess(PDKResponse response) {
                String strResp = response.getData().toString();
                Log.d(AC.TAG_NAME, strResp);
                progressBar.setVisibility(View.GONE);
                BasicProfile profile = new Gson().fromJson(strResp, BasicProfile.class);
                MainActivity.go(LoginActivity.this, profile);
                finish();
            }
            @Override
            public void onFailure(PDKException exception) {
                Log.e(AC.TAG_NAME, exception.getDetailMessage());
            }
        });
    }

    private void hideBars() {
        if (getSupportActionBar()!=null)
            getSupportActionBar().hide();
    }

    public void beginWithAuthorBoard(View view) {
        AP.saveData(this, AC.PATH_MODE, PSMode.AUTHOR);
        authenticatePDK();
    }

    public void beginWithUserBoard(View view) {
        AP.saveData(this, AC.PATH_MODE, PSMode.USER);
        authenticatePDK();
    }
}
