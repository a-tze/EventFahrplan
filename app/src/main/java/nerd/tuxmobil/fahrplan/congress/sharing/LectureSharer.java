package nerd.tuxmobil.fahrplan.congress.sharing;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import nerd.tuxmobil.fahrplan.congress.MyApp;

public class LectureSharer {

    // String formattedLectures can be one or multiple lectures
    public static boolean shareSimple(@NonNull Context context, @NonNull String formattedLectures) {
        return share(context, formattedLectures, "text/plain");
    }

    public static boolean shareJson(@NonNull Context context, @NonNull String formattedLectures) {
        return share(context, formattedLectures, "text/json");
    }

    private static boolean share(@NonNull Context context, @NonNull String formattedLectures, @NonNull String mimeType) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, formattedLectures);
        intent.setType(mimeType);
        PackageManager packageManager = context.getPackageManager();
        if (intent.resolveActivity(packageManager) == null) {
            MyApp.LogDebug(LectureSharer.class.getSimpleName(), "No activity to handle share intent.");
            return false;
        } else {
            context.startActivity(intent);
            return true;
        }
    }

}
