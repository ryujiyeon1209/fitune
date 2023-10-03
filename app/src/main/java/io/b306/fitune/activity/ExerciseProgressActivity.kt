package io.b306.fitune.activity

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattService
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import io.b306.fitune.R
import io.b306.fitune.databinding.ActivityExerciseProgressBinding
import io.b306.fitune.databinding.ActivityMainBinding
import io.b306.fitune.databinding.ActivityTutorialsBinding
import java.util.UUID

class ExerciseProgressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseProgressBinding
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private var bluetoothGatt: BluetoothGatt? = null
    var nowHeartRate = MutableLiveData<String>()
    var maxHeartRate = 0;
    var avgHeartRate =0;
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findDevice()

        nowHeartRate.observe(this) { heartRate ->
            binding.curHeart.text = heartRate  // 현재 심박수 업데이트

            // 평균 및 최대 심박수 계산 로직이 필요합니다.
            // 아래는 예시로 임의의 값을 설정하는 코드입니다.
            avgHeartRate += heartRate.toInt()
            maxHeartRate = heartRate.toInt().coerceAtLeast(maxHeartRate)
            binding.avgHeart.text = (avgHeartRate/4).toString()
            binding.maxHeart.text = maxHeartRate.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingPermission")
    private fun findDevice() {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
        val devices = bluetoothAdapter.bondedDevices
        for (device in devices) {
            Log.d("연결시도 하겠음", device.alias.toString())
            // type이 3인 기기들은 bluetooth, bluetoothLE 모두 지원하는 기기들로, 노트북, 스마트폰, 스마트워치 등이 해당함. 뭔가 바꿀 수 있다면 바꾸는게 좋음.
            if (device.type == 3) {
                Log.d("연결시도 하겠음", device.name)
                bluetoothGatt =
                    device.connectGatt(this, true, gattCallback, BluetoothDevice.TRANSPORT_LE)
            }
        }
    }

    // gatt 연결되면 자동으로 실행되는 함수
    private val gattCallback = object : BluetoothGattCallback() {
        @SuppressLint("MissingPermission")
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {

            super.onConnectionStateChange(gatt, status, newState)
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {

                    Log.d("onConnectionStateChange", "연결 성공")
                    gatt?.discoverServices()
                }

                BluetoothProfile.STATE_DISCONNECTED -> {
                    Log.d("onConnectionStateChange", "연결 종료.")
                }

            }
        }

        // gatt 서버에서 서비스를 찾는다.
        @SuppressLint("MissingPermission")
        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {

                val service: BluetoothGattService? =
                    gatt?.getService(UUID.fromString("d950a7fd-6f09-4ac5-dec6-677de893cce2"))

                val characteristic: BluetoothGattCharacteristic? =
                    service?.getCharacteristic(UUID.fromString("6c4bf4ac-8a65-42bf-abfd-dd43f2dd3a22"))

                Log.i("서비스 UUID 조회", service?.uuid.toString());
                Log.i("특성 UUID 조회", characteristic?.uuid.toString())

                if (characteristic != null) {
                    gatt.readCharacteristic(characteristic)
                    val descriptor =
                        characteristic.getDescriptor(UUID.fromString("d950a7fd-6f09-4ac5-dec6-677de893cce4"))
                    gatt.setCharacteristicNotification(characteristic, true)

                } else {
                    Log.e("onServicesDiscovered", "특성 발견 실패")
                }
            }

        }

        // 특성 바뀌면 자동 실행
        @SuppressLint("MissingPermission")
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            gatt?.readCharacteristic(characteristic)
            val data = characteristic?.value
            val heartRate = data?.let { String(it, Charsets.UTF_8) }
            nowHeartRate.postValue(heartRate!!)
            Log.i("onCharacteristicChanged", "변경된 심박수: $heartRate bpm")
        }
    }

    //fragments와 연동을 위한 인터페이스
    interface ExerciseSelectedListener {
        fun onExerciseStartButtonClicked()
    }

    //운동 시작하기 > 누적 시간 count
    class ExerciseProgressActivity : AppCompatActivity(), ExerciseSelectedListener {

        private lateinit var exerciseTimer: TextView
        private var startTimeMillis: Long = 0
        private var timerRunning = false
        private var elapsedTimeMillis: Long = 0
        private val handler = Handler()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_exercise_progress)

            exerciseTimer = findViewById(R.id.exerciseTimer)
        }

        // "운동 시작" 버튼 클릭 시 실행할 코드
        override fun onExerciseStartButtonClicked() {
            startTimeMillis = System.currentTimeMillis()
            startTimer()
        }

        private fun startTimer() {
            if (!timerRunning) {
                handler.post(object : Runnable {
                    override fun run() {
                        elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis
                        updateTimerText(elapsedTimeMillis)
                        handler.postDelayed(this, 1000)
                    }
                })
                timerRunning = true
            }
        }

        private fun updateTimerText(timeInMillis: Long) {
            val seconds = (timeInMillis / 1000).toInt()
            val minutes = seconds / 60
            val hours = minutes / 60

            val formattedTime = String.format(
                "%02d:%02d:%02d",
                hours,
                minutes % 60,
                seconds % 60
            )

            exerciseTimer.text = formattedTime
        }
    }
}