package com.example.mapproject
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_timer.*


class MainActivity_timer : AppCompatActivity() {

    private var lap = 1

    //번호 선택기에 대한 변수
    lateinit var np1 : NumberPicker
    lateinit var np2 : NumberPicker
    //버튼에 대한 변수
    lateinit var b1 : Button
    lateinit var b2 : Button
    //시간 카운트 다운에 대한 변수

    private var timer : CountDownTimer? = null // 처음 null값 오류 해결
    // lateinit var timer : CountDownTimer 이전 코드
    //정수형 변수
    var n1 = 0
    var n2 = 0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_timer)


        title = "타이머"

        //아이디 연결
        np1 = findViewById(R.id.np1) //분 입력
        np2 = findViewById(R.id.np2) //초 입력
        //번호 선택기 최소 및 최대 값 설정
        np1.minValue = 0
        np1.maxValue = 59
        np2.minValue = 0
        np2.maxValue = 59
        //아이디 연결
        b1 = findViewById(R.id.fab) // 시작 또는 일시정지
        b2 = findViewById(R.id.resetFab) // 리셋

        //START 또는 STOP 버튼 클릭시 이벤트
        b1.setOnClickListener() {
            //START 버튼의 텍스트가 START라면
            if(b1.text=="START") {
                //번호 선택기로 지정한 숫자 값 저장
                n1 = np1.value
                n2 = np2.value
                //START 버튼의 텍스트를 STOP으로 변경
                b1.text="STOP"
                //카운트 다운 타이머 설정. 1초 간격으로 카운트 다운. 설정한 분에 대한 수 * 60초 + 설정한 초에 대한 수 * 1초
                timer = object : CountDownTimer(
                    (np1.value * 60000 + np2.value * 1000).toLong(),
                    1000
                ) {
                    //카운트 다운중일 때
                    override fun onTick(millisUntilFinished: Long) {
                        //0분이 아니고, 0초라면
                        if(np1.value!=0&&np2.value==0) {
                            //1분 감소
                            np1.value = 0
                            //59초로 설정
                            np2.value = 0
                        }
                        //초에 대한 정보 저장. 남은 초 - 남은 분 * 60초
                        np2.value = (millisUntilFinished / 1000).toInt() - np1.value*60
                    }
                    //카운트 다운이 끝났을 때
                    override fun onFinish() {
                        //START 버튼의 텍스트가 STOP으로 바뀐 상태이므로 START로 초기화
                        b1.text="START"
                    }
                }
                //타이머 시작
                (timer as CountDownTimer).start()
                //START 버튼의 텍스트가 STOP이라면
            } else {
                //STOP 텍스트를 START로 변경
                b1.text="START"
                //타이머 취소
                timer?.cancel()
            }
        }

        //초기화 버튼을 클릭했을 때 이벤트
        b2.setOnClickListener() {

            //START 버튼의 텍스트가 START이든 STOP이든 상관없이 START로 바꿈
            b1.text="START"
            //타이머 취소

            timer?.cancel()
            //분과 초의 정보를 START버튼을 클릭했을 때 설정했었던 숫자로 돌려줌
            np1.value = n1
            np2.value = n2

            // 모든 랩타임을 제거
            lapLayout.removeAllViews()
            lap = 1


        }



        lapButton.setOnClickListener {
            recordLapTime()
        }

        //돌아가기
        val btnBack = findViewById<Button>(R.id.back)
        btnBack.setOnClickListener {
            finish()
        }


    }

    // 기록
    @SuppressLint("SetTextI18n")
    private fun recordLapTime() {

        val textView = TextView(this)
        textView.text = "$lap 기록 : " + np1.value + "분" + np2.value +"초"

        // 맨 위에 랩타임 추가
        lapLayout.addView(textView, 0)
        lap++
    }


}