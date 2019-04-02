package ua.alaali.repo;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ua.alaali.model.Group;
import ua.alaali.model.Product;

public class ApiCall {
    private static final String URL_BASE = "http://192.168.0.102:8080/api";
    private static final String URL_GET_ONE = "/one";
    private static final String URL_GET_GROUPS = "/groups";
    private static final String URL_GET_PRODUCTS = "/products";


    private static String getResultJson(String urlGet) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL_BASE + urlGet)
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        return json;
    }


    private static class GetTask extends AsyncTask<String, Void, String> {
        // TODO Может неправильно вызывать синхронный запрос здесь, но потом допишу onProgress
        @Override
        protected String doInBackground(String... strings) {
            String json = null;
            try {
                json = getResultJson(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }
    }

    public Product getProductById(long id) {
        try {
            String json = new GetTask().execute(URL_GET_ONE + "/" + id).get();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Product.class);
        } catch (ExecutionException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProductBySectionId(long id) {
        try {
            String json = new GetTask().execute(URL_GET_PRODUCTS + "/" + id).get();
            ObjectMapper mapper = new ObjectMapper();
            Product[] products = mapper.readValue(json, Product[].class);
            return Arrays.asList(products);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Group> getAllGroup() {
        try {
            String json = new GetTask().execute(URL_GET_GROUPS).get();
            ObjectMapper mapper = new ObjectMapper();
            Group[] groups = mapper.readValue(json, Group[].class);
            return Arrays.asList(groups);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProduct() { //TODO Потом будет постранично
        try {
            String json = new GetTask().execute(URL_GET_PRODUCTS).get();
            ObjectMapper mapper = new ObjectMapper();
            Product[] products = mapper.readValue(json, Product[].class);
            return Arrays.asList(products);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
