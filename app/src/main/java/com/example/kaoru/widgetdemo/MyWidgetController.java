package com.example.kaoru.widgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kaoru on 15/06/21.
 */
public class MyWidgetController {
    private static final MyWidgetController sInstance = new MyWidgetController();

    private AtomicInteger mAtomicInteger = new AtomicInteger(0);

    private final int[] mDrawableResources = {
            R.drawable.widget,
            R.drawable.widget2,
            R.drawable.widget3,
    };
    
    public static MyWidgetController getInstance() {
        return sInstance;
    }

    public void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        int index = mAtomicInteger.get() % mDrawableResources.length;
        updateWidget(context, index, appWidgetManager);
    }

    public void onClickWidget(Context context) {
        int index = mAtomicInteger.addAndGet(1) % mDrawableResources.length;
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        updateWidget(context, index, manager);
    }

    private void updateWidget(Context context, int index, AppWidgetManager manager) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        Intent intent = new Intent();
        intent.setAction("com.example.kaoru.widgetdemo.UPDATE_WIDGET");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.widget_button, pendingIntent);
        remoteViews.setImageViewResource(R.id.widget_button, mDrawableResources[index]);

        ComponentName myWidget = new ComponentName(context, MyWidgetProvider.class);
        manager.updateAppWidget(myWidget, remoteViews);
    }
}
