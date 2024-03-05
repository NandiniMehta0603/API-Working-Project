package com.finance.transactions.service;

import com.finance.transactions.kirana.CurrencyExchange;
import com.finance.transactions.kirana.GetKiranaDto;
import com.finance.transactions.kirana.MyKirana;
import com.finance.transactions.repository.KiranaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class KiranaService {
    KiranaRepository kiranaRepository;

    KiranaService(KiranaRepository kiranaRepository) {
        this.kiranaRepository = kiranaRepository;
    }

    public void postTransactionKirana(MyKirana myKirana) {
        kiranaRepository.save(myKirana);
    }

    public GetKiranaDto getTransactionById(Integer id) {
        Optional<MyKirana> field = kiranaRepository.findById(id);
        MyKirana myKirana = field.get();
        GetKiranaDto getKiranaDto = new GetKiranaDto();
        getKiranaDto.setAmount(myKirana.getAmount());
        getKiranaDto.setType_of_trans(myKirana.getType_of_trans());
        return getKiranaDto;
    }

    //mapping code
    public GetKiranaDto toDto(MyKirana myKirana) {
        String type = myKirana.getType_of_trans();
        int amount = myKirana.getAmount();
        return new GetKiranaDto(amount, type);
    }

    public MyKirana toKirana(GetKiranaDto getKiranaDto) {
        MyKirana myKirana = new MyKirana();
        myKirana.setDate(LocalDate.now());
        myKirana.setAmount(getKiranaDto.getAmount());
        myKirana.setType_of_trans(getKiranaDto.getType_of_trans());

//        int id=kiranaRepository.findLastId();
//        myKirana.setId(id);
        return myKirana;
    }

    public List<GetKiranaDto> getTransactionByMonth(String month) {
        List<GetKiranaDto> monthlyTransactions = kiranaRepository.findByMonth(month)
                .stream()
                .map(u -> toDto(u))
                .collect(Collectors.toList());

//        List<MyKirana> monthlyTransactions=kiranaRepository.findByMonth(month);
        return monthlyTransactions;
    }

    public Integer getSumByType(String type) {
        Integer sum = kiranaRepository.findByType(type)
                .stream()
                .map(u -> u.getAmount())
                .reduce(0, (a, b) -> a + b);
        return sum;
    }

    RestTemplate restTemplate = new RestTemplate();
    String forex_url = "https://api.fxratesapi.com/latest";

    public Float getDetails(String curr,Integer amount) {
        ResponseEntity<CurrencyExchange> response = restTemplate.exchange(forex_url + "?currencies=" + curr,
                HttpMethod.GET, null, CurrencyExchange.class);
        CurrencyExchange temp=response.getBody();
        Float currency_rate=temp.getRates().get(curr);
        return amount/currency_rate;
    }
}
