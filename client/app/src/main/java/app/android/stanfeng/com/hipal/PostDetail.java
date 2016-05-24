// Create by Anan Wang
package app.android.stanfeng.com.hipal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PostDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        TextView nickname = (TextView) findViewById(R.id.detail_nickname);
        nickname.setText(getIntent().getExtras().getString("nickname"));

        TextView signature = (TextView) findViewById(R.id.detail_signature);
        signature.setText("Lucky to be here " + getIntent().getExtras().getString("city"));

        Button backBtn = (Button) findViewById(R.id.detail_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(PostDetail.this, MainActivity.class);
                mainIntent.putExtra("token", getIntent().getExtras().getString("token"));
                startActivity(mainIntent);
            }
        });
    }

}
