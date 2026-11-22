package com.example.zbaqtd

import android.content.Intent
import android.os.Bundle
import android.app.Activity
import android.widget.Button

class Datingluo : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datingyang)

        findViewById<Button>(R.id.btnZinv).setOnClickListener {
            // 建好子女端页面后，删掉下行注释
            // startActivity(Intent(this, Zinvduanluo::class.java))
        }

        findViewById<Button>(R.id.btnLaoren).setOnClickListener {
            // startActivity(Intent(this, Laorenduanluo::class.java))
        }

        findViewById<Button>(R.id.btnJianyi).setOnClickListener {
            // startActivity(Intent(this, Jianyiduanluo::class.java))
        }
    }
}
