package im.zgr.pushservice.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import im.zgr.pushservice.NotificationSdk

class MainActivity : AppCompatActivity() {

    private val sendButton: Button by lazy { findViewById<Button>(R.id.phone_number_button) }
    private val phoneNumberEditText: EditText by lazy { findViewById<EditText>(R.id.phone_number) }
    private val tokenTextView: TextView by lazy { findViewById<TextView>(R.id.current_push_token) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendButton.setOnClickListener {
            sendPhoneNumber()
        }
    }

    private fun sendPhoneNumber() {
        val phoneNumber = phoneNumberEditText.text
        if (phoneNumber.isNotBlank()) {
            NotificationSdk.getInstance(this).registerPhoneNumber(
                phoneNumber.toString(),
                { Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show() },
                { Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show() })
        }
    }

    override fun onStart() {
        super.onStart()
        NotificationSdk.getInstance(this)
            .setLogsEnabled(true)
            .setNotificationIconResId(R.drawable.notification_icon)
            .registerPushToken({ token ->
                tokenTextView.text = token
            }, {
                tokenTextView.text = "Ошибка при регистрации токена $it"
            })
    }
}