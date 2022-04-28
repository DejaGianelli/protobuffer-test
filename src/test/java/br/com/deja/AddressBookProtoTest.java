package br.com.deja;

import br.com.deja.protos.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddressBookProtoTest {
    @Test
    void serializeAndDeserializeBackAddressBookProto() throws Exception {
        Person.Builder personBuilder = Person.newBuilder();
        personBuilder.setId(1);
        personBuilder.setName("H495hFl455");
        personBuilder.setEmail("h495hfl455@email.com");

        Person.PhoneNumber.Builder phoneNumberBuilder = Person.PhoneNumber.newBuilder().setNumber("551994685478");
        phoneNumberBuilder.setType(Person.PhoneType.MOBILE);

        personBuilder.addPhones(phoneNumberBuilder.build());

        var person = personBuilder.build();
        byte[] message = person.toByteArray();

        Person.Builder deserializedPersonBuilder = Person.newBuilder();
        var deserializedPerson = deserializedPersonBuilder.mergeFrom(message).build();

        assertNotNull(person);
        assertEquals(person.getId(), deserializedPerson.getId());
        assertEquals(person.getEmail(), deserializedPerson.getEmail());
        assertEquals(person.getName(), deserializedPerson.getName());
        assertEquals(person.getPhonesCount(), deserializedPerson.getPhonesCount());
        assertEquals(person.toByteArray().length, deserializedPerson.toByteArray().length);
    }
}
