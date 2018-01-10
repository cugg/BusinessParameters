package be.kwakeroni.parameters.petshop.service.hardcoded;

import be.kwakeroni.parameters.petshop.model.ContactInformation;
import be.kwakeroni.parameters.petshop.service.ContactService;

class HardcodedContactService extends ContactService {

    @Override
    public ContactInformation getContactInformation() {
        return new ContactInformation("Demo Petshop", "Cuckoo Street 70, Melbourne, Australia", "+01 555 6789");
    }
}