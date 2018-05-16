package com.happens.bunterhund.tiere;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tiere{
    @Autowired
    public TierRepository tierRepository;

    public Map<String, Integer> tierArtAnzahl() {
        List<Tier> tiere = this.tierRepository.findAll();

        Map<String, Integer> resultat = new HashMap();
        for (Tier tier : tiere) {
            resultat.putIfAbsent(tier.getArt(), 0);
            resultat.put(
                tier.getArt(),
                resultat.get(tier.getArt()) + 1
            );
        }

        return resultat;
    }
}
