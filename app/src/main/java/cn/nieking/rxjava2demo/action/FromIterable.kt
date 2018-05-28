package cn.nieking.rxjava2demo.action

import android.util.Log
import io.reactivex.Flowable

/**
 * formIterable 遍历可迭代数据集合
 */
class FromIterable {

    init {
        val list = listOf("a", "b", "c")

        // 按顺序输出列表元素
        Flowable.fromIterable(list)
                .subscribe { s -> Log.i("tag", s) }
    }
}