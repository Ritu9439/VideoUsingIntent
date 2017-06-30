package eazy.videousingintent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edskypeusername;
    Button skypevideocall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edskypeusername= (EditText) findViewById(R.id.edskypeusername);
        skypevideocall= (Button) findViewById(R.id.btnvideocall);

        skypevideocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edskypeusername.getText().toString().length()<=0)
                {
                    Toast.makeText(getApplicationContext(), "Enter skype username", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String mySkypeUri = "skype:"+edskypeusername.getText().toString()+"?call&video=true";
                    if (!isSkypeInstalled(getApplicationContext())) {
                        //if skype is not installed than it will redirect to google play store
                        Uri marketUri = Uri.parse("market://details?id=com.skype.raider");
                        Intent intent = new Intent(Intent.ACTION_VIEW, marketUri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        return;
                    }
                    Uri skypeUri = Uri.parse(mySkypeUri);
                    Intent intent = new Intent(Intent.ACTION_VIEW, skypeUri);
                    intent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
    public boolean isSkypeInstalled(Context context) {
        PackageManager myPackageMgr = context.getPackageManager();
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
        }
        catch (PackageManager.NameNotFoundException e) {
            return (false);
        }
        return (true);
    }
}
