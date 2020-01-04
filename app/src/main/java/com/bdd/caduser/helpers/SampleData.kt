package com.bdd.caduser.helpers

import com.bdd.caduser.models.User
import java.util.*

object SampleData {

	val USERS = ArrayList<User>()

	private var COUNT = 5

	init {
		// Add some sample items
		val newUser = User()
		newUser.id = 1
		newUser.name = "New Delhi"
		newUser.email = "India"
		USERS.add(newUser)

		val newUser2 = User()
		newUser.id = 2
		newUser.name = "New Delhi"
		newUser.email = "India"
		USERS.add(newUser2)

		val newUser3 = User()
		newUser.id = 3
		newUser.name = "New Delhi"
		newUser.email = "India"
		USERS.add(newUser3)

		val newUser4 = User()
		newUser.id = 4
		newUser.name = "New Delhi"
		newUser.email = "India"
		USERS.add(newUser4)

		val newUser5 = User()
		newUser.id = 5
		newUser.name = "New Delhi"
		newUser.email = "India"
		USERS.add(newUser5)

	}

	fun addUser(item: User) {
		item.id = COUNT
		USERS.add(item)
		COUNT += 1
	}

	fun getUserById(id: Int): User? {
		for (i in USERS.indices) {
			if (USERS[i].id == id) {
				return USERS[i]
			}
		}

		return null
	}

	fun deleteUser(id: Int) {
		var userToRemove: User? = null

		for (i in USERS.indices) {
			if (USERS[i].id == id) {
				userToRemove = USERS[i]
			}
		}

		if (userToRemove != null) {
			USERS.remove(userToRemove)
		}
	}

	fun updateUser(user: User) {
		for (i in USERS.indices) {
			if (USERS[i].id == user.id) {
				val userToUpdate = USERS[i]

				userToUpdate.name = user.name
				userToUpdate.email = user.email
			}
		}
	}
}
