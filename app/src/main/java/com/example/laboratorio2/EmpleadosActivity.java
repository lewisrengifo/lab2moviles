package com.example.laboratorio2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.laboratorio2.entidades.ApiKey;
import com.example.laboratorio2.entidades.DtoDepartamento;
import com.example.laboratorio2.entidades.DtoEmpleado;
import com.example.laboratorio2.entidades.Message;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class EmpleadosActivity extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar2,menu);
        return true;
    }
    String apikey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        obtenerapikey();



    }
    public String obtenerapikey() {
        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey?groupKey=3an4WujfyPA2VddT2vEb";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respuesta", response);
                        Gson gson = new Gson();
                        ApiKey apiKey = gson.fromJson(response, ApiKey.class);
                        Log.d("api-key", apiKey.getApikey());
                        apikey = apiKey.getApikey();

                        obtenerEmpleados();
                        obtenerDepartamentos();
                        buscarDepartamentos("100");
                        //guardarTrabajo( "AE_PRESI", "PRESIIIII", "10000", "1000000", "true");

                        //guardarEmpleado("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
        return "";

    }
    public void obtenerDepartamentos( ) {
        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/departamentos";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respuesta", response);
                        Gson gson = new Gson();
                        DtoDepartamento t = gson.fromJson(response,DtoDepartamento.class);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> cabeceras = new HashMap<>();
                cabeceras.put("api-key",apikey);
                return cabeceras;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
    public void buscarDepartamentos(String id) {

        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/buscar/departamento?id="+id;

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respuesta", response);
                        Gson gson = new Gson();
                        DtoDepartamento t = gson.fromJson(response,DtoDepartamento.class);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> cabeceras = new HashMap<>();
                cabeceras.put("api-key",apikey);
                return cabeceras;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
    public void obtenerEmpleados() {
        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/empleados";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respuesta", response);
                        Gson gson = new Gson();
                        DtoEmpleado t = gson.fromJson(response, DtoEmpleado.class);
                        //Empleado[] = t.getEmpleados();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras = new HashMap<>();
                cabeceras.put("api-key", apikey);
                return cabeceras;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
    public void guardarEmpleado(final String employeeid, final String firstName, final String lastName, final String email, final String phoneNumber, final int jobId, final Double salary,final Double commissionPct,final int managerId,final int departmentId ,final String update) {

        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/empleado";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respuesta", response);
                        Gson gson = new Gson();
                        Message msg = gson.fromJson(response, Message.class);
                        Log.d("msg", msg.getMsg());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> cabeceras = new HashMap<>();
                cabeceras.put("api-key",apikey);
                return cabeceras;
            }
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("employeeid",employeeid);
                params.put("firstName",firstName);
                params.put("lastName",lastName);
                params.put("email",email);
                params.put("update",update);
                params.put("phoneNumber", phoneNumber);
                params.put("jobId", String.valueOf(jobId));
                params.put("salary", String.valueOf(salary));
                params.put("commissionPct", String.valueOf(commissionPct));
                params.put("managerId", String.valueOf(managerId));
                params.put("departmentId", String.valueOf(departmentId));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
    public void borrarEmpleado(String id) {
        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/borrar/empleado"+id;

        StringRequest stringRequest = new StringRequest(StringRequest.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respuesta", response);
                        Gson gson = new Gson();
                        Message msg = gson.fromJson(response, Message.class);
                        Log.d("estado", msg.getMsg());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> cabeceras = new HashMap<>();
                cabeceras.put("api-key",apikey);
                return cabeceras;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network networks = connectivityManager.getActiveNetwork();
            if (networks == null) return false;

            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(networks);
            if (networkCapabilities == null) return false;

            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                return true;
            return false;

        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) return false;

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) return true;
            return false;

        }
    }
}
