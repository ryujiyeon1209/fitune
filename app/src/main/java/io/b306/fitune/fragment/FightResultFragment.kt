package io.b306.fitune.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.b306.fitune.adapter.FightResultAdapter
import io.b306.fitune.api.ApiObject
import io.b306.fitune.api.FightRecordResponse
import io.b306.fitune.databinding.FragmentFightResultBinding
import io.b306.fitune.model.FightRecordData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date

class FightResultFragment : Fragment() {
    private var fightList: List<FightRecordData> = emptyList() // userList를 클래스 멤버 변수로 추가
    private var _binding: FragmentFightResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFightResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userApi = ApiObject.getRetrofitService

        Log.e("대결 리스트1", "안된당!!!1")
        //room에서 가져온 내 아이디
        val userId = 6
        userApi.getFightList(userId).enqueue(object : Callback<FightRecordResponse> {
            override fun onResponse(call: Call<FightRecordResponse>, response: Response<FightRecordResponse>) {
                if (response.isSuccessful) {
                    val fightRecordResponse = response.body()
                    val fightRecordList = fightRecordResponse?.data
                    Log.e("대결 리스트11", fightRecordList.toString())

                    if (fightRecordList != null) {
                        val record = fightRecordResponse.data
                        if(record != null ){
                            fightList = record.map { fightRecord ->
                                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                                val outputDateFormat = SimpleDateFormat("yyyy-MM-dd")
                                val date: Date = dateFormat.parse(fightRecord.battleDate)
                                val formattedDate: String = outputDateFormat.format(date)

                                FightRecordData(
                                    fightRecord.battleRecordSeq,
                                    formattedDate,
                                    fightRecord.winnerName,
                                    fightRecord.battleOtherSeq,
                                    fightRecord.battleOtherName,
                                )
                            }
                            Log.e("hello", fightList.toString())
                        binding.rvFightResult.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = FightResultAdapter(
                                this@FightResultFragment,
                                fightList
                            ) { selectedUser ->
                                val dialogFragment = FightResultDetailDialogFragment().apply {
                                    arguments = Bundle().apply {
                                        putParcelable("fight_result", selectedUser)
                                    }
                                }
                                dialogFragment.show(parentFragmentManager, "userDetail")
                            }
                        }

                        }


                    } else {
                        // 실패 처리

                    }
                }
            }

            override fun onFailure(call: Call<FightRecordResponse>, t: Throwable) {

            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FightResultFragment()
    }
}
