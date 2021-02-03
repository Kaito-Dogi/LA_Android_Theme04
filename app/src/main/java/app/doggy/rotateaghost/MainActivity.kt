package app.doggy.rotateaghost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //制限時間を設定。
    companion object {
        const val SECOND = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //制限時間を設定。
        var second = SECOND
        //タイマーがスタートしているかを管理する変数。
        var hasStarted = 0
        //カウント用の変数。
        var energyCount = 0

        //残り時間をTextViewに反映。
        secondTextView.text = "TIME $second"

        //インテントを設定。
        val intent = Intent(applicationContext, RotateActivity::class.java)

        //タイマーを設定。
        val timer: CountDownTimer = object : CountDownTimer(second*1000.toLong(), 1000) {
            override fun onFinish() {
                //残り時間をTextViewに反映。
                secondTextView.text = "TIME 0"
                //残り時間をリセット。
                second = SECOND
                //タイマーを終了。
                hasStarted = 0
                //カウントをインテントに渡す。
                intent.putExtra("energy", energyCount)
                //カウントをリセット。
                energyCount = 0

                //画面遷移。
                startActivity(intent)
            }

            override fun onTick(p0: Long) {
                //残り時間を更新。
                second -= 1
                //残り時間をTextViewに反映。
                secondTextView.text = "TIME $second"
            }
        }

        button.setOnClickListener {
            when(hasStarted) {
                0 -> {
                    //タイマーをスタート。
                    timer.start()
                    hasStarted = 1

                    //カウント。
                    energyCount += 1
                    //カウントをTextViewに反映。
                    energyTextView.text = "ENERGY $energyCount"
                }

                1 -> {
                    //カウント。
                    energyCount += 1
                    //カウントをTextViewに反映。
                    energyTextView.text = "ENERGY $energyCount"
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //ボタンが分かりにくいのでToast。
        Toast.makeText(applicationContext, "Push Thunder Button!", Toast.LENGTH_LONG).show()
    }
}