package com.example.APP.Company.domain.entity.users.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Uf {

    AC("Acre", Region.NORTE),
    AL("Alagoas", Region.NORDESTE),
    AP("Amapá", Region.NORTE),
    AM("Amazonas", Region.NORTE),
    BA("Bahia", Region.NORDESTE),
    CE("Ceará", Region.NORDESTE),
    DF("Distrito Federal", Region.CENTRO_OESTE),
    ES("Espírito Santo", Region.SUDESTE),
    GO("Goiás", Region.CENTRO_OESTE),
    MA("Maranhão", Region.NORDESTE),
    MT("Mato Grosso", Region.CENTRO_OESTE),
    MS("Mato Grosso do Sul", Region.CENTRO_OESTE),
    MG("Minas Gerais", Region.SUDESTE),
    PA("Pará", Region.NORTE),
    PB("Paraíba", Region.NORDESTE),
    PR("Paraná", Region.SUL),
    PE("Pernambuco", Region.NORDESTE),
    PI("Piauí", Region.NORDESTE),
    RJ("Rio de Janeiro", Region.SUDESTE),
    RN("Rio Grande do Norte", Region.NORDESTE),
    RS("Rio Grande do Sul", Region.SUL),
    RO("Rondônia", Region.NORTE),
    RR("Roraima", Region.NORTE),
    SC("Santa Catarina", Region.SUL),
    SP("São Paulo", Region.SUDESTE),
    SE("Sergipe", Region.NORDESTE),
    TO("Tocantins", Region.NORTE);
    
    private final String name;
    private  final Region region;
    
    Uf(final String name, final Region region) {
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }
    public Region getRegion() {
        return region;
    }

    @JsonCreator
    public static Uf fromJson(String value){
        if(value == null){
            return null;
        }
        String v = value.trim().toUpperCase();
        for (Uf uf : Uf.values()){
            if(uf.getName().equals(v)){
                return uf;
            }
        }
        throw new IllegalArgumentException("Invalid UF value: " + value);

    }

    @JsonValue
    public String toJson(){
        return this.name();
    }




}
