package com.example.financeapp.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.R
import com.example.financeapp.core.Money
import com.example.financeapp.core.RUB
import com.example.financeapp.util.EXTRA_ACCOUNT
import com.example.financeapp.util.EXTRA_ACCOUNT_ID
import kotlinx.android.synthetic.main.activity_add_account.*

class AddAccountActivity : AppCompatActivity() {

    private var accountId : Int = 0
    // todo default type setting
    private var accountType : AccountType = Cash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)

        accountId = intent.getIntExtra(EXTRA_ACCOUNT_ID, 0)

        rgAccountType.setOnCheckedChangeListener { group, checkedId ->
            accountType = when(findViewById<RadioButton>(checkedId).text) {
                getString(R.string.cash) -> Cash
                getString(R.string.card) -> Card
                // todo default type setting
                else -> Cash
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
                            amount = amount.text.toString().toDouble(),
                            currency = RUB()
                        )
                    )
                )
                setResult(Activity.RESULT_OK, it)
            }
            finish()
        }

        if (accountId > 0) {
            // todo fill account fields by ID
//            amount.text = dsAccounts[accountId].amount.toString()
        }

        name.requestFocus()
    }
}
