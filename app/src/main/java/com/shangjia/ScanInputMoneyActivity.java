package com.shangjia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.shangjia.views.HeaderView;

/**
 * Created by dai on 2015/12/17.
 */
public class ScanInputMoneyActivity extends Activity {

    TextView mMoneyNumber;
    Button submitButton;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_inputmoney);

        HeaderView headerView = (HeaderView) findViewById(R.id.header);
        headerView.hideRightImage();
        headerView.setTitle(R.string.receivemoney);
        headerView.setTitleLeft();


        mMoneyNumber = (TextView) findViewById(R.id.id_input_money);
        submitButton = (Button) findViewById(R.id.id_scan_submit_button);

        submitButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String errorMsg = null;

                    String moneyNumber = mMoneyNumber.getText().toString();
                    Double money = null;
                    try{
                        money = Double.parseDouble(moneyNumber);
                    }catch (Exception e){
                        errorMsg = getString(R.string.error_field_be_number);
                    }
                    if( money!=null && money <=0){
                        errorMsg = getString(R.string.error_field_be_positive);
                    }
                    if( errorMsg != null){
                        mMoneyNumber.setError( errorMsg );
                        mMoneyNumber.requestFocus();
                    }else{
                        Toast.makeText(ScanInputMoneyActivity.this, "Trigger scan activity!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ScanInputMoneyActivity.this, MipcaActivityCapture.class);
                        startActivity(intent);
                    }

                }
            }
        );

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ScanInputMoney Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.shangjia/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ScanInputMoney Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.shangjia/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
