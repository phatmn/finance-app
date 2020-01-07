package com.example.financeapp.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.R
import com.example.financeapp.core.Money
import com.example.financeapp.core.RUB
import com.example.financeapp.util.EXTRA_ACCOUNT
import com.example.financeapp.util.EXTRA_ACCOUNT_ID
import kotlinx.android.synthetic.main.activity_add_account.*
import java.math.BigDecimal

class AddAccountActivity : AppCompatActivity() {

    private val accountId : Int by lazy { intent.getIntExtra(EXTRA_ACCOUNT_ID, 0) }
    // todo default type setting
    private var accountType : AccountType = AccountType.Cash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)

        rgAccountType.setOnCheckedChangeListener { _, checkedId ->
            accountType = when(checkedId) {
                R.id.cash -> AccountType.Cash
                R.id.card -> AccountType.Card
                // todo default type setting
                else -> AccountType.Cash
            }
        }

        btnSave.setOnClickListener {
            Intent().also {
                it.putExtra(
                    EXTRA_ACCOUNT,
                    Account(
                        name = name.text.toString(),
                        type = accountType,
                        // todo default currency setting
                        amount = Money(
                            amount = BigDecimal(amount.text.toString()),
                            currency = RUB
                        )
                    )
                )
                setResult(Activity.RESULT_OK, it)
            }
            finish()
        }

        if (accountId > 0) {
            // todo fill account fields by ID
//            value.text = dsAccounts[accountId].value.toString()
        }

        name.requestFocus()
    }
}
