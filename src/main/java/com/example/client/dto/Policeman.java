package com.example.client.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
public class Policeman extends Human{

    private int rankNum;


    public Policeman(String name, int age, int rankNum) {
        super(name, age);
        this.rankNum = rankNum;
    }

    @Override
    public String toString() {
        return "Policeman{" +
                "rankNum=" + rankNum +
                "} " + super.toString();
    }
}