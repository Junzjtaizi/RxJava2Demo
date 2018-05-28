package cn.nieking.rxjava2demo.action

import android.util.Log
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/**
 * 延迟发射数据
 */
class Timer {

    init {
        Flowable.timer(2, TimeUnit.SECONDS)
                .subscribe { x -> Log.i("tag", x.toString()) }
    }
}