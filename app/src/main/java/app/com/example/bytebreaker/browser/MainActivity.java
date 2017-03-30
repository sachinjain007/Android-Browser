package app.com.example.bytebreaker.browser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urlEdit;
    Button go, forward, clear, reload, back;
    ProgressBar progressBar;

    @Override
//    savedInstanceState is preferences
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declare WebView
        brow = (WebView) findViewById(R.id.wv_brow);
        urlEdit = (EditText) findViewById(R.id.et_url);
        go = (Button) findViewById(R.id.btn_go);
        back = (Button) findViewById(R.id.btn_bck);
        forward = (Button) findViewById(R.id.btn_fwd);
        clear = (Button) findViewById(R.id.btn_clear);
        reload = (Button) findViewById(R.id.btn_reload);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        brow.setWebViewClient(new WebViewClient());
        brow.setWebChromeClient(new WebChromeClient(){
            // Making Function
            //onProgressChanged:- When website loaded or changed it call
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                // 100 means website loaded or not
                if(newProgress == 100){
                    progressBar.setVisibility(view.GONE);
                }
                else{
                    progressBar.setVisibility(view.VISIBLE);
                }

            }
        });

        //Enabling Javascript True

        WebSettings webSettings = brow.getSettings();
        webSettings.setJavaScriptEnabled(true);

        brow.loadUrl("https://bytebreaker.in/");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take Data from Edit Text
                String editTextValue = urlEdit.getText().toString();

                if(!editTextValue.startsWith("http://"))
                    editTextValue = "http://"+editTextValue;
                String url = editTextValue;
                brow.loadUrl(url);

                //Hide KeyBoard after using Edit Text
                //Gets keyboard open up and store instace in imm
                InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                //Hide getWindowToken which window of talking about , and Hide keyboard 0
                imm.hideSoftInputFromWindow(urlEdit.getWindowToken(),0);

            }
        });


        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brow.canGoForward())
                    brow.goForward();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brow.canGoBack())
                    brow.canGoBack();
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brow.reload();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brow.clearHistory();
            }
        });

    }
}
