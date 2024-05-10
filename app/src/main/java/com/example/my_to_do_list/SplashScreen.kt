import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.my_to_do_list.CardInfo
import com.example.my_to_do_list.DataObject
import com.example.my_to_do_list.MainActivity
import com.example.my_to_do_list.R
import com.example.my_to_do_list.myDatabase
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        database = Room.databaseBuilder(
            applicationContext, database::class.java, "To_Do"
        ).build()

        // Launch coroutine in lifecycleScope
        lifecycleScope.launch {
            DataObject.listdata = database.dao().getTasks() as MutableList<CardInfo>
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
