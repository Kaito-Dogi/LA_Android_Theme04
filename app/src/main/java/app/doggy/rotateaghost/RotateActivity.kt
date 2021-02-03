package app.doggy.rotateaghost

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_rotate.*

class RotateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate)

        //カウントを受け取る。
        var energyCount = intent.getIntExtra("energy", 1)

        //アニメーションの開始。
        animateRotation(ghost, energyCount)

    }

    //回転アニメーション。
    private fun animateRotation(animation: LottieAnimationView, energyCount: Int) {
        val objectAnimator = ObjectAnimator.ofFloat(animation, "rotation", 1000f)
        objectAnimator.duration = 20000/energyCount.toLong()
        objectAnimator.repeatCount = energyCount/5
        objectAnimator.start()
    }
}