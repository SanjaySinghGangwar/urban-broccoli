package dev.sanjaygangwar.tempproject.workers.DummyWorker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.UUID
import java.util.concurrent.TimeUnit

object DummyWorkers {
    private var workerID: UUID? = null
    private var workerName: String = "Send Notification"
    private var workManager: WorkManager? = null
    const val time: Long = 20
    private var constraints: Constraints? = null
    private var work: PeriodicWorkRequest? = null

    fun startDummyWorker(context: Context) {
        initWorker(context)
        if(!isWorkerRunning()){
            startWorkerForNotification()
        }
    }

    private fun initWorker(context: Context) {
        workManager = WorkManager.getInstance(context)
        constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        constraints?.let {
            work = PeriodicWorkRequest
                .Builder(DummyWorkerTask::class.java, time, TimeUnit.HOURS)
                .setConstraints(it).build()
        }
        workerID = work?.id
    }

    private fun isWorkerRunning(): Boolean {
        val workInfo = workerID?.let { workManager?.getWorkInfoByIdLiveData(it)?.value }
        return workInfo != null && workInfo.state == WorkInfo.State.RUNNING
    }

    private fun startWorkerForNotification() {
        work?.let { worker -> workManager?.enqueue(worker) }
        work?.let { worker ->
            workManager?.enqueueUniquePeriodicWork(
                workerName,
                ExistingPeriodicWorkPolicy.REPLACE,
                worker
            )
        }
    }
}