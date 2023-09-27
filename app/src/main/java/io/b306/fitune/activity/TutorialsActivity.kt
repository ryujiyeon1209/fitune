package io.b306.fitune.activity

import android.Manifest
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
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import io.b306.fitune.adapter.TutorialsPagerAdapter
import io.b306.fitune.databinding.ActivityTutorialsBinding
import io.b306.fitune.fragment.Tutorial1Fragment
import io.b306.fitune.fragment.Tutorial2Fragment
import io.b306.fitune.fragment.Tutorial3Fragment
import io.b306.fitune.fragment.Tutorial4Fragment
import java.util.UUID

class TutorialsActivity : AppCompatActivity(),
    Tutorial1Fragment.TutorialPageNavigator, Tutorial2Fragment.TutorialPageNavigator,
    Tutorial3Fragment.TutorialPageNavigator, Tutorial4Fragment.TutorialPageNavigator {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private var bluetoothGatt: BluetoothGatt? = null
    var nowHeartRate = MutableLiveData<String>()
    private lateinit var binding: ActivityTutorialsBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewPager2에 어댑터를 설정합니다.
        val adapter = TutorialsPagerAdapter(this)
        binding.viewPagerTutorials.adapter = adapter
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 306);
        }
        if (checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf<String>(Manifest.permission.BLUETOOTH_CONNECT), 306);
        }
        if (checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf<String>(Manifest.permission.BLUETOOTH_SCAN), 306);
        }
        findDevice()
    }

    override fun moveToNextPage() {
        val currentPosition = binding.viewPagerTutorials.currentItem
        val nextPosition = currentPosition + 1
        binding.viewPagerTutorials.setCurrentItem(nextPosition, true)
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
}
