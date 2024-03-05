package com.finance.transactions.controller;
import com.finance.transactions.kirana.CurrencyExchange;
import com.finance.transactions.kirana.GetKiranaDto;
import com.finance.transactions.kirana.MyKirana;
import com.finance.transactions.repository.KiranaRepository;
import com.finance.transactions.service.KiranaService;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@RestController
public class KiranaController {
    KiranaService kiranaService;

    KiranaController(KiranaService kiranaService){
        this.kiranaService=kiranaService;
    }


    @Autowired
    KiranaRepository kiranaRepository;

//    @GetMapping("/transaction/{id}")
//    public MyKirana getTransactionById(@PathVariable Integer id){
//        System.out.println("Entering into get request "+id);
//        MyKirana temp=kiranaRepository.findById(id).get();
//        return temp;
//    }
    @GetMapping("/trans/ById/{id}")
    public GetKiranaDto getTransaction(@PathVariable Integer id){
        GetKiranaDto transaction=kiranaService.getTransactionById(id);
        return transaction;
    }

    @PostMapping("/trans")
    public ResponseEntity<String> postTransaction(@RequestBody GetKiranaDto getKiranaDto){

        MyKirana myKirana=kiranaService.toKirana(getKiranaDto);
        try{
            kiranaService.postTransactionKirana(myKirana);
            return new ResponseEntity<>("Transaction created successfully "+myKirana.getId(), HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("Transaction failed due to,"+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //get all entries
    @GetMapping("/trans")
    public List<MyKirana> getAllTransactions(){
        List<MyKirana> temp=kiranaRepository.findAll();
        return temp;
    }

    //get all transactions of a month
    @GetMapping("/trans/ByMonth/{month}")
    public List<GetKiranaDto> getTransactionsByMonth(@PathVariable String month){
        List<GetKiranaDto> temp=kiranaService.getTransactionByMonth(month);
        return temp;
//        return kiranaRepository.findByMonth(month);
    }

    //get transactions by their type
    @GetMapping("trans/ByType/{type}")
    public List<MyKirana> getTransactionsByType(@PathVariable String type){
        List<MyKirana> temp=kiranaRepository.findByType(type);
        return temp;
    }

    //sum of transactions that have their type=type
    @GetMapping("trans/GetSum/{type}")
    public Integer getSumByType(@PathVariable String type){
        Integer sum=kiranaService.getSumByType(type);
        return sum;
    }

    @GetMapping("trans/GetDate/")
    public List<MyKirana> getLocalDate(){
        LocalDate date_temp=LocalDate.parse("2024-02-23");
        List<MyKirana> temp=kiranaRepository.findByDate(date_temp);
        return temp;
    }

    @GetMapping("/trans/Curr")
    public Float getCurrencyDetails(@RequestParam String curr, @RequestParam Integer amount){
//        System.out.println(curr);
        return kiranaService.getDetails(curr,amount);
    }
}
