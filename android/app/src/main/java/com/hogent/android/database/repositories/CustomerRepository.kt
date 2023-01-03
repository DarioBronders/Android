package com.hogent.android.database.repositories

import com.hogent.android.database.entities.Customer
import com.hogent.android.network.dtos.LoginCredentials
import com.hogent.android.network.services.CustomerApi
import com.hogent.android.util.AuthenticationManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import timber.log.Timber

class CustomerRepository(private  val customer_id: Int? = -1) {

    private val customerApi = CustomerApi.service;

    suspend fun updateCustomer(id: Int, cust: Customer): Customer? {
        return customerApi.updateCustomer(id, cust)
    }
    suspend fun getAll(): List<Customer>? {
        return customerApi.getCustomers()
    }
    suspend fun registerCustomer(customer: Customer): Customer {
        return customerApi.registerCustomer(customer)
    }

    suspend fun login(email: String, password: String): Customer?{
        Timber.d("Login request")
        val customer: Customer? = customerApi.loginCustomer(LoginCredentials(email, password))
        if(customer != null){
            AuthenticationManager.setCustomer(customer!!)
        }
        Timber.d(String.format("Customer is null? %s", (customer == null).toString()))
        return customer;
    }
}