package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

public class SimpleTest {
    private static final Logger log = LogManager.getLogger();

    @Test
    public void testSumm() {
        log.info("Сейчас мы будем проверять сумму");
        assertEquals("11 + 11 = 22", 22, 11 + 11);
        log.info("Тест прошёл успешно!");
    }

    @Test
    public void serializationWithSerializable() throws IOException, ClassNotFoundException {
        Human vaisilyPupkin = new Human().setAge(10)
                .setGender(Gender.MALE)
                .setName("Vaisily Pupkin");

        ByteOutputStream baos = new ByteOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(vaisilyPupkin);
        oos.flush();
        oos.close();

        log.info(vaisilyPupkin + " after serialization " + baos.toString());

        ByteInputStream bis = new ByteInputStream(baos.getBytes(), baos.getBytes().length);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Human deserializedVasiliyPupkin = (Human) ois.readObject();
        ois.close();
        bis.close();

        log.info("After deserialization: " + deserializedVasiliyPupkin);
        assertEquals(vaisilyPupkin, deserializedVasiliyPupkin);
    }

    @Test
    public void serializationWithJackson() throws JsonProcessingException {
        Human regularAutotester = new Human().setAge(28)
                .setGender(Gender.MALE)
                .setName("Александр");
        XmlMapper xmlMapper = new XmlMapper();
        String xmlAutotester = xmlMapper.writeValueAsString(regularAutotester);

        log.info("Так выглядит автотестер в xml: " + xmlAutotester);

        Human deserializedAutotester = xmlMapper.readValue(xmlAutotester, Human.class);

        assertEquals(regularAutotester, deserializedAutotester);
    }


    @Test
    public void serializationWtihGson() throws JsonProcessingException {
        Human cleverAutotester = new Human().setAge(22)
                .setGender(Gender.FEMALE)
                .setName("Виктория");
        Gson gson = new Gson();
        String cleverAtJson = gson.toJson(cleverAutotester);

        log.info("AT JSON: " + cleverAtJson);

        Human deserializedCleverAutotester = gson.fromJson(cleverAtJson, Human.class);

        assertEquals(cleverAutotester, deserializedCleverAutotester);
    }
}
