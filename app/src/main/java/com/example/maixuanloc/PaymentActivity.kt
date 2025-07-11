package com.example.maixuanloc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtAddress = findViewById<EditText>(R.id.edtAddress)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val btnConfirm = findViewById<Button>(R.id.btnConfirmPayment)
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // đóng Activity hiện tại → quay về màn trước
        }
        btnConfirm.setOnClickListener {
            val name = edtName.text.toString().trim()
            val address = edtAddress.text.toString().trim()
            val phone = edtPhone.text.toString().trim()

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            } else {
                showSuccessDialog()
            }
        }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle("Thanh toán thành công")
            .setMessage("Cảm ơn bạn đã đặt hàng!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                CartManager.clearCart()  // <-- thêm dòng này
                finish()
            }
            .show()
    }

}
