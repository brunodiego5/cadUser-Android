package com.bdd.caduser.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// import android.support.v7.app.AppCompatActivity
import com.bdd.caduser.R
import com.bdd.caduser.helpers.SampleData
import com.bdd.caduser.models.User
import com.bdd.caduser.services.ServiceBuilder
import com.bdd.caduser.services.UserService
import kotlinx.android.synthetic.main.activity_user_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserCreateActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_user_create)

		setSupportActionBar(toolbar)
		val context = this

		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		btn_add.setOnClickListener {
			val newUser = User()
			newUser.name = et_name.text.toString()
			newUser.email = et_email.text.toString()

			val userService = ServiceBuilder.buildService(UserService::class.java)
			val requestCall = userService.newUser(newUser)

			requestCall.enqueue(object: Callback<User> {

				override fun onResponse(call: Call<User>, response: Response<User>) {
					if (response.isSuccessful) {
						finish()
						var newlyCreatedUser = response.body()
						Toast.makeText(context, "Successfull Added", Toast.LENGTH_SHORT).show()
					} else {
						Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
					}
				}

				override fun onFailure(call: Call<User>, t: Throwable) {
					Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
				}



			})
		}
	}
}
