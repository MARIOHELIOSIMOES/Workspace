package simoes.mario.agendabootcamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events.*
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEvent = findViewById<AppCompatButton>(R.id.btn_setevent)
        btnEvent.setOnClickListener {
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.CONTENT_URI)
                .putExtra(TITLE, "Bootcamp")
                .putExtra(EVENT_LOCATION, "On line")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, (System.currentTimeMillis()+ (60*60*1000)))

            startActivity(intent)
        }

    }
}