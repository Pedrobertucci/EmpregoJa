package bertucci.pedro.empregoja.firebase;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import bertucci.pedro.empregoja.R;

/**
 * Created by b_ped on 07/04/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private static final int ID_NOTIFICACAO = 1000;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Contrata Já")
                        .setSound(defaultSoundUri)
                        .setSmallIcon(R.drawable.logomini)
                        .setContentText(remoteMessage.getNotification().getBody());

        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        mNotificationManager.notify(ID_NOTIFICACAO, mBuilder.build());

    }
}
