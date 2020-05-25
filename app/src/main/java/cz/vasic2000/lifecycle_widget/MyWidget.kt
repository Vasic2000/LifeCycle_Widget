package cz.vasic2000.lifecycle_widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

class MyWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    companion object {
        fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
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