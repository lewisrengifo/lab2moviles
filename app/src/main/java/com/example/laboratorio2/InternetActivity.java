package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.laboratorio2.entidades.ApiKey;
import com.example.laboratorio2.entidades.DtoDepartamento;
import com.example.laboratorio2.entidades.DtoEmpleado;
import com.example.laboratorio2.entidades.DtoTrabajo;
import com.example.laboratorio2.entidades.Empleado;
import com.example.laboratorio2.entidades.Message;
import com.example.laboratorio2.entidades.Trabajo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class InternetActivity extends AppCompatActivity {
    String apikey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obtenerapikey();


    }

    public void obtenerDeInternet() {
        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/trabajos";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respuesta", response);
                        Gson gson = new Gson();
                        DtoTrabajo t = gson.fromJson(response, DtoTrabajo.class);
                        Log.d("trabajo", String.valueOf(t.getTrabajos().length));

                        DtoEmpleado dtoEmpleado = gson.fromJson(response,DtoEmpleado.class);
                        Empleado[] listaEmpleados = dtoEmpleado.getEmpleados();

                        ListaEmpleadosAdapter listaEmpleadosAdapter =
                                new ListaEmpleadosAdapter(listaEmpleados,InternetActivity.this);

                        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
                        recyclerView.setAdapter(listaEmpleadosAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(InternetActivity.this));

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
                        obtenerDeInternet();
                        obtenerEmpleados();
                        obtenerDepartamentos();
                        buscarDepartamentos("100");
                        //guardarTrabajo( "AE_PRESI", "PRESIIIII", "10000", "1000000", "true");
                        borrarTrabajo("AD_PRESI");
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
                        Log.d("departamento", String.valueOf(t.getDepartamentos().length));

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
                        Log.d("departamento", String.valueOf(t.getDepartamentos().length));

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


    public void guardarTrabajo(final String jobid, final String jobtitle, final String minsalary, final String maxsalary, final String update) {

        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/trabajo";

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
                params.put("jobId",jobid);
                params.put("jobTitle",jobtitle);
                params.put("minSalary",minsalary);
                params.put("maxSalary",maxsalary);
                params.put("update", update);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }


    public void borrarTrabajo(String id) {
        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/borrar/trabajo?id="+id;

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

    public void obtenerEmpleados() {
        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/empleados";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respuesta", response);
                        Gson gson = new Gson();
                        DtoEmpleado t = gson.fromJson(response, DtoEmpleado.class);
                        Log.d("empleado", String.valueOf(t.getEmpleados().length));

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
