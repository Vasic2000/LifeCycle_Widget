package cz.vasic2000.lifecycle_widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class MyWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray) {
        //Создаем новый RemoteViews
        val remoteViews = RemoteViews(context.getPackageName(), R.layout.my_widget)
        //Подготавливаем Intent для Broadcast
        val active = Intent(context, MainActivity::class.java)
        //создаем наше событие
        val actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0)
        //регистрируем наше событие
        remoteViews.setOnClickPendingIntent(R.id.widget_button, actionPendingIntent)

        //обновляем виджет
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews)
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context?, intent: Intent) {
    // on receive function use this for new activity start
        context!!.startActivity(intent)
    }


    companion object {

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val widgetText: CharSequence = context.getString(R.string.appwidget_text)
            val views = RemoteViews(context.getPackageName(), R.layout.my_widget)

            // Здесь обновим текст, будем показывать номер виджета
            views.setTextViewText(
                R.id.appwidget_text,
                String.format("%s - %d", widgetText, appWidgetId)
            )
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}