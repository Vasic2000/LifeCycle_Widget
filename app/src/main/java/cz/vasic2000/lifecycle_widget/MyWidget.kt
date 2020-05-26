package cz.vasic2000.lifecycle_widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews


class MyWidget : AppWidgetProvider() {
    private val ACTION_SIMPLEAPPWIDGET = "ACTION_BROADCASTWIDGETSAMPLE"

    fun updateAppWidget(context: Context,
                        appWidgetManager: AppWidgetManager,
                        appWidgetId: Int) {
        // Construct the RemoteViews object
        val views = RemoteViews(context.getPackageName(), R.layout.my_widget)
        // Construct an Intent which is pointing this class.
        val intent = Intent(context, MyWidget::class.java)
        intent.action = ACTION_SIMPLEAPPWIDGET
        // And this time we are sending a broadcast with getBroadcast
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        views.setOnClickPendingIntent(R.id.widget_button, pendingIntent)
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (ACTION_SIMPLEAPPWIDGET == intent.action) {
            // Construct the RemoteViews object
            val views = RemoteViews(context.getPackageName(), R.layout.my_widget)
            // Start new activity
            val intt = Intent(context, MainActivity::class.java)
            intt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intt)
            // This time we dont have widgetId. Reaching our widget with that way.
            val appWidget = ComponentName(context, MyWidget::class.java)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidget, views)
        }
    }
}