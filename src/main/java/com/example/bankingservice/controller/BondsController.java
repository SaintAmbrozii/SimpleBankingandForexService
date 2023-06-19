package com.example.bankingservice.controller;




import com.example.bankingservice.api.JsonParser;
import com.example.bankingservice.api.MoexApi;
import com.example.bankingservice.api.MoexParser;
import com.example.bankingservice.api.XMLCBRParser;
import com.example.bankingservice.domain.BondDTO;
import com.example.bankingservice.domain.CurrRate;
import com.example.bankingservice.domain.Currency;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/bonds")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BondsController {

    private XMLCBRParser parser;
    private MoexParser moexParser;
    private JsonParser JsonParser;
    private MoexApi api;




    @GetMapping
    public List<CurrRate> getBonds() {
        return parser.getAllRates();
    }

    @GetMapping("map")
    public Map<Currency,BigDecimal> getMap() {
        return parser.getMapRates(parser.getAllRates());
    }




    @GetMapping("bonds")
    public List<BondDTO> getAllBonds() throws IOException {
            return moexParser.getAllBonds();
    }



}
