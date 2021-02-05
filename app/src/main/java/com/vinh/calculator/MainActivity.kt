package com.vinh.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.key_board.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)

        // Dấu chấm của số thực
        btnPoint.setOnClickListener {
            if (tvY.text.isNotEmpty()) {
                // Nếu chưa có dấu chấm thì ta mới thêm vào
                if (tvY.text.indexOf('.') == -1) {
                    tvY.text = tvY.text.toString() + "."

                }
            }
        }

        btnPlus.setOnClickListener {
            execute("+")
        }

        btnSubtract.setOnClickListener {
            execute("-")
        }

        btnMultiply.setOnClickListener {
            execute("*")
        }

        btnDivide.setOnClickListener {
            execute("/")
        }

        // Tính phần trăm
        btnPercent.setOnClickListener {
            if (tvY.text.isNotEmpty()) {
                var a: Double = tvY.text.toString().toDouble()
                a /= 100
                tvY.text = a.toString()
            }
        }

        // Nút đổi dấu toán hạng
        btnSign.setOnClickListener {
            if (tvY.text.isNotEmpty()) {
                var d: Double = tvY.text.toString().toDouble()
                d = -d
                var i: Long = d.toLong()

                if (d == i.toDouble()) tvY.text = i.toString()
                else tvY.text = d.toString()
            }
        }

        // xóa toàn bộ dữ liệu
        btnClear.setOnClickListener {
            tvX.text = ""
            tvY.text = ""
            tvOperator.text = ""
        }

        // Chỉ xóa 1 kí tự bên phải toán hạng đang nhập
        btnDelete.setOnClickListener {
            if (tvY.text.isNotEmpty()) {
                tvY.text = tvY.text.toString().substring(0, tvY.text.length - 1)
            }
        }

        btnCalculate.setOnClickListener {
            execute("=")
        }
    }


    /*
    Hàm thực thi phép tính và thực hiện chuyển đổi giá trị cũng như toán tử
     */
    private fun execute(op: String) {
        if (tvOperator.text.isEmpty()) {
            if (tvY.text.isEmpty()) {
                if (op != "=") tvOperator.text = op
            } else {
                tvX.text = tvY.text
                tvOperator.text = op
                tvY.text = ""
            }
        } else {
            if (tvY.text.isEmpty()) {
                if (op != "=") tvOperator.text = op
            } else {
                var x: Double = tvX.text.toString().toDouble()
                var y: Double = tvY.text.toString().toDouble()

                if (y == 0.0 && tvOperator.text.toString() == "/") {
                    Toast.makeText(this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show()
                    return
                }

                var res: Double = calculate(x, y, tvOperator.text.toString())
                var i: Long = res.toLong()

                if (res == i.toDouble()) tvX.text = i.toString()
                else tvX.text = res.toString()

                if (op == "=") tvOperator.text = ""
                else tvOperator.text = op

                tvY.text = ""
            }

        }
    }

    private fun calculate(x: Double, y: Double, op: String): Double {
        when (op) {
            "+" -> return x + y
            "-" -> return x - y
            "*" -> return x * y
            "/" -> return x / y
        }
        return 0.0
    }

    override fun onClick(v: View?) {
        val btn: Button = v as Button
        tvY.text = tvY.text.toString() + btn.text.toString()
    }
}