package com.example.bankingservice.api;

import com.example.bankingservice.domain.BondDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonParser {
    private MoexApi moexApi;

    ObjectMapper objectMapper = new ObjectMapper();


    public List<BondDTO> getAllBounds() throws IOException {
        ArrayList<BondDTO> list = new ArrayList<>();
        Gson gson = new Gson();
        Reader reader = new FileReader("https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR/securities.xml?iss.meta=off&iss.only=marketdata&marketdata.columns=SECID,LAST");
        BondDTO bondDTO = gson.fromJson(reader, BondDTO.class);
        list.add(bondDTO);


        return list;
    }


}
