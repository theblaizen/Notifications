package owdienko.jaroslaw.testapplication1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mainUserInput;
    Button mainBtnAcceptInput;
    RecyclerView mainRecyclerView;
    CustomRecyclerViewAdapter mainRecyclerViewAdapter;
    NotificationCompat.Builder notification;
    private static final int uniqID = 847592;

    List<String> userInputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIElements();

        mainBtnAcceptInput.setOnClickListener(setOnClickListenerToButton());
    }

    private void initUIElements() {
        mainUserInput = (EditText) findViewById(R.id.main_activity_editField);
        mainBtnAcceptInput = (Button) findViewById(R.id.main_activity_btnPush);
        mainRecyclerView = (RecyclerView) findViewById(R.id.main_activity_recyclerView);
        userInputData = new ArrayList<>();
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);


        mainRecyclerViewAdapter = new CustomRecyclerViewAdapter(this, userInputData);
        mainRecyclerView.setAdapter(mainRecyclerViewAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private View.OnClickListener setOnClickListenerToButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputData.add(mainUserInput.getText().toString());
                mainRecyclerViewAdapter.notifyDataSetChanged();
                addNotification(mainUserInput.getText().toString());

                mainUserInput.setText("");
            }
        };
    }

    private void addNotification(String text) {
        notification.setSmallIcon(R.mipmap.ic_launcher_round);
        notification.setTicker("Notification");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("App Notification");
        notification.setContentText(text);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(uniqID, notification.build());
    }
}
