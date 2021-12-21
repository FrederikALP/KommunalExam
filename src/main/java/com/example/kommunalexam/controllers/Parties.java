package com.example.kommunalexam.controllers;

import com.example.kommunalexam.models.Party;
import com.example.kommunalexam.repositories.PartiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Parties {

    @Autowired
    PartiesRepository parties;

    @GetMapping("/parties")
    public Iterable<Party> getParties() {
        return parties.findAll();
    }

    @GetMapping("/parties/{id}")
    public Party getParty(@PathVariable Long id) {
        return parties.findById(id).get();
    }

    @PostMapping("/parties")
    public Party addParty(@RequestBody Party newParty) {
        newParty.setId(null);
        return parties.save(newParty);
    }

    @PutMapping("/parties/{id}")
    public String updatePartyWithId(@PathVariable Long id, @RequestBody Party partyToUpdateWith) {
        if (parties.existsById(id)) {
            partyToUpdateWith.setId(id);
            parties.save(partyToUpdateWith);
            return "Party was created";
        } else {
            return "Party not found";
        }
    }

    @PatchMapping("/parties/{id}")
    public String patchPartyById(@PathVariable Long id, @RequestBody Party partyToUpdateWith) {
        return parties.findById(id).map(foundParty -> {
            if (partyToUpdateWith.getName() != null) foundParty.setName(partyToUpdateWith.getName());

            parties.save(foundParty);
            return "Party updated";
        }).orElse("Party not found");
    }

    @DeleteMapping("/parties/{id}")
    public void deletePartyById(@PathVariable Long id) {
        parties.deleteById(id);
    }
}