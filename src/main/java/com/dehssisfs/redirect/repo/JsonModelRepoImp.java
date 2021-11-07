package com.dehssisfs.redirect.repo;

import com.dehssisfs.redirect.model.JsonModel;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Repository
public class JsonModelRepoImp implements JsonModelRepo{
    @Override
    public JsonModel getJsonModel()  {
        URL url = null;
        JsonModel jsonModel = null;
        try {
            url = new URL("https://www.tinkoff.ru/api/v1/currency_rates");
            jsonModel = new ObjectMapper().readValue(url, JsonModel.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonModel;
    }
}
